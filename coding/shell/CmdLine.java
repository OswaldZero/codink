package shell;

import shell.cmd.Cmd;
import shell.cmd.Ls;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/4/30
 */
public class CmdLine {
    private String path;
    private Cmd command;
    private String system;
    private CmdLine(){
        this.path = System.getProperty("user.dir");
        String property = System.getProperty("os.name");
        if (property.contains("Windows")){
            system="windows";
        }else {
            system="linux";
        }
    }

    public void setPath(String path){
        this.path=path;
    }

    public String getPath(){
        return this.path;
    }

    public void setCommand(Cmd command){
        this.command = command;
    }

    public String getSystem() {
        return system;
    }

    private static class InnerClass{
        private static CmdLine cmdLine = new CmdLine();
    }

    public static CmdLine getInstance(){
        return InnerClass.cmdLine;
    }

    public void execute() throws IOException {
        command.execute();
    }

}
