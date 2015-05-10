package com.sollyu.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by king.sollyu email: 191067617@qq.com.
 */
public class UtilsHelper
{
    public static void setDataConnectionState ( Context cxt, boolean state ) throws Exception
    {
        ConnectivityManager connectivityManager = ( ConnectivityManager ) cxt.getSystemService ( Context.CONNECTIVITY_SERVICE );
        Class connectivityManagerClz = connectivityManager.getClass ();
        Method method = connectivityManagerClz.getMethod ( "setMobileDataEnabled", new Class[] { boolean.class } );
        method.invoke ( connectivityManager, state );
    }

    public static void shareMultiplePictureToTimeLine ( Context context, File... files )
    {
        Intent intent = new Intent ();
        ComponentName comp = new ComponentName ( "com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI" );
        intent.setComponent ( comp );
        intent.setAction ( Intent.ACTION_SEND_MULTIPLE );
        intent.setType ( "image/*" );

        ArrayList< Uri > imageUris = new ArrayList< Uri > ();
        for ( File f : files )
        {
            imageUris.add ( Uri.fromFile ( f ) );
        }
        intent.putParcelableArrayListExtra ( Intent.EXTRA_STREAM, imageUris );

        context.startActivity ( intent );
    }

    public static void WriteFileToSD(String FilePath, String Context) throws Exception
    {
        File file = new File ( FilePath );
        if (file.exists ())
            file.delete ();
        file.createNewFile ();
        RandomAccessFile randomAccessFile = new RandomAccessFile ( file, "rw" );
        randomAccessFile.write ( Context.getBytes () );;
        randomAccessFile.close ();
    }

    /**
     * 判断是否有网络连接
     * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     * @param context
     * @return
     */
    public static boolean isNetworkConnected ( Context context )
    {
        if ( context != null )
        {
            ConnectivityManager mConnectivityManager = ( ConnectivityManager ) context.getSystemService ( Context.CONNECTIVITY_SERVICE );
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo ();
            if ( mNetworkInfo != null )
            {
                return mNetworkInfo.isAvailable () && mNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * 判断WIFI网络是否可用
     * @param context
     * @return
     */
    public static boolean isWifiConnected ( Context context )
    {
        if ( context != null )
        {
            ConnectivityManager mConnectivityManager = ( ConnectivityManager ) context.getSystemService ( Context.CONNECTIVITY_SERVICE );
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo ( ConnectivityManager.TYPE_WIFI );
            if ( mWiFiNetworkInfo != null )
            {
                return mWiFiNetworkInfo.isAvailable () && mWiFiNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     * @param context
     * @return
     */
    public static boolean isMobileConnected ( Context context )
    {
        if ( context != null )
        {
            ConnectivityManager mConnectivityManager = ( ConnectivityManager ) context.getSystemService ( Context.CONNECTIVITY_SERVICE );
            NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo ( ConnectivityManager.TYPE_MOBILE );
            if ( mMobileNetworkInfo != null )
            {
                return mMobileNetworkInfo.isAvailable () && mMobileNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * 获取当前网络连接的类型信息
     * @param context
     * @return
     */
    public static int getConnectedType ( Context context )
    {
        if ( context != null )
        {
            ConnectivityManager mConnectivityManager = ( ConnectivityManager ) context.getSystemService ( Context.CONNECTIVITY_SERVICE );
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo ();
            if ( mNetworkInfo != null && mNetworkInfo.isAvailable () )
            {
                return mNetworkInfo.getType ();
            }
        }
        return -1;
    }
}