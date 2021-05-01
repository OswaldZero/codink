package shell.cmd;

import shell.CmdLine;
import shell.exceptions.ArgumentsException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public class Cp extends Cmd{
    public Cp(CmdLine cmdLine,String[] args) throws ArgumentsException {
        this.cmdLine=cmdLine;
        this.arguments=args;
        argumentsCheck(arguments);
    }

    @Override
    public void execute() throws IOException {
        File sourceFile = new File(arguments[1]);
        File dstFile = new File(arguments[2]);

        if(!dstFile.exists()){
            dstFile.createNewFile();
        }

        FileReader fileReader = new FileReader(sourceFile);
        FileWriter fileWriter = new FileWriter(dstFile);
        char[] buffer = new char[1024];
        int length;
        while ((length=fileReader.read(buffer))>0){
            fileWriter.write(buffer,0,length);
        }
        fileWriter.close();
        fileReader.close();
    }

    @Override
    public void argumentsCheck(String[] arguments) throws ArgumentsException {
        if(arguments.length!=3){
            throw new ArgumentsException("参数个数错误");
        }
        File fileSource = new File(arguments[1]);
        if (!fileSource.exists()){
            throw new ArgumentsException("参数源地址不存在");
        }
        String desDir = arguments[2].substring(0, arguments[2].lastIndexOf(File.separator));
        File file = new File(desDir);
        if (!file.exists()){
            throw new ArgumentsException("参数目标地址文件夹不存在");
        }

    }
}
