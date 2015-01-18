package com.example.android_unit_test.service;

import android.content.Context;

public class CalService {
	private Context context;
	
	public CalService(Context context) {
		super();
		this.context = context;
	}

	public int add(int num1, int num2){
		return num2 + num1;
	}
	
	public int sub(int num1, int num2){
		return num1 - num2;
	}
	
	public int mul(int num1, int num2){
		return num2 * num1;
	}
	
	public int div(int num1, int num2){
		if(num2 == 0){
			try {
				throw new Exception("除数不能为0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return num1/num2;
	}
}
