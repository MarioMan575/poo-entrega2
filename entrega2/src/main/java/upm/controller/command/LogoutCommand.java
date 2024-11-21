package upm.controller.command;

import upm.controller.UserController;

public class LogoutCommand extends Command {
    private final UserController controller;

    public LogoutCommand(UserController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {

        String result = super.testparams(params[0], "logout", params.length - 1, 0);

        if (result != null && result.isEmpty()) {
            if (controller.getLoggedUser() == null) {
                return "Error: No user is currently logged in.";
            }

            result = controller.logout();
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "logout";
    }
}
