package co.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import co.soft.domain.MenuInfoBean;
import co.soft.persistence.MenuRepository;

public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuRepository menuRepo;
	
	@Override
	public List<MenuInfoBean> getMenuList(MenuInfoBean menuinfo) {
		return (List<MenuInfoBean>) menuRepo.findAll();
	}

	@Override
	public MenuInfoBean getMenu(MenuInfoBean menuinfo) {
		return menuRepo.findById(menuinfo.getMenu_info_idx()).get();
	}

}
