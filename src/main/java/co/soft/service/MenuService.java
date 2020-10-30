package co.soft.service;

import java.util.List;

import co.soft.domain.MenuInfoBean;

public interface MenuService {

	List<MenuInfoBean> getMenuList(MenuInfoBean menuinfo);
	
	MenuInfoBean getMenu(MenuInfoBean menuinfo);
}
