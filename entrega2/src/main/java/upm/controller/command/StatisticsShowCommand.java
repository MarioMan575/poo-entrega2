package upm.controller.command;

import upm.controller.UserController;

public class StatisticsShowCommand extends Command {
    private UserController controller;

    public StatisticsShowCommand(UserController controller) {
        this.controller = controller;
    }

    @Override
    public String apply(String[] params) {

        String result = super.testparams(params[0], "statistics-show", params.length - 1, 1);

        if (result != null && result.isEmpty()) {
            if (controller.getLoggedUser() == null || !controller.isPlayer()) {
                return "Error: You must be logged in as a player to view statistics.";
            }

            result = controller.statisticsShow(params[1]);
        }

        return result;
    }

    @Override
    public String toStringCommand() {
        return "statistics-show [-csv or -json]";
    }
}
