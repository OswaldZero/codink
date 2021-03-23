package visitor;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/23
 */
public interface Visitor {
    void visit(ElementA elementA);
    void visit(ElementB elementB);
}

class VisitorA{
    public void visit(ElementA elementA){
        System.out.println("具体访问者A访问--->" + elementA.operationA());
    }
    public void visit(ElementB elementB){
        System.out.println("具体访问者B访问--->" + elementB.operationB());
    }
}


