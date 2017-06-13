package com.kdn.controller;

import java.util.List;










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
public class ReviewController {
	
//	@ExceptionHandler
//	public ModelAndView handler(Exception e) {
//		ModelAndView model = new ModelAndView("index");
//		model.addObject("msg", e.getMessage()); //request?좎럥???좎룞?쇿뜝?뚯궋
//		model.addObject("reviewBoardContent", "ErrorHandler.jsp"); //request?좎럥???좎룞?쇿뜝?뚯궋
//		return model;
//		
//	}
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private NoticeBoardService noticeBoardService;
	
	@Autowired
	private DietService dietService;
	
	@Autowired
	private RankingService rankingService;
	
	@RequestMapping(value="insertReviewForm.do", method=RequestMethod.GET)
	public String insertBoardForm(Model model) {
		return "index";
	}
	@RequestMapping(value="insertReview.do", method=RequestMethod.POST)
	public String insertBoard(Review review) {
		System.out.println("this is review : " + review);
		reviewService.add(review);
		return "redirect:listReview.do";
	}
	@RequestMapping(value="updateReview.do", method=RequestMethod.POST)
	public String updateBoard(Review review) {
		reviewService.update(review);
		System.out.println("review : "+review);
		return "redirect:listReview.do";
	}
	
	@RequestMapping(value="listReview.do", method=RequestMethod.GET)
	public String listReview(ReviewPageBean bean, Model model, NoticePageBean noticebean) {
		List<Review> list = reviewService.searchAll(bean);
		model.addAttribute("list", list);
		model.addAttribute("reviewPageBean", bean);
		model.addAttribute("reviewBoardContent", "review_board/listReview.jsp");
		
		System.out.println("NoticeBoardController.listBoard>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("NoticePageBean>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+noticebean);
		List<NoticeBoard> noticeList = noticeBoardService.searchAll(noticebean);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("noticePageBean", noticebean);
		model.addAttribute("noticeBoardContent", "notice_board/listBoard.jsp");
		
		List<Diet> dietList = dietService.searchAll();
		System.out.println("dietList : " + dietList);
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

	@RequestMapping(value="searchReviewBoard.do", method=RequestMethod.GET)
	public String searchBoard(int no, Model model) {
		model.addAttribute("reviewBoard", reviewService.search(no));
		model.addAttribute("reviewBoardContent", "review_board/searchBoard.jsp");
		return "index";
		
	}
	@RequestMapping(value="deleteReview.do", method=RequestMethod.POST)
	public String deleteReview(int rno){
		System.out.println("rno"+rno);
		reviewService.remove(rno);
		return "redirect:listReview.do";
	}
	

	@RequestMapping(value="test2.do", method=RequestMethod.GET)
	public String test(int rno, Model model) {
		model.addAttribute("reviewBoard", reviewService.search(rno));
		model.addAttribute("reviewBoardContent", "review_board/searchBoard.jsp");
		return "index";
		
	}


}
