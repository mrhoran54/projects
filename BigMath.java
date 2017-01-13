
/* File: BigMath.java
 * Date: 2/15/16
 * Author:  Megan Horan (mrhoran@bu.edu)
 * Class: CS 112, Spring 2016
 * 
 * Purpose: This is a template for the BigMath class.   
 */
import java.util.Arrays;

public class BigMath  { 
    
    public static boolean debug = false; 
    public static final int SIZE = 50;  
    
    // this function compares ints, determining which is bigger 
    public static int compare(BigInt N, BigInt M) {

        int nlength = N.length();
        int mlength = M.length();
        // if n is smaller in length, its smaller
        if (nlength < mlength)
            return -1;
        //if n is bigger, its length its larger
        if (nlength > mlength)
           return 1;
        // if they are the same length then go through each value
        else if (nlength == mlength){
            for(int i =0; i < N.length(); i++){
                if (N.digitAt(i) > M.digitAt(i))
                    return 1;
                if (N.digitAt(i) < M.digitAt(i))
                    return -1;
            }
            
        }
        // if theya re equal return zero
        return 0;
    }  
//  this function will return true if N == M and false otherwise  
    public static boolean equals(BigInt N, BigInt M) {
        if (compare(N, M) == 0)
            return true;
        else
        return false;    
    }
    // this function will find the sum of N and M by creating new stacks using IntStack
    public static BigInt add(BigInt N, BigInt M) {
        
        // create stacks to hold the contents in the big ints
        IntStack SA = new IntStack(SIZE);    // stack to hold digits of A
        IntStack SB = new IntStack(SIZE);    // stack to hold digits of B
        
        IntStack SS = new IntStack(SIZE);    // stack to hold the digits of the sum of A and B 

        //use for loops to put the contents of the big ints in SA and SB
        
        for(int i = 0; i < N.length(); i++){
            int n = N.digitAt(i);
            SA.push(n);
        }
       
         for(int i = 0; i < M.length(); i++){
            int n = M.digitAt(i);
            SB.push(n);
        }
         
         int carry = 0; 
         
         //while loop: if SA and SB are not empty
         while (!SA.isEmpty() & !SB.isEmpty()){
        
        // store addition of the pop values from SA and SB
             int x = SA.pop();
             int y = SB.pop();
             
             int val = x+y+carry;
        
        //if result is two digits, set carry = 1 
             if (10 <= val) {
                
                 SS.push((val-10));
                 carry = 1;        //push the int - 10 onto SS
                 
             }
             else {
                 
                 SS.push(val);
                 carry = 0;       
             }
         }
        //if SA empty, push the rest of SB onto SS
         if (SA.isEmpty()){
          
             while(!SB.isEmpty()) {
                  int val = (SB.pop()+carry);
                  //  make if statements for the two cases: if there is a carry
                  if (10 <= val) {
                  
                 SS.push((val-10));
                 carry = 1; //push the int - 10 onto SS
                 
             }
                  // or if there isnt a carry
             else {
              
                 SS.push(val);
                 carry = 0;
             }       
           }
         }
                                   
        //if SB is empty, use the same computations to push the rest of SA onto SS
        if (SB.isEmpty()){
              while(!SA.isEmpty()) {
                  int val = (SA.pop()+carry);
             
                  if (10 <= val) {
                
                 SS.push((val-10));
                 carry = 1; 
             }
             else {
                 SS.push(val);
                 carry = 0;
             }       
           }
         }
        
        //if SA & SB is empty and if carry = 1; push 1 onto ss
         if(SA.isEmpty() & SB.isEmpty()){
             if(carry == 1){
                 
                 SS.push(1);    
             }
          }
          
          //pop the values from ss into an array, make a big int from the array
          int[] A = new int[SS.size()];
          int i = 0;
          while(!SS.isEmpty()){
              A[i] = SS.pop();
              i++;
          }
          
          //put all the values from the array into a big int
          BigInt B = new BigInt();
          
          B.putValue((A));
      
          return B;    
    }
    
