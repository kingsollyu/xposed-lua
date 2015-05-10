package com.sollyu.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by king.sollyu on 15/2/27.
 */
public class HttpUtils
{
    public static void GetHtml(final String baseUrl, final List<BasicNameValuePair > params, final HttpUtilsCallBack httpUtilsCallBack)
    {
        new Thread ( new Runnable ()
        {
            @Override
            public void run ()
            {
                try
                {
                	String requestUrl = baseUrl;
                	if (params != null )
                		requestUrl = requestUrl + "?" + URLEncodedUtils.format ( params, HTTP.UTF_8 );
                	
                    HttpGet    httpGet      = new HttpGet ( requestUrl );
                    HttpClient httpClient   = new DefaultHttpClient ( );

                    HttpResponse httpResponse = httpClient.execute ( httpGet );
                    String resultString = EntityUtils.toString ( httpResponse.getEntity (), "utf-8" );
                    int    resultCode   = httpResponse.getStatusLine().getStatusCode();

                    httpUtilsCallBack.OnFinish ( httpResponse, resultCode, resultString );
                }
                catch ( Exception e )
                {
                    httpUtilsCallBack.OnError ( e );
                }
            }
        } ).start ();
    }

    public static HttpResponse GetHtml(final String baseUrl, final List<BasicNameValuePair > params) throws IOException
    {
        String requestUrl = baseUrl;
        if (params != null )
            requestUrl = requestUrl + "?" + URLEncodedUtils.format ( params, HTTP.UTF_8 );

        HttpGet    httpGet      = new HttpGet ( requestUrl );
        HttpClient httpClient   = new DefaultHttpClient ( );

        return httpClient.execute ( httpGet );
    }

    public static void PostHtml(final String baseUrl, final List<BasicNameValuePair > params, final HttpUtilsCallBack httpUtilsCallBack)
    {
        new Thread ( new Runnable ()
        {
            @Override
            public void run ()
            {
                try
                {
                    HttpPost httpPost     = new HttpPost ( baseUrl );
                    httpPost.setEntity ( new UrlEncodedFormEntity ( params, HTTP.UTF_8 ) );

                    HttpClient httpClient   = new DefaultHttpClient ( );

                    HttpResponse httpResponse = httpClient.execute ( httpPost );
                    String resultString = EntityUtils.toString ( httpResponse.getEntity (), "utf-8" );
                    int    resultCode   = httpResponse.getStatusLine().getStatusCode();

                    httpUtilsCallBack.OnFinish ( httpResponse, resultCode, resultString );
                }
                catch ( Exception e )
                {
                    httpUtilsCallBack.OnError ( e );
                }
            }
        } ).start ();
    }

    static public interface HttpUtilsCallBack
    {
        public void OnFinish( final HttpResponse httpResponse, int resultCode, String resultString );
        public void OnError( Exception e );
    }
}
