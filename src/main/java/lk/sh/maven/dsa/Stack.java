package lk.sh.maven.dsa;

public class Stack {

    Node top = null;

    public void push(int data) {

        //Node constructer ekte method eke parameter pass
        Node node = new Node(data);

        //node eke next part
        node.next = top;
        top = node;

    }

    public void PrintStack() {
        Node temp = top;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public void pop() {
        top = top.next;

    }
}
