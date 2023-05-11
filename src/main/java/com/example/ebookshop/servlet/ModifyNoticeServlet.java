package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.NoticeUnit;
import com.example.ebookshop.utils.CharacterTools;
import com.example.ebookshop.utils.StreamTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ModifyNoticeServlet", value = "/ModifyNoticeServlet")
public class ModifyNoticeServlet extends HttpServlet
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
        List<NoticeUnit> noticeUnits = new ArrayList<>();
        String sendStr = "";
        boolean isGet = false;

        String data = StreamTools.ReadData(request.getReader());
        RecNoticeUnit recNoticeUnit = JSON.parseObject(data,RecNoticeUnit.class);
        System.out.println(recNoticeUnit.getType());
        boolean succ =false;
        switch (recNoticeUnit.getType())
        {
            case "ADD":
                //添加
                succ = dbHelper.AddNotice(recNoticeUnit.getData());
                break;
            case "MOD":
                //修改
                succ = dbHelper.ModifyNotice(recNoticeUnit.getData());
                break;
            case "DEL":
                //删除
                succ = dbHelper.DeleteNotice(recNoticeUnit.getData().getNid());
                break;
            case "GET-ALL":
                //获取全部
                isGet = true;
                noticeUnits = dbHelper.GetAllNotice();
                sendStr = JSON.toJSONString(noticeUnits);
                break;
        }
        if(isGet)
        {
            response.getWriter().write(sendStr);
        }else
        {
            response.getWriter().println(succ);
        }
    }
}
class RecNoticeUnit
{
    private String type;
    private NoticeUnit data;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public NoticeUnit getData()
    {
        return data;
    }

    public void setData(NoticeUnit data)
    {
        this.data = data;
    }
}

