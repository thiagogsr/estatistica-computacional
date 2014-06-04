package com.ec.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ec.R;
import com.ec.entities.Calculator;

public class CalcResultActivity extends Activity {

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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calc_result);

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

    if(getIntent().getExtras() != null) {
      Bundle bum = getIntent().getExtras();
      this.numbers = (ArrayList<Float>) bum.getSerializable("numbers");
      
      Calculator calc = new Calculator(this.numbers);

      orderedNumbers.setText(calc.getOrderedNumbers());
      maximumNumber.setText(calc.getMaximumNumber().toString());
      minimumNumber.setText(calc.getMinimumNumber().toString());
      mode.setText(calc.getMode());
      average.setText(calc.getAverage().toString());
      median.setText(calc.getMedian().toString());
      firstQuartile.setText(calc.getFirstQuartile().toString());
      thirdQuartile.setText(calc.getThirdQuartile().toString());
      upperLimit.setText(calc.getUpperLimit());
      lowerLimit.setText(calc.getLowerLimit());
      amplitude.setText(calc.getAmplitude().toString());
      variance.setText(calc.getVariance().toString());
      standardDeviation.setText(calc.getStandardDeviation().toString());
      coefficientOfVariation.setText(calc.getCoefficientOfVariation().toString());
      interquartileRange.setText(calc.getInterquartileRange().toString());
    }
    
    btBack.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

}