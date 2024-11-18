package tp1.control.commands;

import java.util.Arrays;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;

public class SetRoleCommand extends NoParamsCommand {

    private Position position;  // Para almacenar la posición del lemming
    private String roleInput;   // Para almacenar el rol de lemming

    private static final String NAME = "setRole";  // Nombre completo del comando
    private static final String SHORTCUT = "sr";   // Abreviatura del comando
    private static final String DETAILS = "[s]et[R]ole ROLE ROW COL: sets the lemming in position (ROW,COL) to role ROLE";
    private static final String HELP = "Assign a role to a lemming at a given position.";

    public SetRoleCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);  // Constructor de la clase base
    }

    // Este método se utilizará para manejar la entrada del usuario y configurar la posición y el rol
    public void setParameters(String roleInput, Position position) {
        this.roleInput = roleInput;
        this.position = position;
    }

    @Override
    public void execute(GameModel game, GameView view) {
        // Verifica si la posición y el rol están configurados correctamente
        if (position == null || roleInput == null) {
            System.out.println("[ERROR] Position or role not set.");
            return;
        }

        // Parsear el rol a partir de la entrada
        LemmingRole role = LemmingRoleFactory.parse(roleInput, position);

        // Si el rol es inválido
        if (role == null) {
            System.out.println("[ERROR] Invalid role: " + roleInput);
            return;
        }

        // Intentar asignar el rol en la posición dada
        boolean success = game.setRoleAt(position, role);

        if (success) {
            System.out.println("[INFO] Role set successfully.");
        } else {
            System.out.println("[ERROR] No lemming found at the given position.");
        }
    }

    @Override
    public Command parse(String[] commandWords) {
        // Verifica que la cantidad de parámetros sea correcta (considerando que el atajo es commandWords[0])
        if (commandWords.length != 4) { // Necesitamos 4 palabras: "sr", "Parachuter", "3", "3"
            System.out.println("[ERROR] Invalid number of parameters");
            return null;
        }

        String role = commandWords[1];  // El primer parámetro después de "sr" es el rol
        int row, col;

        try {
            row = Integer.parseInt(commandWords[2]);  // El segundo parámetro es la fila
            col = Integer.parseInt(commandWords[3]);  // El tercer parámetro es la columna
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid row/col values.");
            return null;
        }

        // Crear la posición a partir de la fila y columna
        Position position = new Position(row, col);

        // Crear una nueva instancia del comando y configurarlo con los parámetros
        SetRoleCommand command = new SetRoleCommand();
        command.setParameters(role, position);

        return command;
    }
}
