 package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;

public class SetRoleCommand extends NoParamsCommand {

    private Position position; 
    private String roleInput;  

    private static final String NAME = "setRole";  
    private static final String SHORTCUT = "sr";   
    private static final String DETAILS = "[s]et[R]ole ROLE ROW COL: sets the lemming in position (ROW,COL) to role ROLE";
    private static final String HELP = "Assign a role to a lemming at a given position.";

    public SetRoleCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);  // Constructor de la clase base
    }

    public void setParameters(String roleInput, Position position) {
        this.roleInput = roleInput;
        this.position = position;
    }

    @Override
    public void execute(GameModel game, GameView view) {

        if (position == null || roleInput == null) {
            System.out.println(Messages.ERROR);
            return;
        }

        LemmingRole role = LemmingRoleFactory.parse(roleInput, position);

        if (role == null) {
            System.out.println(Messages.ERROR_ROLE); 
            return;
        }

        boolean success = game.setRoleAt(position, role);
        if (!success) {
            System.out.println(Messages.ERROR_ROLE_POS);    
        }
        
		view.showGame();
    }

    @Override
    public Command parse(String[] commandWords) {

        if (commandWords.length != 4) { //Necesitamos 4 palabras: "sr", "Parachuter", "3", "3"
            return null;
        }

        String role = commandWords[1];  //El primer parámetro después de "sr" es el rol
        int row, col;

        try {
            row = Integer.parseInt(commandWords[2]);  //El segundo parámetro es la fila
            col = Integer.parseInt(commandWords[3]);  //El tercer parámetro es la columna
        } catch (NumberFormatException e) {
            System.out.println(Messages.ERROR_ROLE_POS);
            return null;
        }

        Position position = new Position(row, col);
        SetRoleCommand command = new SetRoleCommand();
        command.setParameters(role, position);

        return command;
    }
}
