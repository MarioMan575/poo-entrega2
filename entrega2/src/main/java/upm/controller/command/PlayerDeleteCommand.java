package upm.controller.command;

import upm.controller.PlayerController;

public class PlayerDeleteCommand extends Command {
    private final PlayerController controller;

    public PlayerDeleteCommand(PlayerController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        if (params == null || params.length != 2) {
            return "Invalid parameters. Usage: delete [playerId]";
        }

        String command = params[0];
        String playerId = params[1];

        String validationResult = super.testparams(command, "delete", params.length - 1, 1);
        if (validationResult != null && !validationResult.isEmpty()) {
            return validationResult;
        }

        boolean deleteSuccess = controller.deletePlayer(playerId);
        return String.valueOf(deleteSuccess);
    }

    @Override
    public String toStringCommand() {
        return "delete [playerId]";
    }
}
