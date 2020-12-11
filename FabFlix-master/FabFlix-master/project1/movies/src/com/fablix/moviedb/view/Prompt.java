package com.fablix.moviedb.view;

import java.util.Date;
//import java.io.Console;
import java.util.Scanner;

public class Prompt {
	
	private Scanner userIn; 
	
	public Prompt(){
		openScanner();
	}
	
	private void openScanner(){
		if (userIn == null){
			userIn = new Scanner(System.in);
		}
	}

	protected void closeScanner(){
		if (userIn != null) {
			userIn.close();
		}
		userIn = null;
	}
	
//	private void openConsole(){		
//			userCons = System.console();
//	}
    
	/*
	 *  get user's password
	 *  @return char[]
	 */
//	protected char[] promptPWD(String message){
//		return userCons.readPassword(message);
//	}
//	
//	protected String promptUserName(String message){
//		return userCons.readLine(message);
//	}
	
	protected int promptInt(String message){
		System.out.println(message);
		try{
			
			int Int = userIn.nextInt();
			userIn.nextLine();
			return Int;
		
		}catch (Exception e){
			System.out.println("Please enter an valid integer!");
			userIn.nextLine();
			return -1;
		}
		
	}
	
	
	protected String promptString(String message){
		System.out.println(message);
		return userIn.nextLine();
	}
}

