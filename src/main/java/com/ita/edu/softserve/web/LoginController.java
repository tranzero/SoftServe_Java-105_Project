package com.ita.edu.softserve.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {		

	/**
	 * Default page to return
	 */
	public static final String DEFAULT_PAGE = "mainpage";
	
	/**
	 * Login page to return
	 */
	public static final String LOGIN_PAGE = "login"; 
	
	/**
	 * Login page to return in the error case
	 */
	public static final String LOGIN_ERROR_PAGE = "/SoftServe_Java-105/login.html"; 
	
	/**
	 * Access denied page to return
	 */
	public static final String ACCESS_DENIED_PAGE = "accessDenied";
	
	/**
	 * Logout page to return
	 */
	public static final String LOGOUT_PAGE = "redirect:/j_spring_security_logout";
	
		
	/**
	 * Controller method for obtaining the referer page  
	 */
	private String getRefererFromRequest(HttpServletRequest request){
		return request.getHeader("Referer");		
	}
	
	/**
	 * Controller method for obtaining the referer page from session  
	 */	
	private String getRefererFromSession(HttpServletRequest request){
		return (String) request.getSession().getAttribute("referer");		
	}

	/**
	 * Controller method for setting the referer page 
	 * and for redirecting to login page
	 * 	
	 * @param request
	 * @return login page
	 */
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request) {
		String referer = getRefererFromRequest(request);
		System.out.println(referer);
		if (!referer.contains(LOGIN_ERROR_PAGE)){		
			HttpSession session = request.getSession();
			session.setAttribute("referer", referer);
		}			
		return LOGIN_PAGE; 
	}
	
	/**
	 * Controller method for redirecting to the access denied page 
	 * 	
	 * @return access denied page
	 */
	@RequestMapping(value="/accessDenied")
	public String accesserror() {		
		return ACCESS_DENIED_PAGE;
	}
	
	/**
	 * Controller method for redirecting to the referer page 
	 * after successful login	
	 * 
	 * @return referer page
	 */
	@RequestMapping(value="/loginsuccess")
	public String loginsuccess(HttpServletRequest request) {  
		return "redirect:" + getRefererFromSession(request);
	}
	
	/**
	 * Controller method for logout 
	 */
	@RequestMapping(value="/logout")
	public String logout() {		
		return LOGOUT_PAGE;
 	}

	/**
	 * Controller method for redirecting to the referer page 
	 * after cancel from login	
	 * 
	 * @return referer page
	 */
    @RequestMapping(value="/logincancel")
	public String logincancel(HttpServletRequest request) {    	
		return "redirect:" + getRefererFromSession(request);
	}
}