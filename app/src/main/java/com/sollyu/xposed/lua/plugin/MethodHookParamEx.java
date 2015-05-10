package com.sollyu.xposed.lua.plugin;

import de.robv.android.xposed.XC_MethodHook;

/**
 * Created by Sollyu on 2015/5/10.
 * E-Mail: sollyu.king@foxmail.com
 */
public class MethodHookParamEx
{
    public XC_MethodHook.MethodHookParam param;
    public MethodHookParamEx( XC_MethodHook.MethodHookParam param )
    {
        this.param = param;
    }

    public Object getArg(int nIndex)
    {
        return param.args[nIndex];
    }

    public Object getResult()
    {
        return this.param.getResult();
    }

    public void setResult( Object result )
    {
        this.param.setResult( result );
    }

    public Throwable getThrowable()
    {
        return this.param.getThrowable();
    }

    public boolean hasThrowable()
    {
        return this.param.hasThrowable();
    }

    public void setThrowable( Throwable throwable )
    {
        this.param.setThrowable( throwable );
    }

    public Object getResultOrThrowable() throws Throwable
    {
        return this.param.getResultOrThrowable();
    }
}
