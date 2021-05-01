package shell.cmd;

import shell.CmdLine;
import shell.exceptions.ArgumentsException;

import java.io.IOException;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public class Echo extends Cmd{
    public Echo(CmdLine cmdLine,String input) throws ArgumentsException {
        this.cmdLine=cmdLine;
        this.arguments=new String[]{input.substring(input.indexOf(" ")+1)};
        argumentsCheck(arguments);
    }

    @Override
    public void execute(){
        System.out.println(arguments[0]);
    }

    @Override
    public void argumentsCheck(String[] arguments) throws ArgumentsException {
        //doNothing
    }
}
