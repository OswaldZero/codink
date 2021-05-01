package shell.cmd;

import shell.CmdLine;
import shell.exceptions.ArgumentsException;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/5/1
 */
public class Grep extends Cmd{
    public Grep(CmdLine cmdLine,String input) throws ArgumentsException {
        this.cmdLine=cmdLine;
        String arg1 = input.substring(0,input.indexOf(" "));
        String arg2 = input.substring(input.indexOf("\"")+1,input.lastIndexOf("\""));
        String arg3 = input.substring(input.lastIndexOf(" ")+1);
        arguments = new String[]{arg1, arg2, arg3};
        argumentsCheck(arguments);
    }
    @Override
    public void execute() throws IOException {
        File file = new File(cmdLine.getPath(), arguments[2]);
        FileReader fileReader = new FileReader(file);
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        int length;
        while ((length=fileReader.read(buffer))>0){
            stringBuilder.append(buffer,0,length);
        }
        String theSourceString=stringBuilder.toString();
        Pattern pattern = Pattern.compile(arguments[1]);
        Matcher matcher = pattern.matcher(theSourceString);
        //这里假如要输出行号的话那么就要把这source按回车换行分割,然后遍历分割完的字符串数组，然后按照对应的序号+1就是行号
        //但是实际上grep命令只有加 -b 等其它参数才会显示字符所在的index，也不会显示行数。
        //另外回车换行window和linux系统也是不一样的一个是\r\n,一个是\n
        while (matcher.find()){
            //在这里可以用matcher.start()和end()得到所在的字符的index
            System.out.println(matcher.group());
        }
    }

    @Override
    public void argumentsCheck(String[] arguments) throws ArgumentsException {
        //判断参数个数
        if (arguments.length!=3){
            throw new ArgumentsException("参数个数错误");
        }
        //校验第二个参数，其实第二个参数可以绝对路径，也可以是相对路径，这里懒得写就用相对路径判断
        //绝对路径和相对路径判断在Cd文件那边代码有，可以看看
        File file = new File(cmdLine.getPath(), arguments[2]);
        if(!file.exists()){
            throw new ArgumentsException("文件位置错误");
        }
    }
}
