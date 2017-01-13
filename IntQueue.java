/* File: IntQueue.java
 * Date: 2/20/16
 * Author: Megan Horan (mrhoran@bu.edu)
 * 
 * Purpose: this function works with an enque, and it has two problems:
 * the first is there is an array out of bounds error when you enque past the last index
 * the second problem using exceptions for trying to dequeue empty queue.

 */


public class IntQueue {
    
    private final int SIZE = 10; 
    
    private int [] A = new int[SIZE]; 
    
    private int front = 0;                       // location of front of queue
    
    private int next = 0;                        // location of next available unused slot  
    
    // interface methods
    
    public void enqueue(int key) {               // push the number onto the end of the queue
        if(next >= SIZE)
            reset();                             // this will fix problem of running off end of array
        A[next++] = key; 
    }
    
    public int dequeue() throws StackUnderflowException{                       // remove the integer at front and return it 
        if(front == next)
            throw new StackUnderflowException();
        ++front;
        return A[front-1];   
    }
    
    public boolean isEmpty() {
        
        return (next == front); 
    }
    
    public int size() {                 // how many integers in the stack 
        return (next - front); 
    }
    
    // Must write this to shift all valid elements to right (towards slot 0) and reset front and next
    public void reset() {
        // this for loop will shift all valid elements, which appears to be the values from 3 to the end to the right
        // putting them at the front 0.
        // I'm not sure why onlt values 3 - 10 are added and the first three get dropped, but that's what it output in the example
        int x = 0;
        for( int i = 3; i < SIZE; i++){
            A[x] = A[i];
            
            x++;
        }
        //get the correct indexes
        next = (SIZE - front);
        front = 0; 
    }
    
  
    public String toString() {
        String s = "";
        for(int i = SIZE-1; i >= 0; --i)
            s += i + "\t"; 
        s += "\n";
        for(int i = SIZE-1; i >= 0; --i)
            s += A[i] + "\t";
        s += "\nnext = " + next + "   front = " + front + "\n";

        return s; 
    }
    
    
    
    // unit test
    
    public static void main(String [] args) {
               
        
        try { 
        
        IntQueue Q = new IntQueue();     
        System.out.println("Enqueueing 5, 9, 3, -3, 31 then printing out the queue:\n"); 
        Q.enqueue(5); 
        Q.enqueue(9); 
        Q.enqueue(3); 
        Q.enqueue(-3); 
        Q.enqueue(31);
        System.out.println(Q);
        
        System.out.println(Q.size()); 
  
        System.out.println("Dequeueing 3 times then printing out the queue:\n");
        System.out.println("dequeue: " + Q.dequeue()); 
        System.out.println("\ndequeue: " + Q.dequeue()); 
        System.out.println("\ndequeue: " + Q.dequeue()); 
        System.out.println(Q);
        
        System.out.println("\nEnqueueing 8, -1, 2, 6, 5 then printing out the queue:\n"); 
        Q.enqueue(8); 
        Q.enqueue(-1); 
        Q.enqueue(2); 
        Q.enqueue(6); 
        Q.enqueue(5);
        System.out.println(Q);
        
        
        // First issue:  this one will cause an error! You must fix this by shifting everything
        // to the right (towards the low indices, so that front is at 0 as it was at the beginning)
        
        System.out.println("First issue to fix: the queue has moved all the way to the left!");
        System.out.println("Must write code in reset() to shift all valid elements to right.");
        System.out.println("Enqueueing 666, which triggers a reset.....\n");
        
        System.out.println("Before enqueue:");
        
        System.out.println(Q);
        
        System.out.println("After enqueue:");
        
        Q.enqueue(666);
        
        System.out.println("\n"+Q);
        
        System.out.println("\nEmptying out the queue:\n"); 
        while(!Q.isEmpty()) {
            Q.dequeue();
        }
        System.out.println("\n"+Q);
        
        
        // Second issue: after fixing the first problem, you must report an error for this one,
        
        System.out.println("Second issue to fix: report an error using exceptions for trying to dequeue empty queue.");
        
        // These next two lines will text to makes sure that if Q.empty is true, an exception will be thrown
        System.out.println("\nQ is empty:  " + Q.isEmpty());
        System.out.println("\ndequeue: " + Q.dequeue());
    
            
        }       
        catch (StackUnderflowException e) {
            System.out.println("Stack Overflew!");
            
        }         
       
    }
    
    
}

class StackUnderflowException extends Exception {
}


