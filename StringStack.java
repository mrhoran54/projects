/* File: StringStack.java
 * Date: 2/25/2016
 * Author: Megan Horan (mrhoran@bu.edu)
 * Class: CS 112, Spring 2016
 * Purpose: Thisclass is a general Stack class, with the functions, push, pop, size and empty!
 * It will use the ideas introduced surrounding linked lists
 */

public class StringStack {
    
    private Node head = null; 
    private String top ="";
    
    private int size = 0;
    
    public class Node { 
        public String item; 
        public Node next; 
        
        // constructors 
        public Node() { 
            item = ""; 
            next = null; 
        } 
        
        public Node(String n) { 
            item = n; 
            next = null; 
        } 
        
        public Node(String n, Node p) { 
            item = n; 
            next = p;         
        } 
    }
    
    //add nodes to the list and change what head points to 
    public void push(String n) {
        // create a new node
        Node p = new Node(n);
        // make this new node point to the previous node held in head
        p.next = head;
        //make head point to the newly added Node!
        head = p;
        ++size;
        
    }
    // this function is alot like the stack variant of pop, removing the head value from the list
    public String pop() throws StackUnderflowException{
        
       
        //check to see if there is an exception 
        if (isEmpty() == true)
            throw new StackUnderflowException();
        //else pop items by setting head to head.next, ie the next value head points to in the linked list
        
        String tmp = head.item;
        // make head point to the next node, effectively erasing it from the list
        head = head.next; 
        //increment size
        --size;
        return tmp;
    }
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        if (size != 0)
            return false;
        else
            return true;
    }
    //return the item at the node that head points to, throw an exception if it overflows
    public String top() throws StackUnderflowException {
        
        if (isEmpty() == true)
            throw new StackUnderflowException();
        else
            
            return head.item;
    }
    
    // wrapper method: just calls recursive helper
    public String toString() {
        return "| " + toStringAux(head);
    }
    
    // recursive helper method
    private String toStringAux(Node p) {
        if(p == null)
            return "";
        else
            return toStringAux(p.next) + " " + p.item ;
    }
    
    
    public static void main(String[] args) {
        
        StringStack S = new StringStack(); 
        
        System.out.println("\n[1] First test toString on empty StringStack... Should print out:"); 
        System.out.println("| "); 
        System.out.println(S); 
      
        // Use step-wise refinement to develop your program: Move the left comment marker "/*"
        // down past one test at a time as you develop each of the methods
        
             
        System.out.println("\n[2] Test size and isEmpty... Should print out:\n0  true"); 
        System.out.println(S.size() + "  " + S.isEmpty()); 
        
        System.out.println("\n[3] Push 4 strings... Should print out:\n|  looney a is Trump"); 
        S.push("looney");
        S.push("a");
        S.push("is");
        S.push("Trump");
        System.out.println(S);
         
        System.out.println("\n[4] Test size and isEmpty... Should print out:\n4  false"); 
        System.out.println(S.size() + "  " + S.isEmpty()); 
       
        
        System.out.println("\n[5] Test top... Should print out:\nTrump"); 
        try {
            System.out.println(S.top());
        }
        catch(StackUnderflowException e) {
            System.err.println("Stack Overflow!");
        }
        
       
    
        System.out.println("\n[6] Pop and print... Should print out:\nTrump is a looney");
        while(!S.isEmpty()) {
            try {
                String s = S.pop(); 
                System.out.print(s+" ");
            }
            catch(StackUnderflowException e) {
                System.err.println("Stack Overflow!");
            }
        }
        System.out.println(); 
        
        System.out.println("\n[7] Testing stack underflow with top... Should print out:");
        System.err.println("Stack Overflow!");
        try {
            
            System.out.print(S.top());
        }
        catch(StackUnderflowException e) {
            System.err.println("Stack Overflow!");
        }  
        
        System.out.println("\n[8] Testing stack underflow with pop... Should print out:");
        System.err.println("Stack Overflow!");
        try {
            String s = S.pop(); 
            System.out.print(s+" ");
        }
        catch(StackUnderflowException e) {
            System.err.println("Stack Overflow!");
        } 
        
        System.out.println("\n[9] Just for fun... Should print out a long String!");
        try {
            S.push("looney! ");
            S.push("a ");
            S.push("is ");
            S.push("Trump ");
            
            String s = "";
            while(!S.isEmpty())
                s += S.pop();
            
            for(int i = 0; i < 5; ++i)
                S.push(s);
            S.push("\n");
            
            String t = "";
            while(!S.isEmpty())
                t += S.pop();
            
            for(int i = 0; i < 5; ++i)
                S.push(t);
            
            while(!S.isEmpty())
                System.out.println(S.pop());
            
        }
        catch(StackUnderflowException e) {
            System.err.println("Stack Overflow!");
        }
       
    }
}

class StackUnderflowException extends Exception {}
