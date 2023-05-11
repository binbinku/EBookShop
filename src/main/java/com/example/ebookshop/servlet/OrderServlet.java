package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.BookUnit;
import com.example.ebookshop.entity.OrderUnit;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet
{
    private DBHelper dbHelper;
    @Override
    public void init() throws ServletException
    {
        super.init();
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
        System.out.println("订单数据："+data);
        ReceiveBuyUnit receiveBuyUnit = JSON.parseObject(data,ReceiveBuyUnit.class);
        int totalprice = 0;

        for(BookUnit bu :receiveBuyUnit.getBuyUnits())
        {
            totalprice +=bu.getBprice();
        }

        HttpSession session = SessionTools.CreateOrReFreshSession(request, response);

        User user = (User)session.getAttribute("User");


        if(user!=null)
        {
            OrderUnit orderUnit = new OrderUnit();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            String formatDate = simpleDateFormat.format(new Date());
            orderUnit.setConfirmationtime(formatDate);
            orderUnit.setAddress(user.getAddress());
            orderUnit.setTelephone(user.getTelephone());
            orderUnit.setTotalprice(totalprice);
            boolean b = dbHelper.AddOrder(orderUnit);
            if(b)
            {
                System.out.println("创建订单成功");
            }else
            {
                System.out.println("创建订单失败");
            }

        }else
        {
            System.out.println("未登录");
        }

    }



}
 class ReceiveBuyUnit
{
    private List<BookUnit> buyUnits;

    public List<BookUnit> getBuyUnits()
    {
        return buyUnits;
    }

    public void setBuyUnits(List<BookUnit> buyUnits)
    {
        this.buyUnits = buyUnits;
    }
}