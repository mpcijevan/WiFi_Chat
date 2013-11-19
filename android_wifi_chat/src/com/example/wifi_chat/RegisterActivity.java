package com.example.wifi_chat;

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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class RegisterActivity extends Activity {
	
	EditText mName;
	EditText mSurname;
	EditText mNikname;
	EditText mPassword;
	EditText mDate;
	EditText mEmail;
	
	RadioGroup mRadio;
	Button mRegister;
	
	user user = new user();
	ArrayList<user>usersArray = new ArrayList<user>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		   mName =(EditText)findViewById(R.id.edit_name);
	        mSurname = (EditText)findViewById(R.id.edit_surname);
	        mNikname = (EditText)findViewById(R.id.edit_nik_name);
	        mEmail = (EditText)findViewById(R.id.edit_email);
	        mPassword = (EditText)findViewById(R.id.edit_password);
	        mDate = (EditText) findViewById(R.id.edit_date);
	        
	        mRegister = (Button)findViewById(R.id.sign_in);
	        
	        RadioGroup mRadioGroup = (RadioGroup) findViewById(R.id.radio_group); 
	        int checkedRadioButton = mRadioGroup.getCheckedRadioButtonId();
	        String radioButtonSelected = "";
	        switch (checkedRadioButton) {
	          case R.id.radio_male : radioButtonSelected = "radio_male";
	                           	              break;
	          case R.id.radio_female : radioButtonSelected = "radio_female";
	        		                      break;
	        }
	        
	        mRegister.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					
					user.setName(mName.getText().toString());
					user.setSurname(mSurname.getText().toString());
					user.setNikname(mNikname.getText().toString());
					user.setEmail(mEmail.getText().toString());
					user.setPassword(mPassword.getText().toString());
					user.setDate(Integer.valueOf(mDate.getText().toString()));
					usersArray.add(user);
					
					ServerCommunicationTask task = new ServerCommunicationTask();
					task.execute(user);
					
				}
			});
	    }
	        class ServerCommunicationTask extends AsyncTask<user, Void, String>{

	    		@Override
	    		protected String doInBackground(user... params) {
	    			user user = params[0];
	    			JSONObject json = new JSONObject();
	    			try {
	    				json.put("name",user.getName() );
	    				json.put("surname", user.getSurname());
	    				json.put("nikname", user.getNikname());
	    				json.put("password",user.getPassword());
	    				json.put("email",user.getEmail());
	    				json.put("date",user.getDate());
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
	    		final String SERVER_URL = "http://192.168.0.11:3001/aduser";
	    		URL url;
	    		try {
	    			url = new URL(SERVER_URL);
	    			String postString = requestParam.toString();
	    			
	    			HttpURLConnection urlConnection;
	    			urlConnection = (HttpURLConnection)url.openConnection();
	    			urlConnection.setRequestMethod("POST");
	    			urlConnection.setRequestProperty("Content-type", "application/json;charset = utf-8");
	    			urlConnection.setReadTimeout(10000);
	    			urlConnection.setConnectTimeout(15000);
	    			urlConnection.connect();
	    			
	    			OutputStream outPut = new BufferedOutputStream(urlConnection.getOutputStream());
	    			outPut.write(postString.getBytes());
	    			outPut.flush();
	    			int responseCode = urlConnection.getResponseCode();
	    		} catch (MalformedURLException e) {
	    			e.printStackTrace();
	    		}catch (IOException e){
	    			e.printStackTrace();
	    		}
	    	}
	        
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
