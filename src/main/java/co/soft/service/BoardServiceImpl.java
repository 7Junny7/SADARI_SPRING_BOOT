package co.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.soft.domain.MenuInfoBean;
import co.soft.persistence.BoardRepository;


@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;

	public List<MenuInfoBean> getBoardList(MenuInfoBean board) {
		return (List<MenuInfoBean>) boardRepo.findAll();
	}

	@Override
	public void insertBoard(MenuInfoBean menuinfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MenuInfoBean getBoard(MenuInfoBean menuinfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBoard(MenuInfoBean menuinfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(MenuInfoBean menuinfo) {
		// TODO Auto-generated method stub
		
	}

//	public void insertBoard(MenuInfoBean board) {
//		boardRepo.save(board);
//	}
//
//	public MenuInfoBean getBoard(MenuInfoBean board) {
//		return boardRepo.findById(board.getSeq()).get();
//	}
//
//	public void updateBoard(MenuInfoBean board) {
//		MenuInfoBean findBoard = boardRepo.findById(board.getSeq()).get();
//
//		findBoard.setTitle(board.getTitle());
//		findBoard.setContent(board.getContent());
//		boardRepo.save(findBoard);
//	}
//
//	public void deleteBoard(MenuInfoBean board) {
//		boardRepo.deleteById(board.getSeq());
//	}

}
