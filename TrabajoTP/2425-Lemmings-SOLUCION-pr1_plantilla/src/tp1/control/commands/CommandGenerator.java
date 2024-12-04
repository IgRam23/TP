package tp1.control.commands;

import java.util.Arrays; 
import tp1.exceptions.*;
import tp1.view.Messages;

import java.util.List;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
			new SetRoleCommand(),
			new UpdateCommand(),
			new ResetCommand(),
			new LoadCommand(),
			new SaveCommand(), 
			new HelpCommand(),
			new ExitCommand()
	);

	public static Command parse(String[] commandWords) throws CommandParseException{		
		for (Command c: availableCommands) {
			Command matchedCommand = c.parse(commandWords);
			if (matchedCommand != null) {
				return matchedCommand;
			}
		}
		throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		for (Command c: availableCommands) {
			commands.append(c.helpText());
		}
		
		return commands.toString();
	}

}
