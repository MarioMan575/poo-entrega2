package upm.controller.command;

import upm.controller.UserController;

public class PlayerDeleteCommand extends Command {
    private final UserController controller;

    public PlayerDeleteCommand(UserController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        String result=super.testparams(params[0],"player-delete",
                params.length-1,1);

        if (result != null && result.isEmpty()) {
            if (controller.getLoggedUser() == null) {
                return "Error: You must be logged in to delete a player.";
            }

            if (!controller.isAdmin()) {
                return "Error: Only admins can delete players.";
            }
            
            result = controller.deletePlayer(params[1]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "player-delete [email]";
    }
}
