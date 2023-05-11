package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.BookCategory;
import com.example.ebookshop.entity.BookUnit;
import com.example.ebookshop.utils.CharacterTools;
import com.example.ebookshop.utils.SessionTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookShopServlet", value = "/BookShopServlet")
public class BookShopServlet extends HttpServlet
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
        //===============================================================================
        CharacterTools.UnifiedCharacters(request,response,"application/json");
        //===============================================================================

        HttpSession session = SessionTools.CreateOrReFreshSession(request, response);

        //===============================================================================
            List<BookUnit> bookUnitList = dbHelper.GetAllBooks();
            List<BookCategory> bookCategoryList = dbHelper.GetAllCategorys();
            List<BookUnit> cartUnitList = (List<BookUnit>) session.getAttribute("Cart");

            SendUnit sendUnit = new SendUnit();

            sendUnit.setBookUnitList(bookUnitList);
            sendUnit.setBookCategoryList(bookCategoryList);
            sendUnit.setCartUnitList(cartUnitList);

            String sendUnitStr =  JSON.toJSONString(sendUnit);

        //===============================================================================
            response.getWriter().write(sendUnitStr);
    }

}
class SendUnit
{
    private List<BookUnit> bookUnitList;
    private List<BookCategory> bookCategoryList;

    private List<BookUnit> cartUnitList;


    public List<BookUnit> getBookUnitList()
    {
        return bookUnitList;
    }

    public void setBookUnitList(List<BookUnit> bookUnitList)
    {
        this.bookUnitList = bookUnitList;
    }

    public List<BookCategory> getBookCategoryList()
    {
        return bookCategoryList;
    }

    public void setBookCategoryList(List<BookCategory> bookCategoryList)
    {
        this.bookCategoryList = bookCategoryList;
    }

    public List<BookUnit> getCartUnitList()
    {
        return cartUnitList;
    }

    public void setCartUnitList(List<BookUnit> cartUnitList)
    {
        this.cartUnitList = cartUnitList;
    }
}