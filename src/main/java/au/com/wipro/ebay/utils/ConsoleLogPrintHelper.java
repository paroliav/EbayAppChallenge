package au.com.wipro.ebay.utils;

import org.apache.log4j.Logger;

public class ConsoleLogPrintHelper {
	
	private static Logger Log;
	
	public ConsoleLogPrintHelper(Class clazz){
		Log = Logger.getLogger(clazz.getName());
	}
	
	public void print(String message){
		Log.info(message);
		System.out.println(message);
	}
}
