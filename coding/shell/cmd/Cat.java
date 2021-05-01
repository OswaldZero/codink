package shell.cmd;

import shell.CmdLine;
import shell.exceptions.ArgumentsException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public class Cat extends Cmd{
    public Cat(CmdLine cmdLine,String[] args) throws ArgumentsException {
        this.cmdLine=cmdLine;
        this.arguments=args;
        argumentsCheck(arguments);
    }
    @Override
    public void execute() throws IOException {
        File file = new File(cmdLine.getPath(), arguments[1]);
        FileReader fileReader = new FileReader(file);
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        int length;
        while ((length=fileReader.read(buffer))>0){
            stringBuilder.append(buffer,0,length);
        }
        String theShowedString=stringBuilder.toString();
        System.out.println(theShowedString);
    }

    @Override
    public void argumentsCheck(String[] arguments) throws ArgumentsException {
        //同样的这里我只检查一种相对地址，像.. 绝对地址啥的看Cd那里，基本上差不多的
        if (arguments.length!=2){
            throw new ArgumentsException("参数个数错误");
        }
        File file = new File(cmdLine.getPath(), arguments[1]);
        if (!file.exists()) {
            throw new ArgumentsException("文件地址不存在");
        }
    }
}
