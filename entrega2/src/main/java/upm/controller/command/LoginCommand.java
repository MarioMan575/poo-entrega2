package upm.controller.command;

import upm.controller.UserController;

public class LoginCommand extends Command {
    private UserController controller;

    public LoginCommand(UserController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {

        String result = super.testparams(params[0], "login",
                params.length - 1, 2);
        if (result != null && result.equals("")) {
            result = String.valueOf(controller.login(params[1], params[2]));
        }
        return result;
    }

    @Override
    public String toStringCommand() {
        return "login [username;password]";
    }
}
