package com.sollyu.xposed.lua;

import com.sollyu.utils.LogUtils;
import com.sollyu.xposed.lua.command.HookMethodAfter;
import com.sollyu.xposed.lua.command.HookMethodBefore;
import com.sollyu.xposed.lua.command.HookStaticObjectField;
import com.sollyu.xposed.lua.command.OutPutDebugString;
import com.sollyu.xposed.lua.plugin.IO;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Sollyu on 2015/5/10.
 * E-Mail: sollyu.king@foxmail.com
 */
public class MainHook implements IXposedHookLoadPackage
{
    public static LuaState                        mLuaState          = LuaStateFactory.newLuaState();
    public static XSharedPreferences              xSharedPreferences = new XSharedPreferences("com.sollyu.xposed.lua");
    public static XC_LoadPackage.LoadPackageParam loadPackageParam   = null;

    @Override
    public void handleLoadPackage( XC_LoadPackage.LoadPackageParam loadPackageParam ) throws Throwable
    {
        MainHook.loadPackageParam = loadPackageParam;
        xSharedPreferences.makeWorldReadable();

        LogUtils.OutputStringTAG = "=== XPOSED LUA ===";
        mLuaState.openLibs();

        try
        {
            // 读取Lua文件的路径
            String luaScriptPath = xSharedPreferences.getString( "LuaScriptPath", null );
            if ( luaScriptPath != null && !luaScriptPath.isEmpty() )
            {
                // 读取Lua文件的内容
                String luaScript = IO.ReadStringFormFile( luaScriptPath );
                if ( luaScript != null && !luaScript.isEmpty() )
                {
                    if ( mLuaState.LdoString( luaScript ) != 0 )
                    {
                        throw new Exception( mLuaState.toString( 1 ) );
                    }
                    LoadAllCommand();
                    EnterJavaEntry();
                }
                else
                {
                    throw new Exception( "Lua Script is Empty." );
                }
            }
            else
            {
                throw new Exception( "Lua Script Path is Empty." );
            }
        }
        catch ( Exception e )
        {
            LogUtils.OutputDebugString( e );
            // XposedBridge.log( LogUtils.StackTraceToString( e ) );
        }
    }

    public void LoadAllCommand() throws LuaException
    {
        OutPutDebugString.Init( mLuaState );
        HookStaticObjectField.Init( mLuaState );
        HookMethodBefore.Init( mLuaState );
        HookMethodAfter.Init( mLuaState );
    }

    public void EnterJavaEntry()
    {
        // 调用入口函数
        mLuaState.resume ( 0 );
        mLuaState.getGlobal("JavaEntry");
        mLuaState.pushJavaObject( loadPackageParam );
        mLuaState.pcall(1, 0, 0);
    }
}
