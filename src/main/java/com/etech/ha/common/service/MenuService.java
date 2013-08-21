package com.etech.ha.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etech.system.bean.Menu;
import com.etech.system.bean.MenuItem;
import com.etech.system.bean.UserInfo;

@Transactional
@Service
public class MenuService {
	
	public void prepareMenu(UserInfo userInfo, Menu menu) {
		menu.getMenuList().clear();
		
		MenuItem baseInfoMenuItem = new MenuItem("menu.maintain", null, false);	//基础信息维护
		menu.getMenuList().add(baseInfoMenuItem);
		prepareBaseSubMenuItem(baseInfoMenuItem);
		

		MenuItem attendanceInfoMenuItem = new MenuItem("menu.attendance", null, false);	//出勤维护
		menu.getMenuList().add(attendanceInfoMenuItem);
	}
	
	private void prepareBaseSubMenuItem(MenuItem menuItem) {
		menuItem.setSubMenuList(new ArrayList<MenuItem>());
		
		List<MenuItem> list = menuItem.getSubMenuList();
		list.add(new MenuItem("menu.maintain.empe_info", null, false));
		list.add(new MenuItem("menu.maintain.holiday", "maintain/holiday", false));
		list.add(new MenuItem("menu.maintain.holiday_info", null, false));
		list.add(new MenuItem("menu.maintain.attendance_status_info", "maintain/adtnsSts", false));
	}
	
	
}
