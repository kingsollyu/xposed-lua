package com.sollyu.xposed.lua.command;

import com.sollyu.xposed.lua.MainHook;

import org.keplerproject.luajava.JavaFunction;
import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;

import de.robv.android.xposed.XposedHelpers;

/**
 * Created by Sollyu on 2015/5/10.
 * E-Mail: sollyu.king@foxmail.com
 */
public class HookStaticObjectField extends JavaFunction
{
    private static HookStaticObjectField self = null;

    public static void Init( LuaState mLuaState ) throws LuaException
    {
        HookStaticObjectField.self = new HookStaticObjectField( mLuaState );
    }

    /**
     * Constructor that receives a LuaState.
     *
     * @param L LuaState object associated with this JavaFunction object
     */
    public HookStaticObjectField( LuaState L ) throws LuaException
    {
        super( L );

        register( "HookStaticObjectField" );
    }

    @Override
    public int execute() throws LuaException
    {
        LuaObject paramLuaObject1 = getParam( -1 );
        LuaObject paramLuaObject2 = getParam( -2 );
        LuaObject paramLuaObject3 = getParam( -3 );

        Class<?> classBuild = XposedHelpers.findClass ( paramLuaObject3.toString(), MainHook.loadPackageParam.classLoader );

        if (paramLuaObject1.isBoolean())
            XposedHelpers.setStaticBooleanField( classBuild, paramLuaObject2.toString(), paramLuaObject1.getBoolean() );
        else if (paramLuaObject1.isNumber())
            XposedHelpers.setStaticDoubleField( classBuild, paramLuaObject2.toString(), paramLuaObject1.getNumber() );
        else if (paramLuaObject1.isString())
            XposedHelpers.setStaticObjectField( classBuild, paramLuaObject2.toString(), paramLuaObject1.toString() );

        return 0;
    }
}
