package com.ita.edu.softserve.web;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
	public static final String LOGIN_ERROR_PAGE = "/login.html"; 
	
	/**
	 * Access denied page to return
	 */
	public static final String ACCESS_DENIED_PAGE = "accessDenied";
	
	/**
	 * Logout page to return
	 */
	public static final String LOGOUT_PAGE = "redirect:/j_spring_security_logout";
	
	/**
	 * Login cancel button request mapping
	 */
	public static final String LOGINCANCEL = "/logincancel";
	
	/**
	 * Login request mapping
	 */
	public static final String LOGIN = "/login";
	
	/**
	 * Access denied request mapping
	 */
	public static final String ACCESS_DENIED = "/accessDenied";
	
	/**
	 * Login success request mapping
	 */
	public static final String LOGINSUCCESS = "/loginsuccess";
	
	/**
	 * Logout request mapping
	 */
	public static final String LOGOUT = "/logout";	
	
	private static final Logger LOGGER = Logger
			.getLogger(LoginController.class);	
	
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
	 * Controller method for obtaining the referer login page in the case of errors  
	 */	
	private String getLoginReferer(String referer) {
		try {
			URL url = new URL(referer);			
			String path = url.getPath().substring(1);			
			int pos = path.indexOf('/');			
			path = path.substring(pos);			
			return path;			
		} catch (MalformedURLException e) {			
			e.printStackTrace();
			LOGGER.error(e);		
		}
		return referer;
	}
	
	/**
	 * Controller method for setting the referer page 
	 * and for redirecting to login page
	 * 	
	 * @param request
	 * @return login page
	 */
	@RequestMapping(value=LOGIN)
	public String login(HttpServletRequest request) {
		String referer = getRefererFromRequest(request);		
		if (!LOGIN_ERROR_PAGE.equals(getLoginReferer(referer))){		
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
	@RequestMapping(value=ACCESS_DENIED)
	public String accesserror() {		
		return ACCESS_DENIED_PAGE;
	}
	
	/**
	 * Controller method for redirecting to the referer page 
	 * after successful login	
	 * 
	 * @return referer page
	 */
	@RequestMapping(value=LOGINSUCCESS)
	public String loginsuccess(HttpServletRequest request) {  
		return "redirect:" + getRefererFromSession(request);
	}
	
	/**
	 * Controller method for logout 
	 */
	@RequestMapping(value=LOGOUT)
	public String logout() {	
		return LOGOUT_PAGE;
 	}

	/**
	 * Controller method for redirecting to the referer page 
	 * after cancel from login	
	 * 
	 * @return referer page
	 */
    @RequestMapping(value=LOGINCANCEL)
	public String logincancel(HttpServletRequest request) {    	
		return "redirect:" + getRefererFromSession(request);
	}
}