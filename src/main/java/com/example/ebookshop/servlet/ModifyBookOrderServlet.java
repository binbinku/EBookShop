package com.example.ebookshop.servlet;

import com.alibaba.fastjson.JSON;
import com.example.ebookshop.DB.DBHelper;
import com.example.ebookshop.entity.OrderUnit;
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

@WebServlet(name = "ModifyBookOrderServlet", value = "/ModifyBookOrderServlet")
public class ModifyBookOrderServlet extends HttpServlet
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
        List<OrderUnit> orderUnits = new ArrayList<>();

        String sendUnitStr =  "";

        boolean isGet = false;

        String data = StreamTools.ReadData(request.getReader());
        RecBookOrderUnit recBookOrderUnit = JSON.parseObject(data,RecBookOrderUnit.class);
        System.out.println(recBookOrderUnit.getType());
        boolean succ =false;
        switch (recBookOrderUnit.getType())
        {
            case "ADD":
                //添加
                succ = dbHelper.AddOrder(recBookOrderUnit.getData());
                break;
            case "MOD":
                //修改
                succ = dbHelper.ModifyBookOrder(recBookOrderUnit.getData());
                break;
            case "DEL":
                //删除
                succ = dbHelper.DeleteBookOrder(recBookOrderUnit.getData().getOid());
                break;
            case "GET-ALL":
                //获取全部
                isGet =true;
                orderUnits = dbHelper.GetAllBookOrder();
                sendUnitStr =  JSON.toJSONString(orderUnits);
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
class RecBookOrderUnit
{
    private String type;
    private OrderUnit data;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public OrderUnit getData()
    {
        return data;
    }

    public void setData(OrderUnit data)
    {
        this.data = data;
    }
}

