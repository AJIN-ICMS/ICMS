package ajin.sf.icms.controllers;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ajin.sf.icms.commons.ConvertPrintstackTrace;
import ajin.sf.icms.commons.ReturnCode;
import ajin.sf.icms.models.UserInfo;
import ajin.sf.icms.services.UserInfoService;
import ajin.sf.icms.vo.UserData;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private ConvertPrintstackTrace convertTrace = new ConvertPrintstackTrace();

	@Autowired
	private UserInfoService userInfoSer;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("login");
		 
		 return mv;
		/*
		 * Cookie[] getCookie = request.getCookies(); if(getCookie != null) { for(Cookie
		 * c : getCookie) { String name = c.getName(); String value = c.getValue();
		 * 
		 * String msg = name + ": " + value;
		 * 
		 * logger.error(msg);
		 * 
		 * String chkId = userInfoSer.getUserID(value); if(chkId.equals("admin")) {
		 * response.sendRedirect("/main");
		 * 
		 * } else { //response.sendRedirect("/login"); }
		 * 
		 * //boolean bChk = userInfoSer.getUserId(value); //if(bChk) {
		 * //mv.setViewName("main");
		 * 
		 * Cookie myCookie = new Cookie("USER", null); myCookie.setMaxAge(0); // 쿠키의
		 * expiration 타임을 0으로 하여 없앤다. myCookie.setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.
		 * response.addCookie(myCookie);
		 * 
		 * response.sendRedirect("/");
		 * 
		 * //response.sendRedirect("/main");
		 * 
		 * //break; //} else { //response.sendRedirect("/login");
		 * //mv.setViewName("login"); //} } }
		 */

		//response.sendRedirect("/login");

	}
	/*
	 * public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse
	 * response) throws Exception {
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * 
	 * Cookie[] getCookie = request.getCookies(); if(getCookie != null) { for(Cookie
	 * c : getCookie) { String name = c.getName(); String value = c.getValue();
	 * 
	 * String msg = name + ": " + value;
	 * 
	 * logger.error(msg);
	 * 
	 * boolean bChk = userInfoSer.getUserId(value); if(bChk) {
	 * mv.setViewName("main");
	 * 
	 * break; } else { mv.setViewName("login"); } } }
	 * 
	 * 
	 * return mv; }
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginSuccess(UserData data, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Integer reCode = ReturnCode.SERVER_ERROR;

		UserInfo user = userInfoSer.getUserInfo(data.getUserId(), data.getUserPw());

		try {
			reCode = userInfoSer.Login(data.getUserId(), data.getUserPw());
			if (reCode.equals(ReturnCode.ID_ERROR)) {

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('아이디를 확인해 주세요.'); history.go(-1);</script>");
				out.flush();
				out.close();

				response.sendRedirect("/login");
			} else if (reCode.equals(ReturnCode.PW_ERROR)) {

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('비밀번호를 확인해 주세요.'); history.go(-1);</script>");
				out.flush();
				out.close();

				response.sendRedirect("/login");
			} else if (reCode.equals(ReturnCode.OK)) {

				Cookie cookie = new Cookie("USER", data.getUserId());
				// cookie.setMaxAge(60*60*24); // 1Day
				cookie.setMaxAge(60); // 1Day
				response.addCookie(cookie);

				response.sendRedirect("/main");

			} else {

			}

		} catch (Exception e) {
			String msg = convertTrace.getPrintstackTrace(e);
			logger.error(msg);
		}
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView getMainPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();

		try {

			mv.setViewName("main");
		} catch (Exception e) {
			String msg = convertTrace.getPrintstackTrace(e);
			logger.error(msg);

		}

		return mv;
	}
}
 