package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.*;  
import tp1.logic.lemmingRoles.*;
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole {  
    @Override
    public void start(Lemming lemming) {
        if (!lemming.isInAir()) {
            lemming.setRole(new WalkerRole()); // Si no está en el aire, vuelve a ser caminante
        }
    }

    @Override
    public void play(Lemming lemming) {
        if (lemming.isInAir()) {
            lemming.fallSlowly(); // Caída lenta sin morir
        } else {
            lemming.setRole(new WalkerRole()); // Al tocar el suelo, vuelve a ser caminante
        }
    }

    @Override
    public String getIcon(Lemming lemming) {
        return Messages.LEMMING_PARACHUTE; // Icono de paracaidista
    }
}
