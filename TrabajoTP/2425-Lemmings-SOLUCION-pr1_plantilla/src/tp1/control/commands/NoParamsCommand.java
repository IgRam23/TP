package tp1.control.commands;

import tp1.logic.GameModel;   
import tp1.exceptions.*;
import tp1.view.GameView; 
import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if ((commandWords.length == 0 || (commandWords.length == 1 && commandWords[0].equalsIgnoreCase(Messages.EMPTY)))) //opcion de enter para update
	 		return this; 
		
	   if (commandWords.length < 1 || !matchCommandName(commandWords[0]))
	 		return null;
	   
	   if (commandWords.length == 1 || matchCommandName(commandWords[0]))
	 		return this;
	   
	     
	   throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	 	
	 }

	
	@Override
	public abstract void execute(GameModel game, GameView view); 
	
}