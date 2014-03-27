package com.thiagogsr.estatisticacomputacional;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
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
		setContentView(R.layout.activity_main);
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
					makeDialog(MainActivity.this, res.getString(R.string.titleDialogRequired), res.getString(R.string.numberRequired));
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
				Intent intent = new Intent(v.getContext(), CalcActivity.class);
				intent.putExtras(bundle);
				v.getContext().startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static void makeDialog(Context context, String title, String msg) {
		AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
		builder1.setTitle(title);
		builder1.setMessage(msg);
		builder1.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
		        dialog.cancel();
		    }
		});
		builder1.show();
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
		ArrayAdapter<Float> arrayAdapter = new ArrayAdapter<Float>(MainActivity.this, android.R.layout.simple_list_item_1, numbers);
		numbersList.setAdapter(arrayAdapter);
	}

}
