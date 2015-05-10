package com.sollyu.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by king.sollyu email: 191067617@qq.com.
 */
public class LogUtils
{
    public static Boolean enableOutputLogString = true;
    public static String OutputStringTAG = "=== AMAP ===";
    
    public static Boolean EnableOutputLogString ( Boolean enable )
    {
        if ( enable != null ) enableOutputLogString = enable;
        return enableOutputLogString;
    }
    
    /**
     * 输出一个调试信息
     * @param string
     */
    public static void OutputDebugString ( String string )
    {
        try
        {
            if ( enableOutputLogString )
            {
                android.util.Log.d ( OutputStringTAG, string );
            }
        }
        catch ( Exception e )
        {
            OutputDebugString ( e );
        }
    }
    
    /**
     * 输出一个异常信息
     * @param throwable
     */
    public static void OutputDebugString ( Throwable throwable )
    {
        if ( enableOutputLogString )
        {
        	android.util.Log.e ( OutputStringTAG, "==================== ERROR START ====================" );
            android.util.Log.e ( OutputStringTAG, StackTraceToString ( throwable ) );
            android.util.Log.e ( OutputStringTAG, "====================  ERROR END  ====================" );
        }
    }
    
    /**
     * 将一个错误异常装换成字符串
     * @param throwable
     * @return
     */
    public static String StackTraceToString ( Throwable throwable )
    {
        if ( throwable == null )
            return "";
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
    
    
    /**
     * 正常情况下打印对战信息
     * @param desc
     */
    public static void PrintFillInStackTrace(String desc)
    {
        RuntimeException e = new RuntimeException(desc);
        OutputDebugString ( e.fillInStackTrace() );
    }
    
	/**
	 * 打印日志时获取当前的程序文件名、行号、方法名 输出格式为：[FileName | LineNumber | MethodName]
	 * 
	 * @return
	 */
	public static String getFileLineMethod()
	{
		StackTraceElement traceElement = ((new Exception ( )).getStackTrace ( ))[1];
		StringBuffer toStringBuffer = new StringBuffer ( "[" ).append ( traceElement.getFileName ( ) ).append ( " | " ).append ( traceElement.getLineNumber ( ) ).append ( " | " ).append ( traceElement.getMethodName ( ) ).append ( "]" );
		return toStringBuffer.toString ( );
	}

	// 当前文件名
	public static String _FILE_()
	{
		StackTraceElement traceElement = ((new Exception ( )).getStackTrace ( ))[1];
		return traceElement.getFileName ( );
	}

	// 当前方法名
	public static String _FUNC_()
	{
		StackTraceElement traceElement = ((new Exception ( )).getStackTrace ( ))[1];
		return traceElement.getMethodName ( );
	}

	// 当前行号
	public static int _LINE_()
	{
		StackTraceElement traceElement = ((new Exception ( )).getStackTrace ( ))[1];
		return traceElement.getLineNumber ( );
	}

	// 当前时间
	public static String _TIME_()
	{
		Date now = new Date ( );
		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss.SSS" );
		return sdf.format ( now );
	}
}
