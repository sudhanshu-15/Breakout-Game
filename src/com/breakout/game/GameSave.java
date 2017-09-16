package com.breakout.game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import com.breakout.command.MacroCommand;

public class GameSave {


	private ArrayList<MacroCommand> list;

	public GameSave(ArrayList<MacroCommand> mylist) 
	{
		this.list = mylist;
	}

	public boolean Serialize() 
	{
		try {
	         FileOutputStream fileOut = new FileOutputStream("C:\\gameData.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(list);
	         out.close();
	         fileOut.close();
	         //System.out.printf("Serialized data is saved in /tmp/employee.ser");
	         return true;
	      }catch(Exception i) {
	         i.printStackTrace();
	      }
		return false;
	}


}
