package class_loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/22
 */
public class MyClassLoader extends ClassLoader{
    //定义默认的class存放路径
    private final static Path DEFAULT_CLASS_DIR = Paths.get("F:","test");

    private final Path classDir;


    public MyClassLoader(){
        this.classDir = DEFAULT_CLASS_DIR;
    }
    public MyClassLoader(String classDir){
        this.classDir=Paths.get(classDir);
    }

    public MyClassLoader(String classDir,ClassLoader parent){
        super(parent);
        this.classDir = Paths.get(classDir);
    }


    @Override
    protected Class<?> findClass(String name)throws ClassNotFoundException{
        byte[] bytes = this.readClassBytes(name);
        if(null == bytes || bytes.length==0){
            throw new ClassNotFoundException("cann't load this class");
        }
        return this.defineClass(name,bytes,0,bytes.length);
    }


    /**
     * 找到class文件位置，把文件的内容变成字节数组
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    private byte[] readClassBytes(String name)throws ClassNotFoundException{
        String classPath = name.replace(".", File.separator);
        Path classFullPath = classDir.resolve(Paths.get(classPath+".class"));
        if(!classFullPath.toFile().exists()){
            throw new ClassNotFoundException("the class "+name+" not find.");
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            Files.copy(classFullPath,baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load class error");
        }
    }

    @Override
    public String toString(){
        return "my classLoader";
    }


}
