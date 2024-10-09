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
			
			 // pedir línea al usuario
			
			String[] res =  view.getPrompt();
			String res2 = String.join(System.lineSeparator(), res);
			mensajesToString (res2);
			//System.out.print(res2);
			
			
			
			 // distinción de casos (mejor en método
			 // privado auxiliary
		
		}
		
		
	
		view.showEndMessage();
		
	}

	
	public void mensajesToString (String s) {
		
		String mensaje;
		s.toLowerCase();   
		 
		switch(s) {
		
			case Messages.COMMAND_HELP_NAME:
			case Messages.COMMAND_HELP_SHORTCUT:
				mensaje = Messages.HELP;
				break;
				
			case "reset":
				mensaje ="";
				break;
				
			case Messages.COMMAND_EXIT_NAME:
			case Messages.COMMAND_EXIT_SHORTCUT:
				mensaje = Messages.COMMAND_EXIT_HELP;
				break;
				
			case Messages.COMMAND_NONE_NAME:
			case Messages.COMMAND_NONE_SHORTCUT:
			case Messages.EMPTY: 
				mensaje = Messages.COMMAND_NONE_HELP;
				break;
			
			default:
		        mensaje = "Comando no válido";
		        break;
		}
		
		view.showMessage(mensaje);
	}
}
