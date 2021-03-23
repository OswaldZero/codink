package visitor;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/23
 */
public interface Element {
    void accept(Visitor visitor);
}
class ElementA{
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
    public String operationA(){
        return "具体A元素的操作";
    }
}
class ElementB{
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
    public String operationB(){
        return "具体B元素的操作";
    }
}