package com.mpc.productapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class UserListActivity extends Activity {
	//ListView mCakesListView;
	userAdapter mAdapter;
	ArrayList<user> userArray;
	public static String login;
	public static String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
		userArray = new ArrayList<user>();
		//mCakesListView = (ListView) findViewById(R.id.cake_list);
		mAdapter = new userAdapter(getApplicationContext());
		mAdapter.setUserArray(userArray);
		//mCakesListView.setAdapter(mAdapter);
		GetCakeTask task = new GetCakeTask();
		task.execute();
	}

	class GetCakeTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			mAdapter.notifyDataSetChanged();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			JSONArray result = doHttpRequest();
			try {
				for (int i = 0; i < result.length(); ++i) {
					JSONObject tmpJSON = result.getJSONObject(i);
					Log.i("current", tmpJSON.toString());
					user user = new user();
					//cake.setBrand(tmpJSON.getString("brand"));
					user.setName(tmpJSON.getString("name"));
					//cake.setType(tmpJSON.getString("type"));
					userArray.add(user);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	static JSONArray doHttpRequest() {
		final String SERVER_URL = "http://192.168.0.77:3001/getuser";
		URL url;
		JSONArray userJSONArray = null;
		try {
			url = new URL(SERVER_URL);
			HttpURLConnection urlConnection;
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = urlConnection.getInputStream();

			BufferedReader streamReader = new BufferedReader(
					new InputStreamReader(in, "UTF-8"));
			StringBuilder responseStrBuilder = new StringBuilder();
			String inputStr;
			while ((inputStr = streamReader.readLine()) != null) {
				responseStrBuilder.append(inputStr);
			}
			userJSONArray = new JSONArray(responseStrBuilder.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(userJSONArray);
		
		for(int i=0; i< userJSONArray.length(); ++i){
			JSONObject users;
			try {
				users = userJSONArray.getJSONObject(i);
				login = users.getString("nikname");
				password = users.getString("password");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			System.out.println(login);
	} 
		return userJSONArray;
	}
}
