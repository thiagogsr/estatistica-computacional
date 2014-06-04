package com.ec.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ec.R;
import com.ec.common.Util;

public class ProbabilityActivity extends Activity {
	
	private Button btRun;
	private EditText sample;
	private EditText success;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_probability);
		btRun = (Button) findViewById(R.id.btRun);
		sample = (EditText) findViewById(R.id.sample);
		success = (EditText) findViewById(R.id.success);
		
		btRun.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Resources res = getResources();
				if (sample.getText().toString().isEmpty()) {
					Util.makeDialog(ProbabilityActivity.this, res.getString(R.string.titleDialogRequired), res.getString(R.string.sampleRequired));
				} else if (success.getText().toString().isEmpty()) {
					Util.makeDialog(ProbabilityActivity.this, res.getString(R.string.titleDialogRequired), res.getString(R.string.successRequired));
				} else {
					Bundle bundle = new Bundle();
					bundle.putInt("sample", Integer.parseInt(sample.getText().toString()));
					bundle.putFloat("success", Float.parseFloat(success.getText().toString()));
					Intent intent = new Intent(v.getContext(), ProbabilityResultActivity.class);
					intent.putExtras(bundle);
					v.getContext().startActivity(intent);
				}
			}
		});
	}

}
