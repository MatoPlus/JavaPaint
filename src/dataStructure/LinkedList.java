package dataStructure;

/*
 * Author: Ri Xin Yang
 * Date: April 19, 2019
 * Desc: This class is the LinkedList data structure as defined by the LinkedListADT interface. 
 * This acts as a dynamic list using nodes. Note that <T> represents a generic object type.
 * This object type needs to be defined when instantiating the linked list.
 */
public class LinkedList<T> implements LinkedListADT<T> {
    private ListNode<T> front = null;
    private int numberOfNodes = 0; 

    // Returns true if the linked list has no nodes, or false otherwise.
    @Override
    public boolean isEmpty() {
        return (front == null);
    }

    // Deletes all of the nodes in the linked list.
    // Note: ListNode objects will be automatically garbage collected by JVM.
    @Override
    public void clear() {
        front = null;
        numberOfNodes = 0;
    }

    // Returns the number of nodes in the linked list
    @Override
    public int size() {
        return numberOfNodes;
    }
    
    // Adds a node to the front of the linked list.
    @Override
    public void addFirst(T element) {
        front = new ListNode<T>(element, front);
        numberOfNodes++;
    }

    // Add an element to the end of the linked list
    @SuppressWarnings("unchecked")
    public void addLast(T element) {
        
        if (isEmpty()) {
            addFirst(element);
        }

        else{
            ListNode<T> lastNode;
            ListNode<T> newNode;
            lastNode = front;
            
            // traverse list
            while ((lastNode.getNext() != null)) {
                lastNode = lastNode.getNext();
            }
            
            // create new node and set to last via reference
            newNode = new ListNode<T>(element, null);
            lastNode.setNext(newNode);
            numberOfNodes++;
        }
    }

    // Returns a reference to the data in the first node, or null if the list is empty.
    @Override
    public T peekFirst() {
        if (isEmpty()) 
            return null;
        
        return front.getData();
    }

    // Removes a node from the front of the linked list (if there is one).
    // Returns a reference to the data in the first node, or null if the list is empty.
    @Override
    @SuppressWarnings("unchecked")
    public T removeFirst() {
        T tempData;
        
        if (isEmpty()) 
            return null;
        
        tempData = front.getData();
        front = front.getNext();
        numberOfNodes--;
        return tempData;
    }

    // Removes a node from the end of the linked list (if there is one).
    // Returns a reference to the data in the first node, or null if the list is empty.
    @SuppressWarnings("unchecked")
    public T removeLast() {
        T tempData;
        ListNode<T> previousNode = null;
        ListNode<T> lastNode = null;

        if (isEmpty())
            return null;
        else if (size() == 1)
            return removeFirst();

        previousNode = front;
        lastNode = front.getNext();

        // traverse list
        while ((lastNode.getNext() != null)) {
            previousNode = lastNode;
            lastNode = lastNode.getNext();
        }

        tempData = lastNode.getData();
        previousNode.setNext(null);
        numberOfNodes--;
        return tempData;
    }

    // Returns true if the linked list contains a certain element, or false otherwise.
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(T key) {
        ListNode<T> searchNode;
        searchNode = front;
        while ((searchNode != null) && (!key.equals(searchNode.getData()))) {
            searchNode = searchNode.getNext();
        }
        return (searchNode != null);
    }

    // Return String representation of the linked list.
    @Override
    @SuppressWarnings("unchecked")
    public String toString() {
        ListNode<T> node;
        String linkedList = "FRONT ==> ";
        
        node = front;
        while (node != null) {
            linkedList += "[ " + node.getData() + " ] ==> ";
            node = node.getNext();
        }
        
        return linkedList + "NULL";
    }
}




