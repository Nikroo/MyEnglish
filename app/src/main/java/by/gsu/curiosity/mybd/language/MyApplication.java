package by.gsu.curiosity.mybd.language;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Locale;

import by.gsu.curiosity.mybd.R;

public class MyApplication extends Application {
	private SharedPreferences preferences;
	private Locale locale;
	private String lang;
	
	@Override
	public void onCreate() {
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		lang = preferences.getString("lang", "default");	
			if (lang.equals("default")) {lang=getResources().getConfiguration().locale.getCountry();}
			locale = new Locale(lang);
			Locale.setDefault(locale);
			Configuration config = new Configuration();
			config.locale = locale;
			Log.i("Lang change", "Locale=" + locale);
			getBaseContext().getResources().updateConfiguration(config, null);
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        locale = new Locale(lang);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, null);     
    }	
}
