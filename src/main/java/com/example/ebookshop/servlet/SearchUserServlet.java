package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.User;
import com.example.ebookshop.utils.CharacterTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchUserServlet", value = "/SearchUserServlet")
public class SearchUserServlet extends HttpServlet
{
    DBHelper dbHelper;
    @Override
    public void init() throws ServletException
    {
        dbHelper = new DBHelper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //===========================================================
        CharacterTools.UnifiedCharacters(request,response,"application/json");
        //===========================================================

        String searchAccount = request.getParameter("searchAccount");

        List<User> users = new ArrayList<>();
        if(searchAccount.equals(""))
        {
            users = dbHelper.GetAllUser();
        }else
        {
            users = dbHelper.GetUserByAccount(searchAccount);
        }

        String sendStr = JSON.toJSONString(users);

        response.getWriter().write(sendStr);

    }
}
