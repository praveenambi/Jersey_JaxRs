package com.praveen.jax.service;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class Summation_driver {


	public static Map<String, String> data_map;

	@Test(dataProvider="dp_sum",dataProviderClass= com.praveen.jax.service.Summation_dataprovider.class)
	public static void test_summation()
	{

		Map<String, String>  calc = new  HashMap<String, String>();

		String Num1 = calc.get("Emp_ID").toString();
		String Num2 = calc.get("name").toString();
		String Expected_Result = calc.get("last_name").toString();

		System.out.println("******************************"+ Num1);
		System.out.println("------------------------------"+ Num2);
		System.out.println("+++++++++++++++++++++++++++++++"+ Expected_Result);


		data_map = new HashMap<>();
		data_map.put(Num1,Num2);

		System.out.println("My data map is -------"+   data_map);
		double Number1 = Double.parseDouble(Num1);
		double Number2 = Double.parseDouble(Num2);
		double Exp_result = Double.parseDouble(Expected_Result);

		double Actual_result = Number1+Number2;
		if(Actual_result==Exp_result)
		{
			System.out.println("Pass");
		}
		{
			System.out.println("Fail");
		}




	}

}
