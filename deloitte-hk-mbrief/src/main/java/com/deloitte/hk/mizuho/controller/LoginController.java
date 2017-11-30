package com.deloitte.hk.mizuho.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.hk.common.bean.AuthBean;
import com.deloitte.hk.common.bean.RestDataResult;
import com.deloitte.hk.common.bean.menu.MenuPo;
import com.deloitte.hk.common.bean.menu.MenuServerResult;
import com.deloitte.hk.common.enums.ResponseEnum;
import com.deloitte.hk.common.exception.ServiceException;
import com.deloitte.hk.common.token.Token;
import com.deloitte.hk.common.token.TokenManager;
import com.deloitte.hk.common.util.ConstConf;
import com.deloitte.hk.mizuho.dao.model.T101user;
import com.deloitte.hk.mizuho.service.ILoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author deezhang
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseBody
	public RestDataResult addUser(@RequestBody T101user user) {
		RestDataResult result = new RestDataResult();
		try {
			loginService.saveOrUpdateUser(user);
			result.setRespcode(ResponseEnum.SUCC.getCode());
			result.setMessage(ResponseEnum.SUCC.getMessage());
		} catch (ServiceException e) {
			result.setRespcode(e.getErrorCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error(result.getRespUUID(),e);
			result.setRespcode(ResponseEnum.SYS_ERROR.getCode());
			result.setMessage(ResponseEnum.SYS_ERROR.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/checkUserid", method = RequestMethod.POST)
	@ResponseBody
	public String checkUserid(@RequestParam(value = "userid", required = true) String userid,
			@RequestParam(value = "token", required = false) String token) {
		String resultString = "{\"valid\":false}";
		try {
			T101user user = loginService.getByUserid(userid);
			if (null == user) {
				resultString = "{\"valid\":true}";
				return resultString;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return resultString;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public AuthBean login(
			@RequestParam(value = "loginid", required = true) String loginid,
			@RequestParam(value = "loginpwd", required = false) String loginpwd) {
		AuthBean auth = new AuthBean();
		try {
			T101user tuser = loginService.getByUserid(loginid);
			if (null != tuser) {
				if (null != tuser.getUserpwd() && tuser.getUserpwd().equals(loginpwd)) {
					Token tk = TokenManager.getInstance().newToken("", tuser.getUserid(), tuser.getUsername(), ConstConf.getToken_expires());
					if (null != tk) {
						auth.setToken(tk.getToken());
						auth.setRole(tuser.getRoleid());				
						auth.setAuth(true);		
					} else {
						auth.setAuth(false);
					}	
				} else {
					auth.setAuth(false);
				}			
			} else {
				auth.setAuth(false);
			}
			return auth;
		} catch (Exception e) {
			logger.error("登录错误.", e);
			auth.setAuth(false);
			return auth;
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getmenu", method = RequestMethod.POST)
	@ResponseBody
	public MenuServerResult getmenu(HttpServletRequest request,
			HttpServletResponse response) {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String applicationid = request.getParameter("applicationid");
		MenuServerResult result = new MenuServerResult();
		try {
			List<MenuPo> list = loginService.queryMenu(userid);//fmsUserService.queryMenu(userid);			
			// list = MenuTest.getTestMenu();
			Map<String, List<MenuPo>> pidMap = new HashMap<String, List<MenuPo>>();
			// 每一菜单ID 对应的子节点
			for (int i = 0; i < list.size(); i++) {
				MenuPo item = list.get(i);
				List<MenuPo> childList = pidMap.get(item.getParentid());
				if (null == childList) {
					childList = new ArrayList<MenuPo>();
					childList.add(item);
					pidMap.put(item.getParentid(), childList);
				} else {
					childList.add(item);
				}
			}
			Map<String, MenuPo> topMap = new HashMap<String, MenuPo>();
			Collection<List<MenuPo>> pidchildren = pidMap.values();
			if (null != pidchildren) {
				for (Iterator<List<MenuPo>> iter = pidchildren.iterator(); iter
						.hasNext();) {
					List<MenuPo> childList = iter.next();
					Collections.sort(childList, new Comparator() {
						public int compare(Object arg1, Object arg2) {
							MenuPo obj1 = (MenuPo) arg1;
							MenuPo obj2 = (MenuPo) arg2;
							return obj1.getOrderno() == obj2.getOrderno() ? 0
									: (obj1.getOrderno() > obj2.getOrderno() ? 1
											: -1);
						}
					});
				}
			}
			List<MenuPo> childList = pidMap.get("0");
			MenuPo root = new MenuPo();
			nestParse(childList, pidMap, root);

			list.get(0).setActive(true);
			list.get(0).setStart(true);
			// list.get(1).getChildren().add(list.get(2));
			result.setSuccess(true);
//			result.setAuth(true);
			if (null != root.getChildren()) {
				result.setSuccess(true);
				result.setData(root.getChildren());
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setMessage("验证失败");
		}
		return result;
	}
	
	private static void nestParse(List<MenuPo> childList,
			Map<String, List<MenuPo>> pidMap, MenuPo curNode) {
		if (null == childList || childList.isEmpty()) {
			return;
		}
		List<MenuPo> children = new ArrayList<MenuPo>();
		for (Iterator iter = childList.iterator(); iter.hasNext();) {
			MenuPo child = (MenuPo) iter.next();
			children.add(child);
			if (null != pidMap.get(child.getMenuid())
					&& !pidMap.get(child.getMenuid()).isEmpty()) {
				nestParse(pidMap.get(child.getMenuid()), pidMap, child);
			}
		}
		if (!children.isEmpty()) {
			curNode.setChildren(children);
		}
	}

}
