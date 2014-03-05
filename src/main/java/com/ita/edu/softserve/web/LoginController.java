package com.ita.edu.softserve.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {		

	/**
	 * Default page to return
	 */
	public static final String DEFAULT_PAGE = "mainpage";	
	
	/**
	 * Page to return after login
	 */
    private String referer = DEFAULT_PAGE;  
    
    public String getReferer() {
		return referer;
	}
	
	public void setReferer(String referer) {
		this.referer = referer;
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
		if (getReferer().equals(DEFAULT_PAGE)){
			setReferer(request.getHeader("Referer"));
		}			
		return "login"; 
	}
	
	/**
	 * Controller method for redirecting to the access denied page 
	 * 	
	 * @return access denied page
	 */
	@RequestMapping(value="/accessDenied")
	public String accesserror() {		
		return "accessDenied";
	}
	
	/**
	 * Controller method for redirecting to the referer page 
	 * after successful login	
	 * 
	 * @return referer page
	 */
	@RequestMapping(value="/loginsuccess")
	public String loginsuccess() {  
		return "redirect:" + getReferer();
	}
	
	/**
	 * Controller method for logout 
	 */
	@RequestMapping(value="/logout")
	public String logout() {		
		return "redirect:/j_spring_security_logout";
 	}

	/**
	 * Controller method for redirecting to the referer page 
	 * after cancel from login	
	 * 
	 * @return referer page
	 */
    @RequestMapping(value="/logincancel")
	public String logincancel() {    	
		return "redirect:" + getReferer();
	}
}