package tp1.logic.lemmingRoles;

import java.util.ArrayList;
import java.util.List;
import tp1.logic.*;
import tp1.view.Messages;

public class LemmingRoleFactory {
    private static final List<LemmingRole> availableRoles = new ArrayList<>();

    static {
        availableRoles.add(new WalkerRole(new Position(0, 0))); // Registro del rol
        availableRoles.add(new ParachuterRole(new Position(0, 0)));// Registro del rol
        availableRoles.add(new DownCaverRole(new Position(0, 0))); // Registtro del rol
    }
    
    public static LemmingRole parse(String input) {
        return parse(input, new Position(0, 0));  // Usamos una posición predeterminada de (0, 0)
    }

    public static LemmingRole parse(String input, Position position) {
        for (LemmingRole role : availableRoles) {
            if (role.canParse(input)) {
                return role.createInstance(position);
            }
        }
        throw new IllegalArgumentException(Messages.UNKNOWN_ROLE);
    } 
}
 