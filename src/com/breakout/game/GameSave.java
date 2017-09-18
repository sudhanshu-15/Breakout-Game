package com.breakout.game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

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

			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			jfc.setApproveButtonText("Save");
			jfc.setApproveButtonMnemonic('s');
			jfc.setApproveButtonToolTipText("Save Game");
			int returnValue = jfc.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				FileOutputStream fileOut = new FileOutputStream(selectedFile.getPath()+".txt");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(list);
				out.close();
				fileOut.close();
				return true;
			}
			else {
				return false;
			}
		}catch(Exception i) {
			i.printStackTrace();
		}
		return false;
	}


}
