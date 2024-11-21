package tp1.logic.lemmingRoles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tp1.control.commands.Command;
import tp1.control.commands.ExitCommand;
import tp1.control.commands.HelpCommand;
import tp1.control.commands.ResetCommand;
import tp1.control.commands.SetRoleCommand;
import tp1.control.commands.UpdateCommand;
import tp1.logic.*;
import tp1.view.Messages;

public class LemmingRoleFactory {
	private static final List<LemmingRole> availableRoles = Arrays.asList(
			new DownCaverRole(),
			new ParachuterRole(),
			new WalkerRole()
	);
	
	public static LemmingRole parse(String commandWords) {		
		for (LemmingRole r: availableRoles) {
			//Command matchedCommand = c.parse(commandWords);
			if (r.canParse(commandWords)) {
				return r;
			}
		}
		return null;
	}
    
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		
		for (LemmingRole c: availableRoles) {
			commands.append("       " + c.helpText()).append("\n");
		}
		
		return commands.toString();
	}

	

}
 