package tp1.control.commands;

import tp1.logic.Game;
import tp1.view.GameView; 

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords) {
		
		boolean matchCommand = false; 
	       
		if (commandWords[0].equalsIgnoreCase(getName()) || commandWords[0].equalsIgnoreCase(getShortcut())) {
			matchCommand = true;
	    }

		if (commandWords.length == 1 && matchCommand){ 
            return this;  
        }
		
		return null;
	}
	
	@Override
	public abstract void execute(Game game, GameView view); 
	
}