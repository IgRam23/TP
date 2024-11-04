package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;

public class ResetCommand extends NoParamsCommand{

	private static final String NAME = "reset";
	private static final String SHORTCUT = "r";
	private static final String DETAILS = "[r]eset";
	private static final String HELP = "start a new game";

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}

	@Override
	public void execute(GameModel game, GameView view){
		game.reset(); 
		view.showGame();
	}

}
