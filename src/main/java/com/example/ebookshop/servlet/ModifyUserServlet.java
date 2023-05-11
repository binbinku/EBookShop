package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.Admin;
import com.example.ebookshop.entity.User;
import com.example.ebookshop.utils.CharacterTools;
import com.example.ebookshop.utils.SessionTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "ModifyUserServlet", value = "/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet
{
    DBHelper dbHelper;
    @Override
    public void init() throws ServletException
    {
        dbHelper = new DBHelper();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CharacterTools.UnifiedCharacters(request,response,"application/json");
        //===============================================================================

        BufferedReader reader = request.getReader();
        String data = "";
        String s;
        while((s = reader.readLine())!=null)
        {
            data+=s;
        }
        System.out.println("收到用户数据："+data+"\n");
        String substring = data.substring(2, 5);
        System.out.println(substring);
        //===================================权限拦截=====================================
        HttpSession session = SessionTools.CreateOrReFreshSession(request, response);
        Admin admin = (Admin) session.getAttribute("Admin");

        if(admin!=null)
        {
            if(substring.equals("uid"))
            {
                System.out.println("[UserM]处理用户删除\n");
                RecUid recUid = JSON.parseObject(data,RecUid.class);
                System.out.println("UID:"+recUid.getUid());
                boolean succ = dbHelper.DeleteUser(recUid.getUid());
                response.getWriter().println(succ);
            }else
            {
                System.out.println("[UserM]处理用户修改\n");
                ReceiveModUserUnit modUserdata=JSON.parseObject(data,ReceiveModUserUnit.class);
                User mUser = modUserdata.getUser();
                System.out.println(mUser.getAccount());
                boolean succ = dbHelper.ModifyUser(mUser);
                response.getWriter().println(succ);
            }
        }else
        {
            response.getWriter().println(false);
        }



    }
}
class RecUid
{
    private int uid;

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }
}
 class ReceiveModUserUnit
{
    private User user;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}

