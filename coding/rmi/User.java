package rmi;

import java.io.Serializable;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/15
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name : " + this.name + ", age : " + this.age;
    }
}
