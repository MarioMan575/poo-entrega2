package upm.controller.command;

public abstract class Command {
    public abstract String apply(String[] params);

    public String testparams(String commandIn, String commandProposed, int paramsNumberIn, int paramsNumberProposed) {
        String result = null;
        if (commandIn.toLowerCase().equals(commandProposed)) {
            if (paramsNumberIn == paramsNumberProposed) {
                result = "";
            } else {
                result = "Number of params incorrect : expected " + paramsNumberIn
                        + "found" + paramsNumberProposed;
            }
        }

        return result;
    }

    public String testparamsMin(String commandIn, String commandProposed, int paramsNumberIn, int paramsNumberProposed) {
        String result = null;

        if (commandIn.toLowerCase().equals(commandProposed))
            if (paramsNumberIn >= paramsNumberProposed) {
                result = "";
            } else
                result = "Number of params incorrect: expected " + paramsNumberIn
                        + " found " + paramsNumberProposed;

        return result;

    }

    public abstract String toStringCommand();
}

