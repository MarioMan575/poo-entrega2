package upm.controller.command;

import upm.controller.UserController;

public class PlayerDeleteCommand extends Command {
    private final UserController controller;

    public PlayerDeleteCommand(UserController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {
        String result=super.testparams(params[0],"delete_task",
                params.length-1,1);

        if (result!=null&&result.isEmpty())
            if (controller.getLoggedUser()!=null)
                result = controller.deletePlayer(params[1]);
            else
                result = "Command not apply until you are logued";

        return result;
    }

    @Override
    public String toStringCommand() {
        return "delete [email]";
    }
}
