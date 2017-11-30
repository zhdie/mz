package com.deloitte.hk.mizuho.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.hk.common.bean.menu.MenuPo;
import com.deloitte.hk.common.enums.ResponseEnum;
import com.deloitte.hk.common.exception.ServiceException;
import com.deloitte.hk.common.util.BeanConvertUtil;
import com.deloitte.hk.mizuho.dao.T101userMapper;
import com.deloitte.hk.mizuho.dao.model.T101user;
import com.deloitte.hk.mizuho.dao.model.T102menu;
import com.deloitte.hk.mizuho.service.ILoginService;

@Service
@Transactional
public class LoginService implements ILoginService {

	private static Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	private T101userMapper t101userMapper;
	
	public T101user getByUserid(String userid)  throws ServiceException  {
		try{
			return t101userMapper.getByUserid(userid);			
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("", e);
			throw new ServiceException(ResponseEnum.SYS_ERROR.code(), ResponseEnum.SYS_ERROR.getMessage());
		}
	}
	
	public void delUser(String userid) {
		try{
			t101userMapper.delByUserid(userid);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("", e);
			throw new ServiceException(ResponseEnum.SYS_ERROR.code(), ResponseEnum.SYS_ERROR.getMessage());
		}
	}
	public void saveOrUpdateUser(T101user user)  throws ServiceException  {
		try{
			if (null == user) {
				throw new ServiceException(ResponseEnum.PARAM_NULL.code(), ResponseEnum.PARAM_NULL.getMessage());
			}
			T101user db = t101userMapper.getByUserid(user.getUserid());
			Date now = new Date();
			user.setUpdatetime(now);
			if (null == db) {
				user.setCreatetime(now);
				t101userMapper.insertSelective(user);
			} else {
				List<String> excludeFds = new ArrayList<String>();
				excludeFds.add("id");
				BeanConvertUtil.copy(user, db, excludeFds);
				t101userMapper.updateByPrimaryKey(db);
			}
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("", e);
			throw new ServiceException(ResponseEnum.SYS_ERROR.code(), ResponseEnum.SYS_ERROR.getMessage());
		}
	}
	
	public List<MenuPo> queryMenu(String userid) throws ServiceException  {
		try{
			List<MenuPo> result = new ArrayList<MenuPo>();
			List<T102menu> dblist = t101userMapper.queryMenu(userid);
			for (int i = 0; i < dblist.size(); i++) {
				T102menu daomenu = dblist.get(i);
				MenuPo menu = new MenuPo();
				BeanUtils.copyProperties(daomenu, menu);
				result.add(menu);
			}
			return result;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("", e);
			throw new ServiceException(ResponseEnum.SYS_ERROR.code(), ResponseEnum.SYS_ERROR.getMessage());
		}
	}
}
