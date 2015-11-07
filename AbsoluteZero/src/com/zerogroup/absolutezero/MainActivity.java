package com.zerogroup.absolutezero;

import com.zerogroup.applibrary.SmsOperator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//At first run, SmsOperator is Enabled
		SmsOperator op = new SmsOperator();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent settings = new Intent(this, Settings.class); // starting Settings activity
			startActivity(settings);
		}
		return super.onOptionsItemSelected(item);
	}
}
