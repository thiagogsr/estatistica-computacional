package com.ec.entities;

public class Probability {
	
	private Float success;
	private Float fail;
	private Integer sample;
	
	public Probability(Float success, Integer sample) {
		this.success = success/100f;
		this.fail = 1 - this.success;
		this.sample = sample;
	}
	
	public String formule(int x) {
		int diff = this.sample - x;
		return "P\\\\left ( x=" + x + " \right ) = \binom{" + this.sample + "}{" + x + "} * " + this.success + "^" + x + "*" + this.fail + "^" + diff + " = \frac{" + this.sample + "!}{" + x + "!\\\\left ( " + this.sample + "-" + x + " \right )!} * " + this.success +  "^" + x + " * " + this.fail + "^" + diff + " =";
	}
	
	public Float run(int x) {
		int diff = this.sample - x;
		Integer numerator = this.factorial(this.sample); 
		Float denominator = (float) (this.factorial(x) * this.factorial(diff));
		Float multiplication = (float) (Math.pow(this.success, x) * Math.pow(this.fail, diff));
		return (numerator/denominator) * multiplication;
	}
	
	public Integer factorial(int number) {
		Integer result = number;
		for (int i = number - 1; i > 0; i--) {
			result *= i;
		}
		return result;
	}

}
