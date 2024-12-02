package tp1.logic;

import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.*;

public interface GameWorld{

	
	void lemmingHasDied();
	void incrementLemmingsExit();
	int numLemmingsExit();
	int numLemmings();    
	int numLemmingsToWin();
	void addLemming(Lemming lemming);
	void incrementDeadLemmings();
	void setFinished(boolean b);
	public boolean receiveInteractionsFrom(GameItem obj);
	public boolean isValidPosition(Position position);
}
