package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.BookCategory;
import com.example.ebookshop.entity.BookUnit;
import com.example.ebookshop.utils.CharacterTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetCategoryBooksServlet", value = "/GetCategoryBooksServlet")
public class GetCategoryBooksServlet extends HttpServlet
{
    private DBHelper dbHelper;
    @Override
    public void init() throws ServletException
    {
        super.init();
        dbHelper = new DBHelper();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CharacterTools.UnifiedCharacters(request,response,"application/json");

        String bcno =  request.getParameter("bcno");
        BookCategory bookCategory = new BookCategory();
        bookCategory.setBcno(Integer.parseInt(bcno));

        List<BookUnit> bookUnitList = dbHelper.GetACategoryBooks(bookCategory);

        String sendUnitStr =  JSON.toJSONString(bookUnitList);

        response.getWriter().write(sendUnitStr);

    }
}
