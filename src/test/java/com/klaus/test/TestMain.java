package com.klaus.test;

import java.util.List;
import java.util.Map;

import com.klaus.utils.Keans;

public class TestMain {

	public static void main(String[] args) {

		System.out.println("Test Start");
		
		double[] p = { 165.2, 2, 34.1, 150.5, 6, 7, 9, 10, 5.5, 4.7, 11.4, 100.3, 150.5, 204.3, 100 };
		int k = 3;
		
		Keans kmean=new Keans( p);

		List<Map<String,Double>> list=kmean.classifyData();
		
		for(int i=0;i<list.size();i++){
			
			Map<String,Double> map=list.get(i);
			
			System.out.print(map.get("maxk")+"  ");
			System.out.print(map.get("mink")+"  ");
			System.out.print(map.get("meank"));
			System.out.println();
			
		}
		

		System.out.println("Test End");

	}

}
