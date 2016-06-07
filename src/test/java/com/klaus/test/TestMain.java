package com.klaus.test;

public class TestMain {

	public static void main(String[] args) {

		System.out.println("Test Start");
		
	
		
		for(int i=0;i<10;i++){
			
			for(int j=0;j<10;j++){
				
				System.out.print(i+" "+j+" ");
				
				if(j>5){
					break;
				}
				
			}
			
			System.out.println();
			
		}
		

		System.out.println("Test End");

	}

}
