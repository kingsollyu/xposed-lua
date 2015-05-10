package com.sollyu.xposed.lua.command;

import com.sollyu.utils.LogUtils;

import org.keplerproject.luajava.JavaFunction;
import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;

/**
 * Created by Sollyu on 2015/5/10.
 * E-Mail: sollyu.king@foxmail.com
 */
public class OutPutDebugString extends JavaFunction
{
    public static OutPutDebugString self = null;

    /**
     * Constructor that receives a LuaState.
     *
     * @param L LuaState object associated with this JavaFunction object
     */
    public OutPutDebugString( LuaState L ) throws LuaException
    {
        super( L );
        register( "OutPutDebugString" );
    }

    public static void Init( LuaState mLuaState ) throws LuaException
    {
        OutPutDebugString.self = new OutPutDebugString( mLuaState );
    }

    @Override
    public int execute() throws LuaException
    {
        LogUtils.OutputDebugString( getParam ( -1 ).toString () );
        return 0;
    }
}
