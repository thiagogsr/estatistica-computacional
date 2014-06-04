package com.ec.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ec.R;
import com.ec.common.Util;

public class CalcActivity extends Activity {
	private ArrayList<Float> numbers;
	private Button btAdd;
	private Button btRun;
	private EditText number;
	private ListView numbersList;
	private TextView numbersListCount;
	private TextView numbersListInfo;
	private Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc);
		numbers = new ArrayList<Float>();
		btAdd = (Button) findViewById(R.id.btAdd);
		btRun = (Button) findViewById(R.id.btRun);
		number = (EditText) findViewById(R.id.number);
		numbersList = (ListView) findViewById(R.id.numbersList);
		numbersListCount = (TextView) findViewById(R.id.numbersListCount);
		numbersListInfo = (TextView) findViewById(R.id.numbersListInfo);
		toast = new Toast(getApplicationContext());
		
		btAdd.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Resources res = getResources();
				if (number.getText().toString().isEmpty()) {
					Util.makeDialog(CalcActivity.this, res.getString(R.string.titleDialogRequired), res.getString(R.string.numberRequired));
				} else {
					numbers.add(Float.parseFloat(number.getText().toString()));
					number.setText(null);
					reloadList();
					
					numbersList.setOnItemClickListener(new ListView.OnItemClickListener() {
						@Override
						public void onItemClick(android.widget.AdapterView<?> arg0, View arg1, int arg2, long arg3) {
							toast.cancel();
							Resources res = getResources();
							Float number = (Float) arg0.getItemAtPosition(arg2);
							toast = Toast.makeText(getApplicationContext(), String.format(res.getString(R.string.infoDeletedMsg), number.toString()), Toast.LENGTH_SHORT);
							toast.show();
							numbers.remove(number);
							reloadList();
						};
					});
				}
			}
		});
		
		btRun.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putSerializable("numbers", numbers);
				Intent intent = new Intent(v.getContext(), CalcResultActivity.class);
				intent.putExtras(bundle);
				v.getContext().startActivity(intent);
			}
		});
	}
	
	public void reloadList() {
		Resources res = getResources();
		int count = numbers.size();
		if (count > 0) {
			numbersListInfo.setVisibility(View.VISIBLE);
			numbersListCount.setVisibility(View.VISIBLE);
			numbersListCount.setText(res.getQuantityString(R.plurals.numbersOfItens, count, count));
			btRun.setEnabled(true);
		} else {
			numbersListInfo.setVisibility(View.INVISIBLE);
			numbersListCount.setVisibility(View.INVISIBLE);
			btRun.setEnabled(false);
		}
		ArrayAdapter<Float> arrayAdapter = new ArrayAdapter<Float>(CalcActivity.this, android.R.layout.simple_list_item_1, numbers);
		numbersList.setAdapter(arrayAdapter);
	}

}
