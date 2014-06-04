package com.ec.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ec.R;
import com.ec.entities.Probability;

public class ProbabilityResultActivity extends Activity {
	
	private Integer sample;
	private Float success;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout ll = (LinearLayout) findViewById(R.layout.activity_probability_result);
		
		if(getIntent().getExtras() != null) {
			Bundle bum = getIntent().getExtras();
			sample = bum.getInt("sample");
			success = bum.getFloat("success");
			
			LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			lp2.setMargins(0, 0, 0, 10);
			Probability probability = new Probability(this.success, this.sample);
			for (int i = 0; i <= sample; i++) {
				TextView tv = new TextView(this);
				tv.setLayoutParams(lp);
				tv.setText(probability.formule(i));
				tv.setTextAppearance(this, android.R.attr.textAppearanceMedium);
				tv.setTypeface(null, Typeface.BOLD);
				ll.addView(tv);
				
				TextView tv2 = new TextView(this);
				tv2.setLayoutParams(lp2);
				tv2.setText(probability.run(i).toString());
				ll.addView(tv2);
			}
		}
		
		setContentView(ll);
	}

}
