package tp1.logic;

import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {
	
	void update();
	void reset();
	public void setFinished(boolean b);
	void nextCycle();
	boolean isFinished();
	
	
    public boolean setRoleAt(Position position, LemmingRole role);
}
