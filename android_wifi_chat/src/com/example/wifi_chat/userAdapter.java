package com.mpc.productapplication;


import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class userAdapter extends BaseAdapter{

	private ArrayList<user> userArray = new ArrayList<user>();
	private LayoutInflater mInflater;
	
	public userAdapter(Context context) {
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setUserArray(ArrayList<user> array){
		this.userArray = array;
	}
	
	@Override
	public int getCount() {
		return userArray.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View converView, ViewGroup parent) {
		View row = converView;
		
		if(row == null){
			row = mInflater.inflate(R.layout.item_user_list, parent,false);
		}
		
		TextView name = (TextView) row.findViewById(R.id.row_name);
		TextView type  = (TextView) row.findViewById(R.id.row_type);
		TextView brand = (TextView)row.findViewById(R.id.row_brand);
		
		user user = userArray.get(position);
		name.setText(user.getNikname());
		//type.setText(cake.getType());
		//brand.setText(cake.getBrand());
		
		return row;
	}

}
