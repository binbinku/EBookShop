package com.example.ebookshop.entity;

public class OrderUnit
{
    private int oid;

    private String confirmationtime;

    private int totalprice;

    private String address;

    private String telephone;

    public int getOid()
    {
        return oid;
    }

    public void setOid(int oid)
    {
        this.oid = oid;
    }

    public String getConfirmationtime()
    {
        return confirmationtime;
    }

    public void setConfirmationtime(String confirmationtime)
    {
        this.confirmationtime = confirmationtime;
    }

    public int getTotalprice()
    {
        return totalprice;
    }

    public void setTotalprice(int totalprice)
    {
        this.totalprice = totalprice;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }
}
