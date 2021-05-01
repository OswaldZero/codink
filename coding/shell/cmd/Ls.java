package shell.cmd;

import shell.CmdLine;
import shell.exceptions.ArgumentsException;

import java.io.File;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public class Ls extends Cmd{

    public Ls(CmdLine cmdLine,String[] args) throws ArgumentsException {
        //感觉这里其实还可以抽象一个父类出来,然后调用super(cmdLine,args),毕竟这里的操作都相同
        this.cmdLine = cmdLine;
        this.arguments = args;
        argumentsCheck(args);
    }

    @Override
    public void execute(){
        File file = new File(this.cmdLine.getPath());
        //这里file不需要检查exist，因为其它机制已经保证了他的准确性
        File[] files = file.listFiles();
        for (File theFile : files) {
            System.out.println(theFile.getName());
        }
    }

    @Override
    public void argumentsCheck(String[] args) throws ArgumentsException {
        if (arguments.length!=1){
            throw new ArgumentsException("ls 命令不接受参数");
        }
    }
}
