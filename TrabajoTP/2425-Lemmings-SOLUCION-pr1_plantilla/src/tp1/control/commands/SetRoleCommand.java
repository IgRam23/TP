 package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;

public class SetRoleCommand extends Command {

    private Position position; 
    private LemmingRole roleInput;  

    private static final String NAME = "setRole";  
    private static final String SHORTCUT = "sr";   
    private static final String DETAILS = "[s]et[R]ole ROLE ROW COL: sets the lemming in position (ROW,COL) to role ROLE";
    private static final String HELP = "\n" + LemmingRoleFactory.commandHelp();

    public SetRoleCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);  
    }


    @Override
    public void execute(GameModel game, GameView view) {

        if (position == null || roleInput == null) {
            view.showError(Messages.ERROR_POSITION);
            return;
        }

        boolean success = game.setRoleAt(position, roleInput);
        if (!success) {
        	view.showError(Messages.ERROR_POSITION);
        	return;
        }
        
        game.update();
        game.nextCycle();
        
		view.showGame();
    }

    @Override
    public Command parse(String[] commandWords) {

        if (commandWords.length != 4 || !matchCommandName(commandWords[0])) { //Necesitamos 4 palabras: "sr", "Parachuter", "3", "3"
            return null;
        }
        
        roleInput = LemmingRoleFactory.parse(commandWords[1]);
        if(roleInput == null) {
        	return null;
        }
               
        int row;
        char letra;
        int col;
             	
            
        letra = Character.toLowerCase(commandWords[2].charAt(0));
        
        if(letra < 'a' || letra > 'j')  {
        	position = null;
        }else {
        	col = letra - 'a';
            row = Integer.parseInt(commandWords[3]);  //El segundo parámetro es la fila (num)
            if(row < 1 || row > 10) {
            	position = null;
            }else {
            	position = new Position(row-1,col);
            }

        }
            	

        return this;
    }
}
