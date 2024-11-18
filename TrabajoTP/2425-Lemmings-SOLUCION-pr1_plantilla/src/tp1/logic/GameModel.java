package tp1.logic;

import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {
	
	void update();
	void reset();
	public void setFinished(boolean b);
	void nextCycle();
	boolean isFinished();
	
	
	// Establecer el rol de un lemming en una posición específica
    public boolean setRoleAt(Position position, LemmingRole role);
}
