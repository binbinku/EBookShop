package com.example.ebookshop.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionTools
{

    public static HttpSession CreateOrReFreshSession(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession(false);

        if(session==null)
        {
            System.out.println("[SessionTools:ERROR]:将创建一个新的Session");

            session = request.getSession(true);

            session.setMaxInactiveInterval(3600);

            Cookie cookie = new Cookie("JSESSIONID",session.getId());

            cookie.setMaxAge(3600);

            response.addCookie(cookie);

        }else
        {
            Cookie cookie = new Cookie("JSESSIONID",session.getId());
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
        }

        return session;

    }

}
