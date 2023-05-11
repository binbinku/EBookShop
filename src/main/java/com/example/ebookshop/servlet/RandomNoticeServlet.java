package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.NoticeUnit;
import com.example.ebookshop.utils.CharacterTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@WebServlet(name = "RandomNoticeServlet", value = "/RandomNoticeServlet")
public class RandomNoticeServlet extends HttpServlet
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
        //===============================================================================
        CharacterTools.UnifiedCharacters(request,response,"application/json");
        //===============================================================================
            List<NoticeUnit> noticeUnits = dbHelper.GetAllNotice();
            int i = new Random().nextInt(noticeUnits.size());
            String sendStr = JSON.toJSONString(noticeUnits.get(i));
            response.getWriter().write(sendStr);
    }
}
