package com.example.ebookshop.utils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class CharacterTools
{

    public  static  void UnifiedCharacters(HttpServletRequest request, HttpServletResponse response, String contentType)
    {
        try
        {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            if(!contentType.equals(""))
            {
                response.setContentType(contentType+";");
            }
        } catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }

    }
}
