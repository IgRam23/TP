package tp1.control.commands;
import tp1.logic.GameModel;
import tp1.logic.gameobjects.*;         
import tp1.logic.lemmingRoles.*; 
import tp1.logic.*;
import tp1.view.*; 


public class SetRoleCommand extends NoParamsCommand {
	
	private final Position position;
	private final LemmingRole role;

   
	private static final String NAME = "setRole";
	private static final String SHORTCUT = "sr";
	private static final String DETAILS = "[s]et[R]ole";
	private static final String HELP = "start a new game";

    
    public SetRoleCommand(String roleInput, Position position) {
		super(NAME, SHORTCUT, DETAILS, HELP); 
    	this.role = LemmingRoleFactory.parse(roleInput);
        this.position = position;
    }

    @Override
    public void execute(GameModel game, GameView view) {
        GameObject object = game.getObjectAt(position);

        if (object == null || !object.setRole(role)) {
            System.out.println("[ERROR] Error: SetRoleCommand error (Incorrect position or no object in that position admits that role)");
        }
    }

    @Override
    public String helpText() {
        return "[s]et[R]ole ROLE ROW COL: sets the lemming in position (ROW,COL) to role ROLE";
    }
}

