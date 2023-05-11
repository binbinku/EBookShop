package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.BookCategory;
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

@WebServlet(name = "ModifyBookCategoryServlet", value = "/ModifyBookCategoryServlet")
public class ModifyBookCategoryServlet extends HttpServlet
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

        List<BookCategory> bookCategoryList = new ArrayList<>();
        String sendUnitStr = "";
        boolean isGet =false;

        String data = StreamTools.ReadData(request.getReader());

        RecModBookCategoryUnit recModBookCategoryUnit = JSON.parseObject(data,RecModBookCategoryUnit.class);

        System.out.println(recModBookCategoryUnit.getType());

        boolean succ =false;

        switch (recModBookCategoryUnit.getType())
        {
            case "ADD":
                //添加
                 succ = dbHelper.AddBookCategory(recModBookCategoryUnit.getData());
                break;
            case "MOD":
                //修改
                 succ = dbHelper.ModifyBookCategory(recModBookCategoryUnit.getData());
                break;
            case "DEL":
                //删除
                 succ = dbHelper.DeleteBookCategory(recModBookCategoryUnit.getData().getBcno());
                break;
            case "GET-ALL":
                //获取全部
                isGet =true;
                bookCategoryList =  dbHelper.GetAllCategorys();
                sendUnitStr = JSON.toJSONString(bookCategoryList);
                break;
        }
        if(isGet)
        {
            response.getWriter().write(sendUnitStr);

        }else
        {
            response.getWriter().println(succ);
        }
    }
}
class RecModBookCategoryUnit
{
    private String type;
    private BookCategory data;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public BookCategory getData()
    {
        return data;
    }

    public void setData(BookCategory data)
    {
        this.data = data;
    }
}
