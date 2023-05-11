package com.example.ebookshop.servlet;
import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.LoginUnit;
import com.example.ebookshop.entity.User;
import com.example.ebookshop.utils.CharacterTools;
import com.example.ebookshop.utils.SessionTools;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet
{
    DBHelper dbHelper;

    public LoginServlet()
    {
        this.dbHelper = new DBHelper();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("收到Post");
        CharacterTools.UnifiedCharacters(request,response,"application/json;charset=UTF-8");

        List<User> userList = new ArrayList<>();

        boolean rem = new Boolean(request.getParameter("rem"));

        User user = new User();
        user.setAccount(request.getParameter("account"));
        user.setPassword(request.getParameter("password"));

        try
        {
             userList = dbHelper.GetUser(user);

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        if(userList.size()<1)
        {
            return;
        }

        boolean matchSuc = false;

        for (User u : userList)
        {
            if (u.getPassword().equals(user.getPassword()))
            {
                System.out.println("[Login]:账号密码匹配成功！");

                Cookie account = new Cookie("account", u.getAccount());
                Cookie password = new Cookie("password", u.getPassword());

                if (rem)
                {
                    account.setMaxAge(60 * 10);
                    password.setMaxAge(60 * 10);
                } else
                {
                    account.setMaxAge(0);
                    password.setMaxAge(0);
                }

                response.addCookie(account);
                response.addCookie(password);

                matchSuc = true;
                user =u;
                break;

            }
        }
        //将该次会话的用户信息保存到服务端
        HttpSession session = SessionTools.CreateOrReFreshSession(request, response);
        session.setAttribute("User",user);

        //向客户端回传数据进行跳转
        LoginUnit loginUnit = new LoginUnit();
        loginUnit.setRedirectUrl("shop.html");
        loginUnit.setLoginSucceeded(matchSuc);
        String sendStr = JSON.toJSONString(loginUnit);
        response.getWriter().write(sendStr);

    }
}
