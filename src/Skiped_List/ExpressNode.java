package Skiped_List;

public class ExpressNode {
    int data;
    NormalNode current;
    ExpressNode next;

    public ExpressNode(int data, NormalNode normalNode){
        this.data = data;
        current = normalNode;
        next = null;
    }
}
