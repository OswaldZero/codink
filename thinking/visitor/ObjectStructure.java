package visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Wuyihu
 * @version 1.0
 * @date 2021/3/23
 */
public class ObjectStructure {
    private List<Element> list = new ArrayList<>();
    public void accept(Visitor visitor){
        Iterator<Element> i = list.iterator();
        while (i.hasNext()){
            ((Element)i.next()).accept(visitor);
        }
    }

    public void add(Element element){
        list.add(element);
    }

    public void remove(Element element){
        list.remove(element);
    }

}
