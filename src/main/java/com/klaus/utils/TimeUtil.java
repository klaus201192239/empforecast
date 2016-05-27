package com.klaus.utils;

import java.util.Calendar;

public class TimeUtil {
	
	public static String getObjectId() {

		long firstPart = Calendar.getInstance().getTimeInMillis();

		//String secondPart = getMACAddress();
		
		int thirdPart=(int)(Math.random()*100000);

		//System.out.println(firstPart);
		//System.out.println(secondPart);
		//System.out.println(thirdPart);
		
		return (firstPart+""+thirdPart).substring(0, 15);
	}

	
}
