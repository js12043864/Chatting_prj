package com.kakao;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
  KakaoAPI kakaoApi = new KakaoAPI();
  
  @RequestMapping(value="/login")
  public ModelAndView login(@RequestParam("code") String code, HttpSession session) {
	  ModelAndView mav = new ModelAndView();
	  String accessToken = kakaoApi.getAccessToken(code);
	  HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
	  System.out.println("login info : " + userInfo.toString());
	  if(userInfo.get("email") != null) {
		  session.setAttribute("userId" , userInfo.get("email"));
		  session.setAttribute("access_token", accessToken);
	  }
	  mav.addObject("userId", userInfo.get("email"));
	  mav.setViewName("index");
	  return mav;
  }
}
