package com.zerogroup.absolutezero;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class Settings extends Activity {
	
	CheckBox enableNotif, enableSms, enableMail; 
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		enableNotif = (CheckBox) findViewById(R.id.checkBox1);
		enableSms = (CheckBox) findViewById(R.id.checkBox2);
		enableMail = (CheckBox) findViewById(R.id.checkBox3);
	    
		// new class OnCheckedChangeListener!
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {
        
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					switch(buttonView.getId()) {
					    case R.id.checkBox1:
					    	Log.d("NOTIF:", "enabled");//enableNotif()
					    case R.id.checkBox2:
					    	Log.d("SMS:", "enabled");//enableSms()
					    case R.id.checkBox3:
					    	Log.d("MAIL:", "enabled");//enableMail()
					}
				} 
			}
        };
		
        // link between button and click on the button
        enableNotif.setOnCheckedChangeListener(listener);
		enableSms.setOnCheckedChangeListener(listener);
		enableMail.setOnCheckedChangeListener(listener);
	}

	// So that the activity doesn't destroy the state on screen rotation or on screen resize
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
