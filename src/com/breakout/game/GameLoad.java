package com.breakout.game;

import com.breakout.command.MacroCommand;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class GameLoad {
	private ArrayList<MacroCommand> savedCommands;
	public static void GameLoad() {
		
	}
	
	public ArrayList<MacroCommand> Deserialize(){
		
		
	      try {
	    	  JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//	    	  jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	  jfc.setApproveButtonText("Open");
	    	  jfc.setApproveButtonMnemonic('o');
	    	  jfc.setApproveButtonToolTipText("Open Game");
	    	  int returnValue = jfc.showOpenDialog(null);
	    	  if (returnValue == JFileChooser.APPROVE_OPTION) {
	    		  File file = jfc.getSelectedFile();
	    		  FileInputStream fileIn = new FileInputStream(file);
		          ObjectInputStream in = new ObjectInputStream(fileIn);
		          savedCommands = (ArrayList<MacroCommand>) in.readObject();
		          System.out.println(savedCommands.size());
		          if(savedCommands != null)
		        	  System.out.println(1);
		          in.close();
		          fileIn.close();
	    	  }
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