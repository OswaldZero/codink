package shell;

import shell.cmd.*;
import shell.exceptions.ErrorCommandException;
import shell.exceptions.MyException;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public class Application {
    public static void main(String[] args) {
        CmdLine cmdLine = CmdLine.getInstance();
        Cmd cmd = null;
        while (true){
            System.out.print(cmdLine.getPath()+">");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("exit")){
                break;
            }
            String[] command = input.split(" ");
            //这里还是不建议像超哥那样用反射做，命名不正规
            try {
                switch (command[0]) {
                    case "ls":
                        cmd = new Ls(cmdLine,command);
                        break;
                    case "cd":
                        cmd = new Cd(cmdLine,command);
                        break;
                    case "pwd":
                        cmd = new Pwd(cmdLine,command);
                        break;
                    case "grep":
                        //grep这里有个小问题，就是不能用split空格分割,因此做一下特殊处理
                        //另外这里文件地址只支持相对目录，具体的改进方法见Grep文件。
                        cmd = new Grep(cmdLine,input);
                        break;
                    case "echo":
                        //echo这边问题也是不能以空格分割的
                        cmd = new Echo(cmdLine,input);
                        break;
                    case "cat":
                        //另外这里文件地址只支持相对目录，具体的改进方法见Cat文件。
                        cmd = new Cat(cmdLine,command);
                        break;
                    case "rm":
                        cmd = new Rm(cmdLine,command);
                        break;
                        //后面这三个写的也是有点随意，毕竟在一天内有点赶，可以补充修改
                    case "touch":
                        //只支持相对目录
                        cmd = new Touch(cmdLine, command);
                        break;
                    case "mkdir":
                        //只支持相对目录
                        cmd = new Mkdir(cmdLine, command);
                        break;
                    case "cp":
                        //只支持绝对目录
                        cmd = new Cp(cmdLine,command);
                        break;
                    default:
                        throw new ErrorCommandException("命令错误，该命令行不包含此命令");
                }
                cmdLine.setCommand(cmd);
                cmdLine.execute();
            }catch (MyException | IOException myException){
                //这里用System.err.println()会出现排版问题，可能是err优先级低一些
                System.out.println(myException.getMessage());
            }
        }
    }
}
