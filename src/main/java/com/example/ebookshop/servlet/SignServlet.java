package com.example.ebookshop.servlet;


import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.User;
import com.example.ebookshop.utils.CharacterTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SignServlet", value = "/SignServlet")
public class SignServlet extends HttpServlet
{
    DBHelper dbHelper;

    public SignServlet()
    {
        this.dbHelper = new DBHelper();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CharacterTools.UnifiedCharacters(request,response,"");

        User user = new User();
        user.setAccount(request.getParameter("account"));
        user.setPassword(request.getParameter("password"));

        PrintWriter printWriter = response.getWriter();

        List<User> userList = dbHelper.GetUser(user);

        if (userList.size()>0)
        {
            printWriter.println(false);
            return;
        }

        if (dbHelper.Sign(user))
        {
            printWriter.println(true);
        } else
        {
            printWriter.println(false);
        }

    }
}
