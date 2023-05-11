package com.example.ebookshop.entity;

public class Admin
{
    private int aid;
    private String adminAccount;
    private String adminPassoWord;
    private String email;

    public int getAid()
    {
        return aid;
    }

    public void setAid(int aid)
    {
        this.aid = aid;
    }

    public String getAdminAccount()
    {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount)
    {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassoWord()
    {
        return adminPassoWord;
    }

    public void setAdminPassoWord(String adminPassoWord)
    {
        this.adminPassoWord = adminPassoWord;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
