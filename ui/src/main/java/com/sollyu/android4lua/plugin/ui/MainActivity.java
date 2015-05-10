package com.sollyu.android4lua.plugin.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( this );
        sharedPreferences.edit().putString( "LuaScriptPath", Environment.getExternalStorageDirectory().getAbsolutePath() + "/xposed/xposed.lua" ).apply();
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings )
        {
            startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( "http://www.sollyu.com" ) ) );
            return true;
        }

        return super.onOptionsItemSelected( item );
    }
}
