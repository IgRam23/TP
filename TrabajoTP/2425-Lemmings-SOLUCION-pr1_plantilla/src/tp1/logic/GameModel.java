package tp1.logic;

import tp1.logic.gameobjects.GameObject;

public interface GameModel {
	
	void update();
	void reset();
	public void setFinished(boolean b);
	void nextCycle();
	boolean isFinished();
	//ns si esta bien
	//GameObject getObjectAt(Position position);
	
}