    private static BigInt multByInt(BigInt N, int m) {
        //create two stacks to hold the two integers
        IntStack SA = new IntStack(SIZE);    // stack to hold digits of N
        IntStack SS = new IntStack(SIZE);    // empty, stack to hold digits of result
        
       //iterate through N and put those values into one of the stacks
        for(int i =0; i< N.length(); i++)
            
            SA.push(N.digitAt(i));
            
       //initialize carry variable to zero
        int carry = 0;
        
        //while loop: while N is not empty, keep popping and pushing
        while(!SA.isEmpty()) {
            int x = SA.pop();
            
            int val = (x * m) + carry;
            
        //if result is two digits, set carry = 1 
             if (10 <= val) {
               
                 //push the value
                 
                 //use the % module to gets the ones place : ie the value to push onto the stack
                 int pushVal = (val%10);
                 
                 SS.push((pushVal)); 
                
                 //find the carry
                 //find the right value of 10 to divide value by to get the carry
                 carry = val / 10;
                
             }
             //if no carry
             else {
                 
                 SS.push(val);
                 carry = 0;       
             }
         }
         //if carry is not empty, push it onto the stack
        if (SA.isEmpty()){
            if(carry != 0)
                SS.push(carry);
        }
            
        
        //make stack into an array
        int[] A = new int[SS.size()];
          
        int i = 0;
          while(!SS.isEmpty()){
              A[i] = SS.pop();
              i++;
          }
          
        //create a new bigint
          BigInt B = new BigInt();
          
        //put values of the array into the stack
          B.putValue((A));
      
          return B;   
                   
    }
    
    public static BigInt mult(BigInt N, BigInt M) {
//        //create a big int stack SB
        BigIntStack SB = new BigIntStack();
       
        //create a regular stack SA containing values in big int M
        IntStack SA = new IntStack(SIZE);
     
        //if N is smaller, or it N and M are equal push it onto the stack
        
        if(compare(N, M) == -1 | compare(N, M) == 0){
            for(int i =0; i< N.length(); i++)
                   
                SA.push(N.digitAt(i));

            //while SA isnt empty, keep popping values from it
            while(!SA.isEmpty()) {
                
                //take the pop value from SA, and use multbyInt to multiply it with M
                 int x = SA.pop();
                 
                 BigInt val = multByInt(M, x);
               
                //push values onto SB
                SB.push(val);
                
                //mult M by 10
                
                M = multByInt(M,10);

            } 
        }
            
//        //if N is larger push M onto the stack
        if(compare(N, M) == 1){
            for(int i =0; i< M.length(); i++)
                   
                SA.push(M.digitAt(i));
                 
            //while SA isnt empty, keep popping values from it
            while(!SA.isEmpty()) {
                
                //take the pop value from SA, and use multbyInt to get the resulting number
                 int x = SA.pop();
                 
                 BigInt val = multByInt(N, x);
           
                //push values onto SB
                SB.push(val);
              
                //mult M by 10
                N = multByInt(N,10);
               
            } 
        }
        
        //make a new big int set to zero, will store all values in SB into it big int S}
            BigInt A = new BigInt();
              
            while (!SB.isEmpty()){
                
                A = add(A,SB.pop());
            }
       
        return A; 
    }      
    
    //for debuging!!!
    public static void db(String s) {
    if (debug)
        System.out.println("\t" + s);      
}          
     
