package com.thiagogsr.estatisticacomputacional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalcActivity extends Activity {

  private ArrayList<Float> numbers;
  private Button btBack;
  private TextView orderedNumbers;
  private TextView maximumNumber;
  private TextView minimumNumber;
  private TextView mode;
  private TextView average;
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
  
  private Float vMax;
  private Float vMin;
  private Float vAverage;
  private Float vMedian;
  private Float Q1;
  private Float Q3;
  private Float vAmplitude;
  private Float vVariance;
  private Double vDeviation;
  private Float vCoefficient;
  private Float vInter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calc);

    btBack = (Button) findViewById(R.id.btBack);
    orderedNumbers = (TextView) findViewById(R.id.orderedNumbers);
    maximumNumber = (TextView) findViewById(R.id.maximumNumber);
    minimumNumber = (TextView) findViewById(R.id.minimumNumber);
    mode = (TextView) findViewById(R.id.mode);
    average = (TextView) findViewById(R.id.average);
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
      numbers = (ArrayList<Float>) bum.getSerializable("numbers");
      Collections.sort(numbers);
      
      vMax = getMaximumNumber();
      vMin = getMinimumNumber();
      vAverage = getAverage();
      vMedian = getMedian();
      Q1 = getFirstQuartile();
      Q3 = getThirdQuartile();
      vVariance = getVariance();
      vDeviation = getStandardDeviation();
      vInter = getInterquartileRange();
      vCoefficient = getCoefficientOfVariation();
      vAmplitude = getAmplitude();

      orderedNumbers.setText(getOrderedNumbers(numbers));
      maximumNumber.setText(vMax.toString());
      minimumNumber.setText(vMin.toString());
      mode.setText(getMode());
      average.setText(vAverage.toString());
      median.setText(vMedian.toString());
      firstQuartile.setText(Q1.toString());
      thirdQuartile.setText(Q3.toString());
      upperLimit.setText(getUpperLimit());
      lowerLimit.setText(getLowerLimit());
      amplitude.setText(vAmplitude.toString());
      variance.setText(vVariance.toString());
      standardDeviation.setText(vDeviation.toString());
      coefficientOfVariation.setText(vCoefficient.toString());
      interquartileRange.setText(vInter.toString());
    }
    
    btBack.setOnClickListener(new Button.OnClickListener() {
	  @Override
	  public void onClick(View v) {
	    finish();
      }
	});
  }
  
  private Float getValue(Float position) {
	Float value;
	if ((numbers.size() % 2) == 0) {
		value = (numbers.get((int) Math.ceil(position - 1)) + numbers.get((int) Math.floor(position - 1)))/2;
	} else {
		value = numbers.get((int) (position - 1));
	}
	
	return value;
  }

  private String getOrderedNumbers(ArrayList<Float> numbers) {
    ArrayList<Float> sortedNumbers = numbers;
    int size = sortedNumbers.size();
    int i = 0;
    String result = "";

    if (size > 0) {
      result = "[";
      for (Float n: sortedNumbers) {
        result += n.toString();
        i++;
        
        if (i < size) {
          result += ", ";
        }
      }
      result += "]";
    }
    
    return result;
  }

  private Float getMaximumNumber() {
    Float max = 0f;

    for (Float n: numbers) {
      if (n > max) {
        max = n;
      }
    }

    return max;
  }

  private Float getMinimumNumber() {
    Float min = numbers.get(0);

    for (Float n: numbers) {
      if (n < min) {
        min = n;
      }
    }
    
    return min;
  }

  private String getMode() {
    Map<Integer, ArrayList<Float>> map = new HashMap<Integer, ArrayList<Float>>();
    String mode = "";
    String modeType = "";
    Float old = 0f;
    int c = 0;
    int i = 0;

    for (Float f: numbers) {
      if (!f.equals(old) || i == (numbers.size() - 1)) {
        if (c > 0) {
          ArrayList<Float> t;
          if (map.containsKey(c)) {
            t = map.get(c);
          } else {
            t = new ArrayList<Float>();
          }
          t.add(old);
          map.put(c, t);
        }
        old = f;
        c = 0;
      }
      c++;
      i++;
    }
    
    int max = 0;
    for (Integer m: map.keySet()) {
    	if (m > max) {
    		max = m;
    	}
    }
    
    if (max > 1) {
    	ArrayList<Float> result = map.get(max);
    	mode = getOrderedNumbers(result);
    	if (result.size() == 2) {
    		modeType = "bimodal";
    	} else {
    		modeType = "multimodal";
    	}
    }

    return mode == "" ? "Amodal" : (mode + " - " + modeType);
  }
  
  private Float getAverage() {
    Float average = 0f;
    for (Float f: numbers) {
	  average += f;
    }
    average /= numbers.size();
    return average;
  }

  private Float getMedian() {
	Float position = (numbers.size() + 1)/2f;
    return getValue(position);
  }

  private Float getFirstQuartile() {
    return getValue(0.25f * (numbers.size() + 1));
  }

  private Float getThirdQuartile() {
	return getValue(0.75f * (numbers.size() + 1));
  }

  private String getUpperLimit() {
	Float result = (float) (Q3 + (1.5*(vInter)));
    return result.toString();
  }

  private String getLowerLimit() {
	Float result = (float) (Q1 - (1.5*(vInter)));
    return result.toString();
  }

  private Float getAmplitude() {
    return vMax-vMin;
  }

  private Float getVariance() {
	Float sum = 0f;
	
	for (Float f: numbers) {
		Float number = f - vAverage;
		sum += number*number;
	}
	
	Float result = sum/(numbers.size() - 1);
    return result;
  }

  private Double getStandardDeviation() {
    return Math.sqrt(vVariance);
  }

  private Float getCoefficientOfVariation() {
    return (float) (vDeviation/vAverage);
  }

  private Float getInterquartileRange() {
    return Q3-Q1;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.calc, menu);
    return true;
  }

}