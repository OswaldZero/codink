package shell.cmd;

import shell.CmdLine;
import shell.exceptions.ArgumentsException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public abstract class Cmd {
    protected CmdLine cmdLine;
    protected String[] arguments;
    public abstract void execute() throws IOException;
    public abstract void argumentsCheck(String[] arguments) throws ArgumentsException;
}
