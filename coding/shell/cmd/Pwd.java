package shell.cmd;

import shell.CmdLine;
import shell.exceptions.ArgumentsException;

import javax.sound.midi.Soundbank;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public class Pwd extends Cmd{
    public Pwd(CmdLine cmdLine, String[] arguments) throws ArgumentsException {
        this.cmdLine=cmdLine;
        this.arguments=arguments;
        argumentsCheck(arguments);
    }

    @Override
    public void execute() {
        System.out.println(cmdLine.getPath());
    }

    @Override
    public void argumentsCheck(String[] arguments) throws ArgumentsException {
        if (arguments.length!=1){
            throw new ArgumentsException("pwd 命令不接受参数");
        }
    }
}
