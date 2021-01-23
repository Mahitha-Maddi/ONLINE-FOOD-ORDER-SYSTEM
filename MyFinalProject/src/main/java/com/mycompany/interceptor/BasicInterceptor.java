/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interceptor;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mahit
 */
public class BasicInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        
        HttpSession session=request.getSession(false);
        if(session==null){
             response.sendRedirect("redirect.jsp");
              return false;
        }
        try {
             Enumeration<String> paramNames=request.getParameterNames();
             while(paramNames.hasMoreElements()){
               String key=(String)paramNames.nextElement();
               String val=request.getParameter(key);
               if(xssCheck(val)){
                   response.sendRedirect("redirect.jsp");
              return false;
               }
             }
          
        } catch (Exception e) {

        }
            
        return true;
    }

    private boolean xssCheck(String value) {
        if (value != null) {
            return (value.matches("<script>") || value.matches("</script>"));
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
       //
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        //
    }
    

    
}
