package co.soft.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.soft.domain.MenuInfoBean;
import co.soft.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/getboard")
	public String getboard(Model model, MenuInfoBean menuinfo) {
//		EntityManagerFactory emf=Persistence.createEntityManagerFactory("menu");
//		EntityManager em=emf.createEntityManager();
//		List<MenuInfoBean> menuList=new ArrayList<MenuInfoBean>();
//		try {
//			MenuInfoBean menu=em.find(MenuInfoBean.class, 1L);
//			
////			menuList.add(menu);
//			System.out.println(menu);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally{
//			em.close();
//			emf.close();
//		}
		List<MenuInfoBean> menuList=boardService.getBoardList(menuinfo);
		System.out.println(menuList.get(0));
		model.addAttribute("boardList", menuList);
		return "getboard";
	}
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
