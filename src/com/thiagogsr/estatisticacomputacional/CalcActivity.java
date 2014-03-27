package com.thiagogsr.estatisticacomputacional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class CalcActivity extends Activity {
	
	private ArrayList<Float> numbers;
	private Button btBack;
	private TextView orderedNumbers;
	private TextView maximumNumber;
	private TextView minimumNumber;
	private TextView mode;
	private TextView median;
	private TextView firstQuartile;
	private TextView thirdQuartile;
	private TextView upperLimit;
	private TextView lowerLimit;
	private TextView amplitude;
	private TextView variance;
	private TextView standardDeviation;
	private TextView coefficientOfVariation;
	private TextView interquartileRange;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc);
		
		btBack = (Button) findViewById(R.id.btBack);
		orderedNumbers = (TextView) findViewById(R.id.orderedNumbers);
		maximumNumber = (TextView) findViewById(R.id.maximumNumber);
		minimumNumber = (TextView) findViewById(R.id.minimumNumber);
		mode = (TextView) findViewById(R.id.mode);
		median = (TextView) findViewById(R.id.median);
		firstQuartile = (TextView) findViewById(R.id.firstQuartile);
		thirdQuartile = (TextView) findViewById(R.id.thirdQuartile);
		upperLimit = (TextView) findViewById(R.id.upperLimit);
		lowerLimit = (TextView) findViewById(R.id.lowerLimit);
		amplitude = (TextView) findViewById(R.id.amplitude);
		variance = (TextView) findViewById(R.id.variance);
		standardDeviation = (TextView) findViewById(R.id.standardDeviation);
		coefficientOfVariation = (TextView) findViewById(R.id.coefficientOfVariation);
		interquartileRange = (TextView) findViewById(R.id.interquartileRange);
		
		if(getIntent().getExtras() != null){
			Bundle bum = getIntent().getExtras();
			numbers =  (ArrayList<Float>) bum.getSerializable("numbers");
			
			orderedNumbers.setText(getOrderedNumbers());
			maximumNumber.setText(getMaximumNumber());
			minimumNumber.setText(getMinimumNumber());
			mode.setText(getMode());
			median.setText(getMedian());
			firstQuartile.setText(getFirstQuartile());
			thirdQuartile.setText(getThirdQuartile());
			upperLimit.setText(getUpperLimit());
			lowerLimit.setText(getLowerLimit());
			amplitude.setText(getAmplitude());
			variance.setText(getVariance());
			standardDeviation.setText(getStandardDeviation());
			coefficientOfVariation.setText(getCoefficientOfVariation());
			interquartileRange.setText(getInterquartileRange());
		}
	}

	private String getOrderedNumbers() {
		int i = 0;
		ArrayList<Float> unsortNumbers = numbers;
		Collections.sort(unsortNumbers);
		int size = unsortNumbers.size();
		
		String result = "[";
		for (Float n: unsortNumbers) {
			result = n.toString();
			
			if (i < size) {
				result = ", ";
			}
			
			i++;
		}
		result = "]";
		
		return result;
	}

	private String getMaximumNumber() {
	  Float max = 0f;

	  for (Float n: numbers) {
		  if (n > max) {
			  max = n;
		  }
	  }
	  
	  return max.toString();
	}

	private String getMinimumNumber() {
	  Float min = numbers.get(0);
	  
	  for (Float n: numbers) {
		  if (n < min) {
			  min = n;
		  }
	  }
	  
	  return min.toString();
	}

	private String getMode() {
	  return "";
	}

	private String getMedian() {
		return "";
	}

	private String getFirstQuartile() {
		return "";
	}

	private String getThirdQuartile() {
		return "";
	}

	private String getUpperLimit() {
		return "";
	}

	private String getLowerLimit() {
		return "";
	}

	private String getAmplitude() {
		return "";
	}

	private String getVariance() {
		return "";
	}

	private String getStandardDeviation() {
		return "";
	}

	private String getCoefficientOfVariation() {
		return "";
	}

	private String getInterquartileRange() {
		return "";
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calc, menu);
		return true;
	}

}
