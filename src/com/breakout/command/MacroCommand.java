package com.breakout.command;

import java.io.Serializable;
import java.util.ArrayList;

public class MacroCommand implements Command,Serializable {
	
	private ArrayList<Command> commands;
	
	public MacroCommand(){
		commands = new ArrayList<Command>();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for(int i = 0; i < commands.size(); i++){
			Command command = commands.get(i);
			command.execute();
		}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		for(int i = 0; i < commands.size(); i++){
			Command command = commands.get(i);
			command.undo();
		}

	}
	
	public void add(Command command){
		if(command != null){
			commands.add(command);
		}
	}
	
	public void remove(Command command){
		int i = commands.indexOf(command);
		if(i >= 0){
			commands.remove(i);
		}
	}

}
