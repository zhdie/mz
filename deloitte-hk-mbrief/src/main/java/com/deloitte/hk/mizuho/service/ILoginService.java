package com.deloitte.hk.mizuho.service;

import java.util.List;

import com.deloitte.hk.common.bean.menu.MenuPo;
import com.deloitte.hk.mizuho.dao.model.T101user;

public interface ILoginService {

	T101user getByUserid(String userid);
	
	List<MenuPo> queryMenu(String userid);
	
	void saveOrUpdateUser(T101user user);
	
	void delUser(String userid);
}
