package com.kdn.controller;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kdn.model.biz.CounterService;
import com.kdn.model.biz.DietService;
import com.kdn.model.biz.EventService;
import com.kdn.model.biz.SuyoService;
import com.kdn.model.domain.Counter;
import com.kdn.model.domain.Diet;
import com.kdn.model.domain.Event;
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
	
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping(value="addSuyo.do", method=RequestMethod.GET)
	public String addSuyo(int dietNo, int mno, Model model, HttpSession session, 
						HttpServletResponse response, HttpServletRequest request) {
		
		Event findEvent = eventService.search(mno); 
		
		Suyo suyo = new Suyo(dietNo, mno);
		int findDietNo = dietNo;
		Diet findDiet = dietService.searchDiet(dietNo);
		
		//카운터 얻어오기
		int count = 0;
		String date = findDiet.getDietDate();
		Counter counter = counterService.search(date);
		int diffTwoDays = diffDays(dietNo);
		
		int dietScode = findDiet.getScode();
		Suyo isSuyo = suyoService.searchSuyo(suyo);
		Suyo isAnotherSuyoExist = null;
		Suyo toFindSuyo = null;
		
		
		if (diffTwoDays == -1) {
			
			switch (dietScode) {
			case 2:
				findDietNo++;
				toFindSuyo = new Suyo(findDietNo, mno);
				isAnotherSuyoExist = suyoService.searchSuyo(toFindSuyo);
				if(isAnotherSuyoExist == null){
					if (isSuyo == null) {
						suyoService.add(suyo);
						if(findEvent == null){
							eventService.add(mno);
							counter.setIcnt(counter.getIcnt() + 1);
							try {
								response.setContentType("text/html; charset=UTF-8");
								PrintWriter writer = null;
								writer = response.getWriter();
								writer.println("<script type='text/javascript'>");
								writer.println("alert('식사 이벤트에 참여 하셨습니다.');");
								writer.println("history.go(-1);");
								writer.println("</script>");
								writer.flush();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else {
						try {
							response.setContentType("text/html; charset=UTF-8");
							 PrintWriter writer = response.getWriter();
						     writer.println("<script type='text/javascript'>");
						     writer.println("alert('이미 예상 식사인원에 포함되어 있습니다.');");
						     writer.println("history.go(-1);");
						     writer.println("</script>");
						     writer.flush();
						     return "index";
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					try {
						 response.setContentType("text/html; charset=UTF-8");
						 PrintWriter writer = response.getWriter();
					     writer.println("<script type='text/javascript'>");
					     writer.println("alert('한식을 이미 선택하셨습니다. 한식을 취소하고 다시 선택해주세요.');");
					     writer.println("history.go(-1);");
					     writer.println("</script>");
					     writer.flush();
					     return "index";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
				
			case 3:
				findDietNo--;
				toFindSuyo = new Suyo(findDietNo, mno);
				isAnotherSuyoExist = suyoService.searchSuyo(toFindSuyo);
				if(isAnotherSuyoExist == null){
					if (isSuyo == null) {
						suyoService.add(suyo);
						if(findEvent == null){
							eventService.add(mno);
							counter.setHcnt(counter.getHcnt() + 1);
							try {
								response.setContentType("text/html; charset=UTF-8");
								PrintWriter writer = null;
								writer = response.getWriter();
								writer.println("<script type='text/javascript'>");
								writer.println("alert('식사 이벤트에 참여 하셨습니다.');");
								writer.println("history.go(-1);");
								writer.println("</script>");
								writer.flush();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else {
						try {
							response.setContentType("text/html; charset=UTF-8");
							 PrintWriter writer = response.getWriter();
						     writer.println("<script type='text/javascript'>");
						     writer.println("alert('이미 예상 식사인원에 포함되어 있습니다.');");
						     writer.println("history.go(-1);");
						     writer.println("</script>");
						     writer.flush();
						     return "index";
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					try {
						response.setContentType("text/html; charset=UTF-8");
						 PrintWriter writer = response.getWriter();
					     writer.println("<script type='text/javascript'>");
					     writer.println("alert('일품을 이미 선택하셨습니다. 일품을 취소하고 다시 선택해주세요.');");
					     writer.println("history.go(-1);");
					     writer.println("</script>");
					     writer.flush();
					     return "index";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;

			default:
				System.out.println("아침 아니면 저녁");
				if(findEvent == null){
					eventService.add(mno);
					try {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter writer = null;
						writer = response.getWriter();
						writer.println("<script type='text/javascript'>");
						writer.println("alert('식사 이벤트에 참여 하셨습니다.');");
						writer.println("history.go(-1);");
						writer.println("</script>");
						writer.flush();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("이벤트 처리 완료");
				
				isSuyo = suyoService.searchSuyo(suyo);
				if (isSuyo == null) {
					suyoService.add(suyo);
					if(findEvent == null){
						if(dietScode == 1){
							System.out.println("아침누르고 이벤트");
							counter.setMcnt(counter.getMcnt() + 1);
							System.out.println("이벤트 카운트 올리고");
						}
						else{
							System.out.println("저녁누르고 이벤트");
							counter.setEcnt(counter.getEcnt() + 1);
							System.out.println("이벤트 카운트 올리고");
						}
					}
				} else {
					try {
						response.setContentType("text/html; charset=UTF-8");
						 PrintWriter writer = response.getWriter();
					     writer.println("<script type='text/javascript'>");
					     writer.println("alert('이미 예상 식사인원에 포함되어 있습니다.');");
					     writer.println("history.go(-1);");
					     writer.println("</script>");
					     writer.flush();
					     return "index";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			}
			
		} else if (diffTwoDays == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('오늘 식사 인원 집계가 완료되었습니다.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return "index";
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (diffTwoDays < -1) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('내일 날짜만 수정 가능합니다.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return "index";
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (diffTwoDays > 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('지나간 날짜는 수정할 수 없습니다.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return "index";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		counterService.update(counter);
		int tCount = counter.getIcnt() + counter.getHcnt() + counter.getEcnt() + counter.getMcnt();
		
		if(tCount == 100){
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = null;
				writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('당첨되셨습니다 . 축하합니다.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return "index";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:listWeeklyMenu.do";
		
	}
	
	@RequestMapping(value="minusSuyo.do", method=RequestMethod.GET)
	public String minusSuyo(int dietNo, int mno, Model model, HttpServletRequest request, HttpServletResponse response){
		
		Suyo suyo = new Suyo(dietNo, mno);
		Suyo isSuyo = null;
		isSuyo = suyoService.searchSuyo(suyo);
		
		int diffTwoDays = diffDays(dietNo);
		Diet findDiet = dietService.searchDiet(dietNo);
		int dietScode = findDiet.getScode();
		
		
		int findDietNo = dietNo;
		Suyo anotherSuyoExist = null;
		Suyo toFindSuyo=null;
		
		if (diffTwoDays == -1) {
			
			switch (dietScode) {
			case 2:
				
				findDietNo++;
				toFindSuyo = new Suyo(findDietNo, mno);
				anotherSuyoExist = suyoService.searchSuyo(toFindSuyo);
				
				if (anotherSuyoExist == null) {
					
					if (isSuyo != null) {
						suyoService.minus(suyo);
					} else {
						try {
							response.setContentType("text/html; charset=UTF-8");
							PrintWriter writer = response.getWriter();
							writer.println("<script type='text/javascript'>");
							writer.println("alert('예상 식사 인원에 포함되어있지 않아 해당 선택이 불가합니다.');");
							writer.println("history.go(-1);");
							writer.println("</script>");
							writer.flush();
							return "index";
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				} else {
					try {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter writer = response.getWriter();
						writer.println("<script type='text/javascript'>");
						writer.println("alert('한식을 선택하셨습니다.');");
						writer.println("history.go(-1);");
						writer.println("</script>");
						writer.flush();
						return "index";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				break;
				
			case 3:
				
				findDietNo--;
				toFindSuyo = new Suyo(findDietNo, mno);
				anotherSuyoExist = suyoService.searchSuyo(toFindSuyo);
				if (anotherSuyoExist == null) {
					
					if (isSuyo != null) {
						suyoService.minus(suyo);
					} else {
						try {
							response.setContentType("text/html; charset=UTF-8");
							PrintWriter writer = response.getWriter();
							writer.println("<script type='text/javascript'>");
							writer.println("alert('예상 식사 인원에 포함되어있지 않아 해당 선택이 불가합니다.');");
							writer.println("history.go(-1);");
							writer.println("</script>");
							writer.flush();
							return "index";
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				} else {
					try {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter writer = response.getWriter();
						writer.println("<script type='text/javascript'>");
						writer.println("alert('일품을 선택하셨습니다.');");
						writer.println("history.go(-1);");
						writer.println("</script>");
						writer.flush();
						return "index";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				break;

			default:
				
				if (isSuyo != null) {
					suyoService.minus(suyo);
				} else {
					try {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter writer = response.getWriter();
						writer.println("<script type='text/javascript'>");
						writer.println("alert('예상 식사 인원에 포함되어있지 않아 해당 선택이 불가합니다.');");
						writer.println("history.go(-1);");
						writer.println("</script>");
						writer.flush();
						return "index";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				break;
			}
			
			if (isSuyo != null) {
				suyoService.minus(suyo);
			} else {
				try {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script type='text/javascript'>");
					writer.println("alert('예상 식사 인원에 포함되어있지 않아 해당 선택이 불가합니다.');");
					writer.println("history.go(-1);");
					writer.println("</script>");
					writer.flush();
					return "index";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} else if (diffTwoDays == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('오늘 식사 인원 집계가 완료되었습니다.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return "index";
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (diffTwoDays < -1) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('내일 날짜만 수정 가능합니다.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return "index";
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (diffTwoDays > 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script type='text/javascript'>");
				writer.println("alert('지나간 날짜는 수정할 수 없습니다.');");
				writer.println("history.go(-1);");
				writer.println("</script>");
				writer.flush();
				return "index";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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
	
	
	public int diffDays(int dietNo){
		
		Diet findDiet = dietService.searchDiet(dietNo);
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date toFindToday = new Date();
		String findToday = transFormat.format(toFindToday);
		
		try {
			Date dietDate = transFormat.parse(findDiet.getDietDate());
			Date today = transFormat.parse(findToday);
			
			long diff = today.getTime() - dietDate.getTime();
		    long diffDays = diff / (24 * 60 * 60 * 1000);
			
			int returnNum = (int)diffDays;
			
			return returnNum;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
