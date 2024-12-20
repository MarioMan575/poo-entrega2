package upm.controller.command;

import upm.controller.UserController;

public class PlayerCreateCommand extends Command {
    private UserController controller;

    public PlayerCreateCommand(UserController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        String result = super.testparams(params[0], "player-create",
                params.length - 1, 5);

        if (result != null && result.isEmpty()) {
            if (controller.getLoggedUser() == null) {
                return "Error: You must be logged in to create a player.";
            }

            if (!controller.isAdmin()) {
                return "Error: Only administrators can create players.";
            }

            return controller.createPlayer(params[1], params[2], params[3], params[4], params[5]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "player-create [email;password;firstName;lastName;dni]";
    }
}

