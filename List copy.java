package project1;

public class List {

    // Represent the head and tail of the singly linked list
    public Node head = null;
    public Node tail = null;
    private Node currentPos = head;
    // private Node list;
    private Node location;
    private Node previous;
    boolean found = false;
    private int numElements = 0;

    public List() {
        // this.numElements = 0;
        // list = null;
    }

    public void find(String patientID) {
        // finds the patient from the patient ID
        location = head;
        found = false;
        while (location != null) {
            if (location.patient.getID().equals(patientID)) {
                found = true;
                return;
            } else {
                previous = location;
                location = location.next;
            }
        }

    }

    public int size() {
        return numElements;
    }

    // public double getAverageAge() {
    // Node currNode = head;
    // double averageAge = 0;
    // while (currNode != null) {
    // averageAge += currNode.patient.getAge();
    // currNode = currNode.next;
    // }
    // return averageAge / numElements;
    // }

    // public Patient getYoungest() {
    // Node currNode = head;
    // int youngest = Integer.MAX_VALUE;
    // Patient patient = null;
    // while (currNode != null) {
    // if (currNode.patient.getAge() < youngest) {
    // patient = currNode.patient;
    // }
    // currNode = currNode.next;
    // }
    // return patient;
    // }

    // public String getOverdue() {
    // Node currNode = head;
    // int overdue;
    // String listString = "List :/ n";
    // while (currNode != null) {
    // overdue = currNode.patient.get_time_since_last_visit();
    // if (overdue >= 3) {
    // listString = listString + " " + currNode.patient.getName() + ", " +
    // currNode.patient.getID() + "\n";
    // }
    // currNode = currNode.next;
    // }
    // return listString;
    // }

    public boolean contain(String patientID) {
        // returns a boolean that shows whether the patient exists in the list
        find(patientID);
        return found;
    }

    public boolean remove(String patientID) {
        // remove() finds and removes a patient selected
        find(patientID);
        if (found) {
            if (head == location)
                head = head.next;
            else
                previous.next = location.next;
            numElements--;
        }
        return found;
    }

    public boolean isEmpty() {
        // checks if list is empty
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void add(Patient patient) {
        // add() adds a new patient at the end of the list
        Node newNode = new Node(patient);
        if (tail == null && head == null) {
            tail = newNode;
            head = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
        numElements++;

    }

    public Patient get(String patientID) {
        // get() returns the patient entered, if not exsits, returns null
        find(patientID);
        if (found)
            return location.patient;
        else
            return null;

    }

    public Patient getNext() {
        // getNext() returns the current patient info, if current patient is the last
        // patient, it returns the first patient
        Patient next = currentPos.patient;
        if (currentPos.next == null) {
            currentPos = head;
        } else {
            currentPos = currentPos.next;
        }
        return next;
    }

    public void reset() {
        // resets the current postition to the head
        currentPos = head;
    }

    public String toString() {
        // returns a nicely formatted string to represent the list
        Node currNode = head;
        String listString = "";
        while (currNode != null) {
            listString = listString + currNode.patient.getName() + ", " + currNode.patient.getID() + "\n";
            currNode = currNode.next;
        }
        return listString;

    }

}