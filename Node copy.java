package project1;
public class Node {

    int key;
    Node next;
    Patient patient;

    public Node(Patient patient) {
        this.patient = patient;
        // this.key = key;
        this.next = null;
    }

}
