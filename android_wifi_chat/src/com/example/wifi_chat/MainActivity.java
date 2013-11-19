package com.example.wifi_chat;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText mLogin;
	private EditText mPassword;
	private CheckBox mSave;
	private Button mLogIn;
	private Button mSignIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLogin = (EditText) findViewById(R.id.login_input);
		mPassword = (EditText) findViewById(R.id.password_input);
		mSave = (CheckBox) findViewById(R.id.save_me);
		mLogIn = (Button) findViewById(R.id.logIn);
		mSignIn = (Button) findViewById(R.id.signin);
		mLogIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,ChatRoomActivity.class);
				startActivity(intent);
			}
		});
		mSignIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
				startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
