package shell.cmd;

import shell.CmdLine;
import shell.exceptions.ArgumentsException;

import java.io.File;
import java.io.IOException;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public class Mkdir extends Cmd{
    public Mkdir(CmdLine cmdLine,String[] arguments) throws ArgumentsException {
        this.cmdLine=cmdLine;
        this.arguments=arguments;
        argumentsCheck(arguments);
    }
    @Override
    public void execute() throws IOException {
        File file = new File(cmdLine.getPath(), arguments[1]);
        if(file.exists()){
            throw new IOException("文件夹已存在");
        }
        file.mkdirs();
    }

    @Override
    public void argumentsCheck(String[] arguments) throws ArgumentsException {
        if (arguments.length!=2){
            throw new ArgumentsException("参数个数错误");
        }
    }
}