    public static void main(String [] args) {
        
        System.out.println("\nUnit Test for BigMath Class");
        
        BigInt A = new BigInt("9999");
        
        BigInt B = new BigInt(1);
        
        int[] c = {1,8,2,7};
        BigInt C = new BigInt(c);
        BigInt D = new BigInt(234);
        BigInt E = new BigInt(235);
        BigInt F = new BigInt(9999);
        BigInt Z = new BigInt(0);
        
         
        System.out.println("\nTest 1: Should be:\n4 9999");
        System.out.println( A.length() + " " + A );
        
        System.out.println("\nTest 2: Should be:\n1 1");
        System.out.println( B.length() + " " + B );
        
        System.out.println("\nTest 3: Should be:\n4 1827");
        System.out.println( C.length() + " " + C );
        
        System.out.println("\nTest 4: Should be:\n3 234");
        System.out.println( D.length() + " " + D );
        
        System.out.println("\nTest 5: Should be:\n-1");
        System.out.println( compare(D,E) );
        
        System.out.println("\nTest 6: Should be:\n1");
        System.out.println( compare(E,D) );
        
        System.out.println("\nTest 7: Should be:\n1");
        System.out.println( compare(C,D) );
        
        System.out.println("\nTest 8: Should be:\n-1");
        System.out.println( compare(D,C) );
        
        System.out.println("\nTest 9: Should be:\n1");
        System.out.println( compare(A,Z) );
        
        System.out.println("\nTest 10: Should be:\n-1");
        System.out.println( compare(Z,A) );
        
        BigInt G = new BigInt(9999);        
        System.out.println("\nTest 11: Should be:\n0 true");
        
        System.out.println( compare(A,G) + " " + equals(A,G) );
        
        System.out.println("\nTest 12: Should be:\n-1 false");
        System.out.println( compare(E,F) + " " + equals(F,E) );
        
        System.out.println("\nTest 13: Should be:\n2");
        System.out.println( add(B,B) );
        
        System.out.println("\nTest 14: Should be:\n1827");
        System.out.println( add(C,Z) );
        
        System.out.println("\nTest 15: Should be:\n1827");
        System.out.println( add(Z,C) );
        
        System.out.println("\nTest 16: Should be:\n0");
        System.out.println( add(Z,Z) );
        
        System.out.println("\nTest 17: Should be:\n10000");
        System.out.println( add(A,B) );
        
        System.out.println("\nTest 18: Should be:\n10000");
        System.out.println( add(B,A) );
        
        System.out.println("\nTest 19: Should be:\n2061");
        System.out.println( add(C,D) );
        
        System.out.println("\nTest 20: Should be:\n2061");
        System.out.println( add(D,C) );
        
        System.out.println("\nTest 21: Should be:\n469");
        System.out.println( add(E,D) );
        
        System.out.println("\nTest 22: Should be:\n469");
        System.out.println( add(D,E) );  
        
        System.out.println("\nTest 23: Should be:\n1827");
        System.out.println( multByInt(C,1) ); 
        
        System.out.println("\nTest 24: Should be:\n3654");
        System.out.println( multByInt(C,2) );
        
        
        System.out.println("\nTest 25: Should be:\n0");
        System.out.println( multByInt(Z,8) );
        
        System.out.println("\nTest 26: Should be:\n99990");
        System.out.println( multByInt(A,10) );
        
        System.out.println("\nTest 27: Should be:\n4");
        BigInt Two = new BigInt(2); 
        System.out.println( mult(Two,Two) );
        
        System.out.println("\nTest 28: Should be:\n468  468");
        System.out.println( mult(D,Two) + "  " + mult(Two,D));
        
        System.out.println("\nTest 29: Should be:\n54990  54990");
        System.out.println( mult(D,E) +" " + mult(E,D) );
        
        System.out.println("\nTest 30: Should be:\n2339766  2339766");
        System.out.println( mult(D,A) + "  " + mult(A,D));
        
        System.out.println("\nTest 31: Should be:\n1013459064417062778220931703313214582990003217000");
        BigInt T = mult(A, mult( B, mult( C, mult( D, mult ( E, F ) ) ) ) ); 
        System.out.println( mult( T, mult( T, T ) ) );
    }
    
    
}
