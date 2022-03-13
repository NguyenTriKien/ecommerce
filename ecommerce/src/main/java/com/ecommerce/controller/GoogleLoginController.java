package com.ecommerce.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.dao.UserAccountDAO;
import com.ecommerce.entity.UserAccount;
import com.ecommerce.model.GooglePojo;
import com.ecommerce.util.GoogleUtils;

@Controller
public class GoogleLoginController {

	  @Autowired
	  private GoogleUtils googleUtils;
	  
	  @Autowired
	  private UserAccountDAO googleaccoutdao;
	  
	  private UserAccount gooleaccount;
	  
	    @RequestMapping(value = {"/login" })
	    public String login(@RequestParam(required = false) String message, final Model model) {
	      if (message != null && !message.isEmpty()) {
	        if (message.equals("logout")) {
	          model.addAttribute("message", "Logout!");
	        }
	        if (message.equals("error")) {
	          model.addAttribute("message", "Login Failed!");
	        }
	        if (message.equals("google_error")) {
	          model.addAttribute("message", "Login by Facebook Failed!");
	        }
	      }
	      return "login";
	    }
	    
	  @RequestMapping("/login-google")
	  public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
	    String code = request.getParameter("code");
	    
	    if (code == null || code.isEmpty()) {
	      return "redirect:/login?message=google_error";
	    }
	    String accessToken = googleUtils.getToken(code);
	    
	    GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
	    googleaccoutdao.saveGoogleAccount(googlePojo);
	    UserDetails userDetail = googleUtils.buildUser(googlePojo);
	    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
	        userDetail.getAuthorities());
	    //authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    return "redirect:/home";
	  }
	  
	  @RequestMapping("/home")
	  public String home() {
	    return "home";
	  }
}
