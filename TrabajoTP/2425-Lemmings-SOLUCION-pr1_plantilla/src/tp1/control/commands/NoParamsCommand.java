package tp1.control.commands;

import tp1.logic.Game; 
import tp1.view.GameView; 
import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords) {
		
	   if ((commandWords.length == 0 || (commandWords.length == 1 && commandWords[0].equalsIgnoreCase(Messages.EMPTY))) 
	            || (commandWords.length == 1 && (commandWords[0].equalsIgnoreCase(getName()) || commandWords[0].equalsIgnoreCase(getShortcut())))) {
	            return this;  
	        }
		
		return null;
	}
	
	@Override
	public abstract void execute(Game game, GameView view); 
	
}