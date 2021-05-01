package shell.cmd;

import shell.CmdLine;
import shell.exceptions.ArgumentsException;

import java.io.File;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public class Cd extends Cmd{
    public Cd(CmdLine cmdLine,String[] args) throws ArgumentsException {
        this.cmdLine=cmdLine;
        this.arguments=args;
        argumentsCheck(arguments);
    }
    
    public CmdLine getCmdLine(){
        return this.cmdLine;
    } 
    
    @Override
    public void execute(){
        //这里职责分配感觉出了问题，执行的过程包含在参数检查里面了，但是又有点没办法，因为cd改变的仅仅是一个path而已
        //argumentsCheck弄个返回值，然后让这个返回值作为execute的参数传进来，感觉也不是很好
        //有想法的可以改进一下,还有下面的参数检查代码段太长了，太难看了，也可以改进一下
    }

    @Override
    public void argumentsCheck(String[] arguments) throws ArgumentsException {
        if(arguments.length!=2){
            throw new ArgumentsException("参数个数错误");
        }
        
        CmdLine cmdLine = getCmdLine();
        String system = cmdLine.getSystem();
        if(arguments[1].equals("..")){
            String path = cmdLine.getPath();
            //这个地方注意一下正则，不要直接用File.separator，会报错的
            String[] split = path.split("\\\\");
            //为window系统时
            if(system.equals("windows")){
                //检验是否为顶级目录的情况
                if (split.length==1){
                    path=split[0];
                }else {
                    path=path.substring(0,path.lastIndexOf(File.separator));
                }
            }else {//为linux系统时
                //检验是否为顶级目录的情况
                if (split.length==0){
                    //doNothing
                }else {
                    path=path.substring(0,path.lastIndexOf(File.separator));
                }
            }
            cmdLine.setPath(path);
        }else {
            if (system.equals("windows")){
                String[] split = arguments[1].split("\\\\");
                boolean contains = split[0].contains(":");
                //绝对路径
                if (contains){
                    File file = new File(arguments[1]);
                    if (!file.exists()){
                        throw new ArgumentsException("该路径不存在");
                    }
                    cmdLine.setPath(arguments[1]);
                }else {//相对路径
                    File file = new File(cmdLine.getPath(), arguments[1]);
                    if (!file.exists()){
                        throw new ArgumentsException("该路径不存在");
                    }
                    cmdLine.setPath(file.getAbsolutePath());
                }
            }else {
                boolean flag = arguments[1].startsWith("/");
                //绝对路径
                if (flag){
                    File file = new File(arguments[1]);
                    if (!file.exists()){
                        throw new ArgumentsException("该路径不存在");
                    }
                    cmdLine.setPath(arguments[1]);
                }else {//相对路径
                    File file = new File(cmdLine.getPath(), arguments[1]);
                    if (!file.exists()){
                        throw new ArgumentsException("该路径不存在");
                    }
                    cmdLine.setPath(file.getAbsolutePath());
                }
            }
        }



    }

}
