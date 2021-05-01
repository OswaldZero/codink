package shell.cmd;

import shell.CmdLine;
import shell.exceptions.ArgumentsException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public class Rm extends Cmd{
    public Rm(CmdLine cmdLine,String[] args) throws ArgumentsException {
        this.cmdLine=cmdLine;
        this.arguments=args;
        argumentsCheck(arguments);
    }

    @Override
    public void execute() throws IOException {
        File file = new File(cmdLine.getPath(), arguments[1]);
        delete(file);
    }

    @Override
    public void argumentsCheck(String[] arguments) throws ArgumentsException {
        if(arguments.length!=2){
            throw new ArgumentsException("参数个数错误");
        }
        //这里也是只支持相对目录
        File file = new File(cmdLine.getPath(), arguments[1]);
        if (!file.exists()) {
            throw new ArgumentsException("不存在该地址");
        }
    }

    /**
     * 递归删除
     * @param file
     */
    public void delete(File file){
        if(file.isFile()){
            file.delete();
        }else {
            File[] files = file.listFiles();
            for (File theFile:files) {
                delete(theFile);
            }
            if(file.exists()){
                file.delete();
            }
        }
    }
}
