package tp1;  

import java.util.Locale; 

import tp1.control.Controller;
import tp1.logic.Game;
import tp1.view.ConsoleColorsView;
import tp1.view.ConsoleView;
import tp1.view.GameView;
import tp1.view.Messages;

public class Main {

	public static void main(String[] args) {

        Locale.setDefault(new Locale("es", "ES"));
		
		try {
			
			int nLevel = 1;
			if (args.length != 0) nLevel = Integer.parseInt(args[0]);

            Game game = new Game(nLevel);
            GameView view = args.length>1 ? new ConsoleColorsView(game): new ConsoleView(game);
            Controller controller = new Controller(game, view);
					
			controller.run();

		} catch (NumberFormatException e) {
			System.out.println(String.format(Messages.LEVEL_NOT_A_NUMBER_ERROR, args[0]));
		}
	}
} 
