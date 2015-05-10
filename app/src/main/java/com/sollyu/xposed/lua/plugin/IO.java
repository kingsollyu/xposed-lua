package com.sollyu.xposed.lua.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Sollyu on 2015/5/10.
 */
public class IO
{
    /**
     * 读取TXT文本
     * @param filePath 文件路径
     * @return 文本内容
     * @throws IOException
     */
    public static String ReadStringFormFile ( String filePath ) throws IOException
    {
        File file = new File ( filePath );

        BufferedReader bufReader = new BufferedReader ( new FileReader( file ) );
        String line = "";
        String Result = "";
        while ( ( line = bufReader.readLine () ) != null )
            Result += line + "\r\n";
        return Result;
    }
}
