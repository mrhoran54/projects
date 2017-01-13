/* File: IntDeque.java
 * Date: 2/19/2016
 * Author:  Megan Horan (mrhoran@bu.edu)
 * Purpose: this function is going to create an queque using the circle buffer implementation
 * It will have another enqueue and dequeue function
 */

import java.util.Arrays;
    
public class IntDeque {
  
    private final int SIZE = 10; 
    public int [] A = new int[SIZE];
    
    private int front = 0;                       // location of front of queue
    private int next = 0;                        // location of next available unused slot  
    private int size = 0;
    
    public int dequeueFront() throws QueueUnderflowEception {
        // Remove the int at the front of the queue and return it
        
        // check that there is a QueueUnderflowEception
        if(size <= 0)
            throw new QueueUnderflowEception();
        
        int temp = A[front];
        //this next line makes sure it goes in a circle: so when you get to 9 the pointer, front, will move to zero
        front = nextSlot(front);
        --size;
        return temp;
        
    }
    
    public void enqueueRear(int k){
    // this function will insert a new int at the next of the queue
        
        // if its full make a new array double the size
        if (front == next && size() == SIZE)
            resize();
        else {
            A[next] = k;
            //this next line makes sure it goes in a circle: so when you get to 9 the pointer, front, will move to zero
            next = nextSlot(next);
            ++size;
        }
    }
    
    public int dequeueRear() throws QueueUnderflowEception {
      // same idea as the dequeueFront but instead it removes elements starting from the next value
        //this function moves backwards using previousSLot, unlike the other dequeue function
        
        // check that there is a QueueUnderflowEception
        if(size <= 0)
            throw new QueueUnderflowEception();
        
        
        //get the previous value
        next = previousSlot(next);
        //return the value being dequeue
        int temp = A[(next)];
        --size;
        return temp;
        
    }
    
    public void enqueueFront(int k){
     // this function will insert a new int at the index at front in the queue
        //checks if there are too many values and will resize if so
       if (front == next && size() == SIZE)
            resize();
       else {
           //move backwards using previousSlot function
           front = previousSlot(front);
        
           A[front] = k;
           //this next line makes sure it goes in a circle: so when you get to 9 the pointer, front, will move to zero
    
           ++size;
       }
    }
    public int size(){
  
        return size;
    }
    
    public boolean isEmpty(){
        int size = size();
        
        if (size == 0)
            return true;
        else
            return false;
    }
    int nextSlot(int k) {
        // this function is used to ensure that the input values wrap around when moving around the circle
        return ((k + 1) % A.length);
    }
    
    public int previousSlot(int k){
    // this function will return the previous slot, making sure that at 0 it will return 9
        if (k == 0)
            //will return 0
             return((k + SIZE-1) % A.length); 
        else
          //else return the previous slot
            return ((k - 1) % A.length);
    }
    
    public String toString(){
        // this will return a string representation of the queue
        //these if statements will print out the right array, the reszied one or the original array
        String s = "";
        if(A.length <= SIZE){
            
            // this for loop will print out the indexes in reverse order
            for(int i = SIZE-1; i >= 0; --i)
                s += i + "\t"; 
                //this next for loop will print out the values in the array in reverse order
                s += "\n";
        
            for(int i = SIZE-1; i >= 0; --i)
                s += A[i] + "\t";
                // this last line will print out the values of next and front
                s += "\nnext = " + next + "   front = " + front + "\n";
            
       
        }
        else if(SIZE < A.length){
        
           
            // this for loop will print out the indexes in reverse order
            for(int i = ((SIZE*2)-1); i >= 0; --i)
                s += i + ""; 
                //this next for loop will print out the values in the array in reverse order
                s += " ";
            
            for(int i = ((SIZE*2)-1); i >= 0; --i)
            s += A[i] + "";
            // this last line will print out the values of next and front
            s += "\nnext = " + next + "   front = " + front + "\n";
            
       
        }
         return s; 
    
    }
    
