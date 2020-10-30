package co.soft.service;

import java.util.List;

import co.soft.domain.MenuInfoBean;

public interface BoardService {

	List<MenuInfoBean> getBoardList(MenuInfoBean menuinfo);

	void insertBoard(MenuInfoBean menuinfo);

	MenuInfoBean getBoard(MenuInfoBean menuinfo);

	void updateBoard(MenuInfoBean menuinfo);

	void deleteBoard(MenuInfoBean menuinfo);

}