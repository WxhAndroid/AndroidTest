package com.example.android_unit_test.service;

import junit.framework.Assert;
import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

public class CalServiceTest extends AndroidTestCase {
	private CalService calService;
	
	protected void setUp() throws Exception {
		Context context = this.getContext();
		this.calService = new CalService(context);
		System.out.println("<<setup()");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAdd() {
//		fail("Not yet implemented");
		int sum = this.calService.add(3, 5);
		Assert.assertEquals(9, sum);
		System.out.println("sum=" + sum);
		Log.e("sum", sum+"");
	}

	public void testSub() {
		int sum = this.calService.sub(6, 3);
		System.out.println("sum = " + sum);
		Log.e("sum", sum+"");
//		fail("Not yet implemented");
	}

	public void testMul() {
//		fail("Not yet implemented");
		int sum = this.calService.mul(6, 3);
		System.out.println("sum = " + sum);
		Log.e("sum", sum+"");
	}

	public void testDiv() {
//		fail("Not yet implemented");
		int sum = this.calService.div(6, 3);
		System.out.println("sum = " + sum);
		Log.e("sum", sum+"");
	}

}
