package upm.controller.command;

public abstract class Command {
    public abstract String apply(String[] params);

    public String testparams(String commandin, String commandproposed,
                             int paramsNumberin, int paramsNumberproposed){
        String result = null;
        if (commandin.toLowerCase().equals(commandproposed)){
            if (paramsNumberin==paramsNumberproposed){
                result = "";
            }else{
                result = "Number of params incorrect : expected "+paramsNumberin
                        +"found"+paramsNumberproposed;
            }
        }

        return result;
    }

    public abstract String toStringCommand();
}

