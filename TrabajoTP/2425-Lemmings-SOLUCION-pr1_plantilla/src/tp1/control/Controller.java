package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.view.ConsoleView;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
		
	}

	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
		view.showWelcome();
		//TODO fill your code: The main loop that displays the game, asks the user for input, and executes the action.
		
		
		while (!game.isFinished()) {
			
			view.showGame();
			
			String[] res =  view.getPrompt();
			String command = String.join(" ", res).toLowerCase();
			mensajesToString (command);
			
			 if (!game.isFinished() && (!command.equals("reset")) && (!command.equals("r"))) {
		            game.Update(); //Para actualizar los ciclos a no ser que se haya terminado o reseteado
		        }
		     
		}
		
	    //El juego termina
		view.showEndMessage();
		
	}

	
	public void mensajesToString (String s) {  
		 
		switch(s) {
		
			case Messages.COMMAND_HELP_NAME:
			case Messages.COMMAND_HELP_SHORTCUT:
				view.showMessage(Messages.HELP);
				break;
				
			case "reset":
			case "r":
				game.reset();  //hace reset y crea una nueva instancia d Game
				break;
				
			case Messages.COMMAND_EXIT_NAME:
			case Messages.COMMAND_EXIT_SHORTCUT:
				view.showMessage(Messages.COMMAND_EXIT_HELP);
				game.setFinished(true);
				break;
				
			case Messages.COMMAND_NONE_NAME:
			case Messages.COMMAND_NONE_SHORTCUT:
			case Messages.EMPTY: 
				view.showMessage(Messages.COMMAND_NONE_HELP);
				//game.Update();
				break;
			
			default:
				view.showMessage("Comando no válido");
		        break;
		}
	}
}
