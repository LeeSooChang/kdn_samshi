package com.kdn.controller;
 
import java.util.List;
 









import javax.servlet.http.HttpServletRequest;
 









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 









import com.kdn.model.biz.DietService;
import com.kdn.model.biz.NoticeBoardService;
import com.kdn.model.biz.RankingService;
import com.kdn.model.biz.ReviewService;
import com.kdn.model.domain.Diet;
import com.kdn.model.domain.NoticeBoard;
import com.kdn.model.domain.NoticePageBean;
import com.kdn.model.domain.PageBean;
import com.kdn.model.domain.Ranking;
import com.kdn.model.domain.Review;
import com.kdn.model.domain.ReviewPageBean;
 
@Controller
public class NoticeBoardController {
	
	@ExceptionHandler
	public ModelAndView handler(Exception e) {
		ModelAndView model = new ModelAndView("index");
		model.addObject("msg", e.getMessage()); 
		model.addObject("noticeBoard", "ErrorHandler.jsp");
		return model;
		
	}
	
	@Autowired
	private NoticeBoardService noticeBoardService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	DietService dietService;
	
	@Autowired
	private RankingService rankingService;
	
	@RequestMapping(value="insertNoticeBoardForm.do", method=RequestMethod.GET)
	public String insertBoardForm(Model model, ReviewPageBean bean) {
		model.addAttribute("noticeBoardContent", "notice_board/insertBoard.jsp");
		
		List<Review> list = reviewService.searchAll(bean);
		model.addAttribute("list", list);
		model.addAttribute("reviewBoardContent", "review_board/listReviewFromHome.jsp");
		
		return "index";
	}
	@RequestMapping(value="insertNoticeBoard.do", method=RequestMethod.POST)
	public String insertBoard(NoticeBoard noticeBoard, HttpServletRequest request) {
		String dir = request.getRealPath("upload/");
		System.out.println("upload directory : " + dir);
		noticeBoardService.add(noticeBoard, dir);
		
		return "redirect:listNoticeBoard.do";
	}
	
	@RequestMapping(value="listNoticeBoard.do", method=RequestMethod.GET)
	public String listBoard(NoticePageBean noticebean, Model model, ReviewPageBean bean) {
		List<NoticeBoard> noticeList = noticeBoardService.searchAll(noticebean);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("noticePageBean", noticebean);
		model.addAttribute("noticeBoardContent", "notice_board/listBoard.jsp");
		
		List<Review> list = reviewService.searchAll(bean);
		model.addAttribute("list", list);
		model.addAttribute("reviewBoardContent", "review_board/listReviewFromHome.jsp");
		
		List<Diet> dietList = dietService.searchAll();
		model.addAttribute("dietList", dietList);
		model.addAttribute("weeklyMenuContent", "weekly_menu/weeklyMenu.jsp");
		
		List<Ranking> rankingList = rankingService.searchN();
		System.out.println("저녁"+rankingList);
		model.addAttribute("rankingList", rankingList);
		List<Ranking> rankingM = rankingService.searchM();
		System.out.println("아침"+rankingM);
		model.addAttribute("rankingM", rankingM);
		List<Ranking> rankingH = rankingService.searchH();
		System.out.println("한식"+rankingH);
		model.addAttribute("rankingH", rankingH);
		List<Ranking> rankingI = rankingService.searchI();
		System.out.println("일품"+rankingI);
		model.addAttribute("rankingI", rankingI);
		
		model.addAttribute("rankingBoardContent", "ranking_board/listBoard.jsp");
		return "index";
	}
 
	@RequestMapping(value="searchNoticeBoard.do", method=RequestMethod.GET)
	public String searchBoard(int nno, Model model) {
		model.addAttribute("noticeBoard", noticeBoardService.search(nno));
		model.addAttribute("noticeBoardContent", "notice_board/searchBoard.jsp");
		return "index";
	}
	
	@RequestMapping(value="updateNoticeBoardForm.do", method=RequestMethod.GET)
	public String updateBoardForm(int nno, Model model){
		model.addAttribute("noticeBoard", noticeBoardService.search(nno));
		model.addAttribute("noticeBoardContent", "notice_board/updateBoard.jsp");
		return "index";
	}
	
	@RequestMapping(value="updateNoticeBoard.do", method=RequestMethod.POST)
	public String updateBoard(NoticeBoard noticeBoard){
		noticeBoardService.update(noticeBoard);
		return "redirect:listNoticeBoard.do";
	}
	
	@RequestMapping(value="deleteNoticeBoard.do", method=RequestMethod.GET)
	public String deletBoard(int nno){
		noticeBoardService.remove(nno);
		return "redirect:listNoticeBoard.do";
	}
 
}
