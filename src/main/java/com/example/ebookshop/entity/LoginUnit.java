package com.example.ebookshop.entity;

public class LoginUnit
{
    private String redirectUrl;
    private boolean loginSucceeded;

    public String getRedirectUrl()
    {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl)
    {
        this.redirectUrl = redirectUrl;
    }

    public boolean isLoginSucceeded()
    {
        return loginSucceeded;
    }

    public void setLoginSucceeded(boolean loginSucceeded)
    {
        this.loginSucceeded = loginSucceeded;
    }
}
