package com.mpc.productapplication;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
	
	ArrayList<user> userArray = new ArrayList<user>();  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLogin = (EditText) findViewById(R.id.login_input);
		mPassword = (EditText) findViewById(R.id.password_input);
		mSave = (CheckBox) findViewById(R.id.save_me);
		
		mLogIn = (Button) findViewById(R.id.logIn);
		mSignIn = (Button) findViewById(R.id.signin);
		
		mLogIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				user user = new user();

				user.setNikname(mLogin.getText().toString());
				user.setPassword(mPassword.getText().toString());
				ServerCommunicationTask task = new ServerCommunicationTask();
				task.execute(user);
				// CakeArray.add(cake);
				Intent openUserList = new Intent(MainActivity.this, UserListActivity.class);
				startActivity(openUserList);
				
				//String log = openUserList.login;
			}
			});
	}

	public static ArrayList<user> sortPrice(ArrayList<user> priceArray) {
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class ServerCommunicationTask extends AsyncTask<user, Void, String> {

		@Override
		protected String doInBackground(user... params) {
			user user = params[0];
			JSONObject json = new JSONObject();
			try {
				json.put("nikname", user.getNikname());
				json.put("brand", user.getPassword());
				Log.i("request", json.toString());
				doHttpRequest(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	}

	static void doHttpRequest(JSONObject requestParam) {
		final String SERVER_URL = "http://192.168.0.77:3001/adduser";
		URL url;
		try {
			url = new URL(SERVER_URL);
			String postString = requestParam.toString();

			HttpURLConnection urlConnection;
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-type",
					"application/json;charset = utf-8");
			urlConnection.setReadTimeout(10000);
			urlConnection.setConnectTimeout(15000);
			urlConnection.connect();

			OutputStream outPut = new BufferedOutputStream(
					urlConnection.getOutputStream());
			outPut.write(postString.getBytes());
			outPut.flush();
			int responseCode = urlConnection.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
