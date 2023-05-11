package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.Admin;
import com.example.ebookshop.entity.LoginUnit;
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

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet
{
    DBHelper dbHelper;

    public AdminServlet()
    {
        this.dbHelper = new DBHelper();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("[Admin]收到Post");

        CharacterTools.UnifiedCharacters(request,response,"application/json;charset=UTF-8");

        List<Admin> adminList = new ArrayList<>();
        Admin admin = new Admin();
        admin.setAdminAccount(request.getParameter("account"));
        admin.setAdminPassoWord(request.getParameter("password"));

        try
        {
            adminList = dbHelper.GetAdmin(admin);

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        if(adminList.size()<1)
        {
            return;
        }

        boolean matchSuc = false;

        for (Admin a : adminList)
        {
            if (a.getAdminPassoWord().equals(admin.getAdminPassoWord()))
            {
                System.out.println("[ADMIN]:账号密码匹配成功！");
                matchSuc = true;
                admin =a;
                break;
            }
        }

        HttpSession session = SessionTools.CreateOrReFreshSession(request, response);

        session.setAttribute("Admin",admin);

        LoginUnit loginUnit = new LoginUnit();

        loginUnit.setRedirectUrl("backstage.html");

        loginUnit.setLoginSucceeded(matchSuc);

        String sendStr = JSON.toJSONString(loginUnit);

        response.getWriter().write(sendStr);

    }
}
