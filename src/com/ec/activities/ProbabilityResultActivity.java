package com.ec.activities;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ec.R;
import com.ec.entities.Probability;

public class ProbabilityResultActivity extends Activity {
	
	private Integer sample;
	private Float success;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_probability_result);
		LinearLayout ll = (LinearLayout) findViewById(R.id.ProbabilityResultView);
		LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(getIntent().getExtras() != null) {
			Bundle bum = getIntent().getExtras();
			sample = bum.getInt("sample");
			success = bum.getFloat("success");
			
			Probability probability = new Probability(this.success, this.sample);
			for (int i = 0; i <= sample; i++) {
				String params = Uri.encode(probability.formule(i));
				String url = "http://latex.codecogs.com/gif.latex?" + params;

//				View inflatedView = mInflater.inflate(R.layout.probability_item, null);
//                ImageView image = (ImageView) inflatedView.findViewById(R.id.image);
//                TextView result = (TextView) inflatedView.findViewById(R.id.text);
//                image.setImageURI(Uri.parse(url));
//                result.setText(probability.run(i).toString());
//                ll.addView(inflatedView);
			}
		}
	}

}
