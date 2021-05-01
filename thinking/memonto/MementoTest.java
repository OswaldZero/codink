package memonto;

import java.util.Stack;

/**
 * 备忘录：存储东西
 * 发起者：创建和恢复状态
 * 管理者：保存于获取备忘录状态
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/29
 */
public class MementoTest {
}

class Memento{
    String word;
    public Memento(String word){
        this.word=word;
    }
    public String getWord(){
        return word;
    }
    public void setWord(String word){
        this.word = word;
    }
}

class Originator{
    private Memento memento;
    public Originator(String word){
        this.memento = new Memento(word);
    }
    public Memento createMemento(String word){
        this.memento.setWord(word);
        return this.memento;
    }
    public void restoreMemento(Memento memento){
        this.memento=memento;
    }
}

class Caretaker{
    private Stack<Memento> stack;
    public void push(Memento memento){
        stack.push(memento);
    }
    public Memento pop(){
        return stack.pop();
    }
}