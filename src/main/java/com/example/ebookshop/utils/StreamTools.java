package com.example.ebookshop.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class StreamTools
{
    public static String ReadData(BufferedReader reader) throws IOException
    {
        String data = "";
        String s;
        while((s = reader.readLine())!=null)
        {
            data+=s;
        }

        return data;
    }
}
