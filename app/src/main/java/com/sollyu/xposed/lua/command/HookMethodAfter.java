package com.sollyu.xposed.lua.command;

import com.sollyu.xposed.lua.MainHook;
import com.sollyu.xposed.lua.plugin.MethodHookParamEx;

import org.keplerproject.luajava.JavaFunction;
import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by Sollyu on 2015/5/10.
 * E-Mail: sollyu.king@foxmail.com
 */
public class HookMethodAfter extends JavaFunction
{
    private static HookMethodAfter self = null;

    public static void Init( LuaState mLuaState ) throws LuaException
    {
        HookMethodAfter.self = new HookMethodAfter( mLuaState );
    }

    /**
     * Constructor that receives a LuaState.
     *
     * @param L LuaState object associated with this JavaFunction object
     */
    public HookMethodAfter( LuaState L ) throws LuaException
    {
        super( L );
        register( "HookMethodAfter" );
    }

    @Override
    public int execute() throws LuaException
    {
        int nTop = -1 * L.getTop();
        int nHookParamCount = -1 * nTop - 3;

        final LuaObject callBackFunction = getParam( -1 );
        LuaObject hookClassName = getParam( nTop + 1 );
        LuaObject hookMethodName = getParam( nTop + 2 );

        Object[] objects = new Object[nHookParamCount];

        int nTemp = 0;
        for ( int i=nTop + 3; i< -1; i++ )
        {
            objects[nTemp++] = XposedHelpers.findClass( getParam( i ).toString(), MainHook.loadPackageParam.classLoader );
        }

        objects[nTemp] = new XC_MethodHook()
        {
            @Override
            protected void afterHookedMethod( MethodHookParam param ) throws Throwable
            {
                MethodHookParamEx methodHookParamEx = new MethodHookParamEx(param);
                if (callBackFunction.isFunction())
                {
                    if ( callBackFunction.call( new Object[]{ methodHookParamEx } ).equals( true ) )
                    {
                        super.afterHookedMethod( methodHookParamEx.param );
                    }
                }
            }
        };

        XposedHelpers.findAndHookMethod( hookClassName.toString(), MainHook.loadPackageParam.classLoader, hookMethodName.toString(), objects );

        return 0;
    }
}
