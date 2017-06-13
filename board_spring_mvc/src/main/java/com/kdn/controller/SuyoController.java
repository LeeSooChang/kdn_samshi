package com.kdn.controller;
 
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kdn.model.biz.DietService;
import com.kdn.model.biz.SuyoService;
import com.kdn.model.domain.Diet;
import com.kdn.model.domain.Suyo;
 
@Controller
public class SuyoController {
	
	@ExceptionHandler
	public ModelAndView handler(Exception e) {
		ModelAndView model = new ModelAndView("index");
		model.addObject("msg", e.getMessage()); 
		model.addObject("content", "ErrorHandler.jsp");
		return model;
		
	}
	
	@Autowired
	private SuyoService suyoService;
	
	@Autowired
	private DietService dietService;
	
	@RequestMapping(value="addSuyo.do", method=RequestMethod.GET)
	public String addSuyo(int dietNo, int mno, Model model, HttpSession session) {
		Suyo suyo = new Suyo(dietNo, mno);
		int findDietNo = dietNo;
		Diet findDiet = dietService.searchDiet(dietNo);
		Suyo isSuyo = null;
		int dietScode = findDiet.getScode();
		System.out.println("dietScode>>>>>>>>>>>>>>>>>>>>"+dietScode);
		switch (dietScode) {
		case 2:
			findDietNo++;
			System.out.println(suyo);
			Suyo findSuyo2 = new Suyo(findDietNo, mno);
			System.out.println(findSuyo2);
			isSuyo = suyoService.searchSuyo(findSuyo2);
			System.out.println(isSuyo);
			if(isSuyo == null){
				suyoService.add(suyo);
			} else {
				System.out.println("이미 한식 먹잖아요~");
			}
			break;
			
		case 3:
			findDietNo--;
			System.out.println(suyo);
			Suyo findSuyo3 = new Suyo(findDietNo, mno);
			System.out.println(findSuyo3);
			isSuyo = suyoService.searchSuyo(findSuyo3);
			System.out.println(isSuyo);
			if(isSuyo == null){
				suyoService.add(suyo);
			} else {
				System.out.println("이미 한식 먹잖아요~");
			}
			break;

		default:
			suyoService.add(suyo);
			break;
		}
		System.out.println(findDiet);
		List<Suyo> list = suyoService.getSuyoCountAll(); 
		int count = 0;
		for (Suyo suyo_ : list) {
			if(suyo_.getDietNo() == dietNo){
				count = suyo_.getSuyoCountAll();
				break;
			}
		}	
		
		if(count == 5){
			int scode = dietService.searchDiet(dietNo).getScode();
			session.setAttribute("win", scode);
			System.out.println(mno + " is Win ");
		}
		return "redirect:listWeeklyMenu.do";
	}
	
	@RequestMapping(value="minusSuyo.do", method=RequestMethod.GET)
	public String minusSuyo(int dietNo, int mno, Model model){
		Suyo suyo = new Suyo(dietNo, mno);
		suyoService.minus(suyo);
		return "redirect:listWeeklyMenu.do";
	}
 
	@RequestMapping(value="searchSuyo.do", method=RequestMethod.GET)
	public String searchBoard(Suyo suyo) {
		suyoService.searchSuyo(suyo);
		return "index";
	}
	
	@RequestMapping(value="getSuyoCount.do", method=RequestMethod.GET)
	public String getSuyoCount(int dietNo, int mno, Model model, HttpSession session){
		Suyo suyo = new Suyo(dietNo, mno);
		int suyoCount = suyoService.getSuyoCount(suyo);
		model.addAttribute("suyoCounte", suyoCount);
		session.setAttribute("suyo", suyoCount);
		return "index";
	}
}
