package upm.controller.command;

import upm.controller.UserController;

public class LogoutCommand extends Command {
    private final UserController controller;

    public LogoutCommand(UserController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        if (params == null || params.length != 1) {
            return "Invalid parameters. Usage: logout";
        }
        String command = params[0];
        String validationResult = super.testparams(command, "logout", params.length - 1, 0);
        if (validationResult != null && !validationResult.isEmpty()) {
            return validationResult;
        }
        boolean logoutSuccess = controller.logOut(params[1]);
        return String.valueOf(logoutSuccess);
    }

    @Override
    public String toStringCommand() {
        return "logout";
    }
}
