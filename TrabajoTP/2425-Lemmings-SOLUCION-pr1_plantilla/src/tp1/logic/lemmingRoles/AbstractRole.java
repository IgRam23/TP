package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;

public abstract class AbstractRole implements LemmingRole {

    public boolean receiveInteraction(GameItem other, Lemming lemming) {
        return other.interactWith(lemming);     
    }

    public boolean interactWith(Lemming receiver, Lemming owner) {
        return false; // Por defecto, no hay interacción
    }

    public boolean interactWith(Wall wall, Lemming owner) {
        return false; // Por defecto, no interactúa con paredes
    }

    public boolean interactWith(ExitDoor door, Lemming owner) {
        return false; // Por defecto, no interactúa con puertas
    }
}

