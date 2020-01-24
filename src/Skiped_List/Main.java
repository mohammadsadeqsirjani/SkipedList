package Skiped_List;

public class Main {
    public static void main(String[] args) {
        ExpressLinkedList linkedList = new ExpressLinkedList();

        //add some nodes to to list
        linkedList.Insert(12);
        linkedList.Insert(3);
        linkedList.Insert(2);
        linkedList.Insert(7);
        linkedList.Insert(9);
        linkedList.Insert(10);

        System.out.println("Express : ");
        linkedList.PrintExpressList();
        System.out.println("\nNormal : ");
        linkedList.PrintNormalList();

        System.out.println("\nFind : \n" + linkedList.Find(2));
    }
}
