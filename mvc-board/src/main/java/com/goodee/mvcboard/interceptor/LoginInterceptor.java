package com.goodee.mvcboard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


//로그인 처리를 하는 인터셉터
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // session 객체를 가져옴
    	HttpSession session = request.getSession();
        if (session.getAttribute("loginMember") == null) {
        	// 로그인이 되어 있지 않으면 home.jsp로 리다이렉트
        	response.sendRedirect("/home");
        	log.debug(session.getAttribute("loginMember")+"loginMember / LoginInterceptor");
            log.debug("preHandle 작동 interceptor login");
            return false;
        } else {
        	// 로그인이 되어 있으면 요청을 계속 진행
        	return true;
        }
    }
        
    
//    @Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//    	
//    	HttpSession session = request.getSession();
//    	if (session.getAttribute("loginMember") != null) {
//    		// 로그인된 사용자일 경우, 모델에 로그인된 아이디를 추가
//    		// 세션에 로그인 정보가 있다면, 해당 로그인 정보를 모델에 추가하여 화면에서 활용할 수 있도록 함
//            modelAndView.addObject("id", session.getAttribute("loginMember"));
//        }
//    	// 다음 인터셉터나 컨트롤러로 요청을 전달
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
}
  

 

