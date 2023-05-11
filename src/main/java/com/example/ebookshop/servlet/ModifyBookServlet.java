package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.BookUnit;
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

@WebServlet(name = "ModifyBookServlet", value = "/ModifyBookServlet")
public class ModifyBookServlet extends HttpServlet
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
        List<BookUnit> bookUnitList = new ArrayList<>();
        String sendUnitStr = "";
        boolean isGet =false;

        String data = StreamTools.ReadData(request.getReader());
        RecBookUnit recBookUnit = JSON.parseObject(data,RecBookUnit.class);
        System.out.println(recBookUnit.getType());

        boolean succ =false;
        switch (recBookUnit.getType())
        {
            case "ADD":
                //添加
                succ = dbHelper.AddBook(recBookUnit.getData());
                break;
            case "MOD":
                //修改
                succ = dbHelper.ModifyBook(recBookUnit.getData());
                break;
            case "DEL":
                //删除
                succ = dbHelper.DeleteBook(recBookUnit.getData().getBno());
                break;
            case "GET-ALL":
                //获取全部
                isGet = true;
                bookUnitList = dbHelper.GetAllBooks();
                System.out.println("获取全部书籍成功");
                break;
        }

        if(isGet)
        {
            sendUnitStr = JSON.toJSONString(bookUnitList);
            response.getWriter().write(sendUnitStr);
        }else
        {
            response.getWriter().println(succ);
        }
    }
}
class RecBookUnit
{
    private String type;
    private BookUnit data;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public BookUnit getData()
    {
        return data;
    }

    public void setData(BookUnit data)
    {
        this.data = data;
    }
}