    public void resize() {
    // this function will allocate an array B twice as big as A, then it will copy all the elements from A over to B
        System.out.println("I should get here");
        int NEW_SIZE = SIZE * 2;
        int [] B = new int[NEW_SIZE];
        
       //copy over all the elements of A into B
        for(int i = 0; i < A.length; i++){
            
            B[i] = A[front];
            
            //make sure the new array will also be able to wrap around and get all values from A (nextSlot essentially)
            front = nextSlot(front);
            
        }
       
      //replace A with B 
       A = B;
       //reset next to the index of the last value of A transferred over(ie it should be 9)
       next = front;
      
       //set the front to be 20, so when you do enqueue rear, it will start at the right index
       front = (SIZE*2);
    }
    
    public static void main(String[] args) {
        try{
        IntDeque D = new IntDeque(); 
        
        System.out.println("\n[1] First test toString on empty dequeue... Should print out:"); 
        System.out.println("[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  length: 10  size: 0  front: 0  next: 0"); 
        System.out.println(D); 
        
//        System.out.println("\n[2] Test size and isEmpty... Should print out:\n0  true"); 
//        System.out.println(D.size() + "  " + D.isEmpty()); 
////        
//        System.out.println("\n[3] Test enqueueRear.... Should print out:"
//                               + "\n[0, 0, 0, 0, 0, 0, 4, 3, 2, 1]  length: 10  size: 4  front: 0  next: 4");
 //       D.enqueueRear(1); 
//        D.enqueueRear(2);
//        D.enqueueRear(3); 
//        D.enqueueRear(4);
////        
//        System.out.println(D); 
//        
//        System.out.println("\n[4] Test size and isEmpty again... Should print out:\n4  false"); 
//        System.out.println(D.size() + "  " + D.isEmpty()); 
////        
//        System.out.println("\n[5] Test dequeueFront.... Should print out:"
//                               + "\nn = 1  m = 3");
//        
//        int n = D.dequeueFront();
//        D.dequeueFront();
//        int m = D.dequeueFront(); 
//        System.out.println("n = " + n + "  m = " + m); 
//        
//        System.out.println("\n[6] And should print out:"
//                               + "\n[0, 0, 0, 0, 0, 0, 4, 3, 2, 1]  length: 10  size: 1  front: 3  next: 4");
//        System.out.println(D); 
//        System.out.println("size is " + D.size());
//        
//        System.out.println("\n[7] Test wrap-around of enqueueRear .... Should print out:"
//                               + "\n[10, 9, 8, 7, 6, 5, 4, 13, 12, 11]  length: 10  size: 10  front: 3  next: 3");
////        
//        for(int i = 5; i < 14; ++i)
//            D.enqueueRear(i);
////        
//        System.out.println(D);
//        
//        System.out.println("size is " + D.size());
//        
//        System.out.println("\n[8] Test wrap-around of dequeueFront .... Should print out:"
//                               + "\n[10, 9, 8, 7, 6, 5, 4, 13, 12, 11]  length: 10  size: 0  front: 3  next: 3  m = 13");
//        
//        for(int i = 0; i < 10; ++i){
//            D.dequeueFront();
//            
//        }
//        
//        System.out.println(D); 
//        System.out.println( "size is " + D.size());
        
        //Test 9: Test enqueue front by inserting several numbers in front and printing out the queue
//        System.out.println("\n[9] Test of enqueueFront .... Should print out:"
//                               + "\n[1,2,3,4,0,0,0,0,0,0,0]  length: 10  size: 4  front: 6  next: 0 m = 3");
//        
//        for(int i = 1; i < 5; ++i)
//            D.enqueueFront(i);
//       
//        System.out.println(D); 
//        
//        //Test 10: Test the size and isempty again
//        System.out.println("\n[10] Should print out 4 and false");
//        System.out.println("size is  " + D.size());
//        System.out.println(D.isEmpty());
//       
////        
//         
//        // Test 11: Test dequeueRear by removing some elements, printing them out, and printing the queue
//        System.out.println("\n[11] Test dequeueRear .... Should print out:"
//                               + "\n[1,2,3,4,0,0,0,0,0,0,0]  length: 10  size: 1  front: 6 next: 7");
//        
//        for(int i = 0; i < 3; ++i){
//            int z = D.dequeueRear();
//            
//  
//        }
//        
//        System.out.println(D); 
//        System.out.println("size is  " + D.size());
//        
//        //Test 11.5: Test wraparound of enqueueFront
//        System.out.println("\n[11.5] Test  enqueueFront.... Should print out:"
//                               + "\n[12,13,14,4,5,6,7,8,9,10,11]  length: 10  size: 10  front: 7 next: 7 ");
//        
//        for(int i = 5; i < 14; ++i)
//            D.enqueueFront(i);
//        System.out.println(D); 
//        System.out.println("size is  " + D.size());
//        
//        //Test 11.75: Test wraparound of dequeueRear
//          System.out.println("\n[11.5] Test  dequeueRear.... Should print out:"
//                               + "\n[12,13,14,4,5,6,7,8,9,10,11]  length: 10  size: 1  front: 8 next: 7 ");
//        
//        for(int i = 5; i < 14; ++i){
//            D.dequeueRear();
//            
//        
//        }
//        System.out.println(D); 
//        System.out.println("size is  " + D.size());

        
        //Test 12: Test all four methods together by alternately removing and adding from both ends
                  //and print out the queue to make sure it worked.  
//        
//        System.out.println("\n[12] Test enqueueRear.... Should print out:"
//                               + "\n[0, 0, 0, 0, 0, 5, 4, 3, 2, 1]  length: 10  size: 5");
//        D.enqueueRear(1); 
//        D.enqueueRear(2);
//        D.enqueueRear(3); 
//        D.enqueueRear(4);
//        D.enqueueRear(5);
//        
//        System.out.println(D); 
//        System.out.println("size is " + D.size());
//        
//        D.dequeueFront(); 
//        D.dequeueFront();  
//        D.dequeueFront(); 
//        
//        System.out.println(D); 
//        System.out.println("size is " + D.size());
//        
//        for(int i = 22; i < 29; ++i)
//            D.enqueueFront(i);
//       
//        System.out.println(D); 
//        System.out.println("size is " + D.size());
//        
//        int z = D.dequeueRear();
//        
//        D.dequeueRear();
//        D.dequeueRear();
//        
//        System.out.println("z is " + z);
//        System.out.println(D); 
//        System.out.println("size is " + D.size());
        
         //Test 15: test resizing by inserting enough elements to trigger a resize to size 20 and print out
//            
//        for(int i = 1; i < 11; ++i)
//            D.enqueueRear(i);
//        
//         System.out.println(D);
//         D.enqueueFront(50);
//         
//         
//         System.out.println(D);
//        
//         System.out.println("size is " + D.size());
//           
////        
//         //Test 16: test that enqueue and dequeue (all four) work after resize by alternately removing and adding from both ends
//        //          and print out the queue to make sure it worked.
//        for(int i = 60; i < 70; ++i)
//            D.enqueueFront(i);
//          
//        System.out.println(D);
//        
//        for(int i = 1; i < 10; ++i)
//            D.dequeueFront();
//        
//        System.out.println(D);
//        System.out.println("size is " + D.size());
//        
//        D.dequeueRear();
//        D.dequeueRear();
//        System.out.println("size is " + D.size());
        
          //Test 17: Test size to make sure it works after the resize
        // done in test 15

        // Test 18: Test exceptions by removing all elements from queue and then doing one more
        //          dequequeFront and showing how it prints out a message 
        
//        D.enqueueRear(1); 
//        D.enqueueRear(2);
//        D.enqueueRear(2);
//                        
//        D.dequeueFront(); 
 //       D.dequeueFront(); 
//        D.dequeueFront(); 
//        D.dequeueFront(); 
       
        
//        Test 19: Do the same for dequeueRear
//        D.enqueueRear(1); 
//        D.enqueueRear(2);
//        D.enqueueRear(2);
//        
        D.dequeueRear(); 
//        D.dequeueRear(); 
//        D.dequeueRear();
//        D.dequeueRear();
//
      
    }
        
        catch (QueueUnderflowEception e){ 
            System.err.println("Queue Underflew!");
        }
    }
}

class QueueUnderflowEception extends Exception {
   //this will catch a queueUnderflow exception     
    }
