package tp1.logic;

import tp1.logic.gameobjects.Lemming;

public interface GameWorld{

	
	void lemmingHasDied();
	void incrementLemmingsExit();
	int numLemmingsExit();
	int numLemmingsToWin();
	void addLemming(Lemming lemming);
	void incrementDeadLemmings();
	void setFinished(boolean b);
	
}
