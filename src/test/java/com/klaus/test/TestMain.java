package com.klaus.test;

import com.klaus.factory.MyBeansFactory;
import com.klaus.workservice.WorkerService;

public class TestMain {

	public static void main(String[] args) {

		System.out.println("Test Start");
		
	
		WorkerService worker=(WorkerService)MyBeansFactory.getBeans("stuabilityworker");
		worker.start();
		
		
		
		System.out.println("Test End");

	}

}
