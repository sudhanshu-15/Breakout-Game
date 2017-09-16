package com.breakout.game;

import com.breakout.command.MacroCommand;
import java.io.*;
import java.util.ArrayList;

public class GameLoad {
	private ArrayList<MacroCommand> savedCommands;
	public static void GameLoad(String args[]) {
		
	}
	
	public ArrayList<MacroCommand> Deserialize(){
		
		
	      try {
	          FileInputStream fileIn = new FileInputStream("C:\\gameData.ser");
	          ObjectInputStream in = new ObjectInputStream(fileIn);
	          savedCommands = (ArrayList<MacroCommand>) in.readObject();
	          if(savedCommands != null)
	        	  System.out.println(1);
	          in.close();
	          fileIn.close();
	       }catch(IOException i) {
	          i.printStackTrace();
	          return null;
	       }catch(ClassNotFoundException c) {
	          System.out.println("Employee class not found");
	          c.printStackTrace();
	          return null;
	       }
	     return savedCommands;
	}
}