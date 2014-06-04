package com.ec.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
	
	private ArrayList<Float> numbers;
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
  	private int size;
  	
  	public Calculator(ArrayList<Float> list) {
  		this.numbers = list;
  		Collections.sort(numbers);
  		this.size = this.numbers.size();
  	}

  	protected String orderedNumbers(ArrayList<Float> list) {
  	  ArrayList<Float> sortedNumbers = list;
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
  	
  	private Float getValue(Float position) {
	    Float value;
	    if (position == Math.ceil(position)) {
	      value = (float) this.numbers.get((int) (position - 1));
	    } else {
	      value = ((float) this.numbers.get((int) Math.ceil(position - 1)) + (float) this.numbers.get((int) Math.floor(position - 1)))/2f;
	    }
	  
	    return value;
  	}
  	
  	public String getOrderedNumbers() {
  		return this.orderedNumbers(this.numbers);
  	}

  	public Float getMaximumNumber() {
  	  Float max = 0f;

  	  for (Float n: this.numbers) {
  	    if (n > max) {
  	      max = n;
  	    }
  	  }

  	  return max;
  	}

  	public Float getMinimumNumber() {
  	  Float min = this.numbers.get(0);

  	  for (Float n: this.numbers) {
  	    if (n < min) {
  	      min = n;
  	    }
  	  }
  	  
  	  return min;
  	}

  	public String getMode() {
  	  Map<Integer, ArrayList<Float>> map = new HashMap<Integer, ArrayList<Float>>();
  	  String mode = "";
  	  String modeType = "";
  	  Float old = 0f;
  	  int c = 0;
  	  int i = 0;

  	  for (Float f: this.numbers) {
  	    Boolean isLast = i == (this.size - 1);
  	    if (!f.equals(old) || isLast) {
  	      if (isLast && f.equals(old)) {
  	      c++;
  	      }
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
  	    mode = this.orderedNumbers(result);
  	    if (result.size() == 2) {
  	      modeType = " - bimodal";
  	    } else if (result.size() > 2) {
  	      modeType = " - multimodal";
  	    }
  	  }

  	  return mode == "" ? "Amodal" : (mode + modeType);
  	}

  	public Float getAverage() {
  	  Float average = 0f;
  	  for (Float f: this.numbers) {
  	    average += f;
  	  }
  	  average /= this.size;
  	  return average;
  	}

  	public Float getMedian() {
  	  Float position = (this.size + 1)/2f;
  	  return getValue(position);
  	}

  	public Float getFirstQuartile() {
  	  return this.size > 2 ? getValue(0.25f * ((float) (this.size + 1))) : 0f;
  	}

  	public Float getThirdQuartile() {
  	  return this.size > 2 ? getValue(0.75f * ((float) (this.size + 1))) : 0f;
  	}

  	public String getUpperLimit() {
  	Float result = this.Q3 + (1.5f * (this.vInter));
  	  return result.toString();
  	}

  	public String getLowerLimit() {
  	  Float result = this.Q1 - (1.5f * (this.vInter));
  	  return result.toString();
  	}

  	public Float getAmplitude() {
  	  return this.vMax - this.vMin;
  	}

  	public Float getVariance() {
  	  Float sum = 0f;

  	  for (Float f: this.numbers) {
  	    Float number = f - this.vAverage;
  	    sum += number * number;
  	  }

  	  Float result = sum/(this.size - 1);
  	  return result;
  	}

  	public Double getStandardDeviation() {
  	  return Math.sqrt(this.vVariance);
  	}

  	public Float getCoefficientOfVariation() {
  	  return (float) (this.vDeviation/this.vAverage);
  	}

  	public Float getInterquartileRange() {
  	  return this.Q3 - this.Q1;
  	}

}
