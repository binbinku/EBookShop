package com.example.ebookshop.entity;

public class NoticeUnit
{
    private int nid;
    private String noticeTitle;
    private String noticeDate;
    private String noticeContent;

    public int getNid()
    {
        return nid;
    }

    public void setNid(int nid)
    {
        this.nid = nid;
    }

    public String getNoticeTitle()
    {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle)
    {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDate()
    {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate)
    {
        this.noticeDate = noticeDate;
    }

    public String getNoticeContent()
    {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent)
    {
        this.noticeContent = noticeContent;
    }
}
