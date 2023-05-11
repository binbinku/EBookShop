package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.entity.BookUnit;
import com.example.ebookshop.utils.CharacterTools;
import com.example.ebookshop.utils.SessionTools;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RemoveCartServlet", value = "/RemoveCartServlet")
public class RemoveCartServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //===============================================================================
        CharacterTools.UnifiedCharacters(request,response,"application/json");
        //===============================================================================

        HttpSession session = SessionTools.CreateOrReFreshSession(request,response);

        String bno = request.getParameter("bno");

        List<BookUnit> cart = (List<BookUnit>) session.getAttribute("Cart");

        if(cart==null)
        {
            System.out.println("[PutCart]:创建购物车");
            cart = new ArrayList<>();
            session.setAttribute("Cart",cart);
        }else
        {
            BookUnit bookUnit = null;
            for(BookUnit u :cart)
            {
                if(bno.equals(u.getBno()+""))
                {
                    bookUnit = u;
                }
            }
            if(bookUnit!=null)
            cart.remove(bookUnit);

            session.setAttribute("Cart",cart);
        }

        String sendUnitStr =  JSON.toJSONString(cart);

        response.getWriter().write(sendUnitStr);
    }

}
