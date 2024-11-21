package tp1.logic.lemmingRoles;

import java.util.Arrays;
import java.util.List;


public class LemmingRoleFactory {
	private static final List<LemmingRole> availableRoles = Arrays.asList(
			new DownCaverRole(),
			new ParachuterRole(),
			new WalkerRole()
	);
	
	public static LemmingRole parse(String commandWords) {		
		for (LemmingRole r: availableRoles) {
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
 