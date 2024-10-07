//NO TOCAR

package tp1.view;

import tp1.logic.Game;

public abstract class GameView {

	protected Game game;
	
	public GameView(Game game) {
		this.game = game;
	}
	
	// show methods
	public abstract void showWelcome();// mensaje de bienvenida
	public abstract void showGame();// muestra el estado del juego
	public abstract void showHelp();
	public abstract void showEndMessage();// mensaje final
	public abstract void showError(String message);// mensaje de error
	public abstract void showMessage(String message);// mensaje

	// get data from view methods
	public abstract String[] getPrompt();//pide una línea al usuario
}
