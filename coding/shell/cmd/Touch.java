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
public class Touch extends Cmd{
    public Touch(CmdLine cmdLine,String[] args) throws ArgumentsException {
        this.cmdLine=cmdLine;
        this.arguments=args;
        argumentsCheck(arguments);
    }

    @Override
    public void execute() throws IOException {
        File file = new File(cmdLine.getPath(), arguments[1]);
        if (!file.exists()){
            file.createNewFile();
        }else {
            throw new IOException("文件已存在");
        }
    }

    @Override
    public void argumentsCheck(String[] arguments) throws ArgumentsException {
        if(arguments.length!=2){
            throw new ArgumentsException("参数个数错误");
        }
    }
}
