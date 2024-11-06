package upm.controller.command;

import upm.controller.UserController;

public class PlayerCreateCommand extends Command {
    private UserController userController;

    public PlayerCreateCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public String apply(String[] params) {
        if (params.length != 5) {
            return "Invalid number of arguments.";
        }
        boolean success = userController.registerPlayer(params[0], params[1], params[2], params[3], params[4]);
        if (success) {
            return "Player created successfully.";
        } else {
            return "Player already exists.";
        }
    }

    @Override
    public String toStringCommand() {
        return "player-create [username; password; firstName; lastName; dni]";
    }
}

