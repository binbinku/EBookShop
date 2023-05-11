package com.example.ebookshop.entity;

public class BookCategory
{
    private int bcno;//类型编号

    private String categoryName;

    public int getBcno()
    {
        return bcno;
    }

    public void setBcno(int bcno)
    {
        this.bcno = bcno;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
}
