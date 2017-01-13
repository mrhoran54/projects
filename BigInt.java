/**************************
  * Filename: BigInt.java
  * Author: Megan Horan (mrhoran@bu.edu)
  * Date: 2/16/16
  * Purpose: This is a template for the BigInt class, representing big integers. 
  * ************************/

import java.util.Arrays;

public class BigInt {  
    
    public static boolean debug = true; 
    
    private int[] A = new int[BigMath.SIZE];
 
    // These constructors will build a new instance of this class
    // and return it, see the Unit Test below for how to use them. 
    
    public BigInt() { 
        A = new int[BigMath.SIZE];              // Default constructor  
        for(int i =0; i <BigMath.SIZE-1; i++)
            A[i] = 0;
    }
    
    public BigInt(String s) {    // These will not work until you implement putValue(..)
        putValue(s);
    }
    
    public BigInt(int[] B) {
        putValue(B);
    }
    
    public BigInt(int n) {
        putValue(n);
    }
    
    public int length() {
        
        int length = 0;
        //special case: if big int is zero return 1
        
        // iterate through the BigInt
        for(int j = 0; j< A.length; j++){
             if(A[j] == 0){
                 length +=1;
             }
             else
                 break;
         }
         int length1 = (BigMath.SIZE - length);
         return (length1);           
    }
    
    // this function will allow the Big Int to contain the int representation of the chars in S
    public void putValue(String s) {
        
        //iterate through BigInt A to put in the values from the string
        for (int i =0; i < s.length(); i++){
                                              //get the char value at each index of the string
            char result = s.charAt(i);
                                             // find int value of each char
            int c;      
            c = result - 48;
                                                   // put in the int values backwards in the BigInt
            int index = ((BigMath.SIZE - s.length()) +i); 
           
            A[index] = c;
            
        }

    }
    //this function will create the big integer's value from an arrayof ints
    public void putValue(int[] B) {
        // get length of the array
        int x = B.length;
       
        for(int i = 0; i < B.length; i++){
                                         
            int z = ((BigMath.SIZE - (x)) +i);
            
            A[z] = B[i];
        }
       
    }
    //use the integer n and convert it into the array of digits. 
    public void putValue(int n) {
        
        // First I convert the int to a string
        String s = String.valueOf(n); 
        
        //since I convertted it to a string, I just use the same process as the putValue for the String
        
        for(int i = 0; i < s.length(); i++){
            
            char result = s.charAt(i);
                                             // find int value of each char
            int c;      
            c = result - 48;
                                                   // put in the int values backwards in the BigInt
            int index = ((BigMath.SIZE - s.length()) +i);             //assign that to the correct index in BigInt
    
            
            A[index] = c;
            
        }
        
        
    }
    // this function will return the digit at slot i(slot 0 is the leftmost digit)
    public int digitAt(int i) {
        //first find the index of the leftmost digit
        
         int index = 0;
         //go through each value and it if still zero add to the value of index
         for(int j = 0; j< A.length-1; j++){
             if(A[j] == 0){
                 index +=1;
             }
             else
                 break;
         }
         // use that value and add the value of i to get the right digit to return
         int digitAt = (i + index);
         return(A[digitAt]);
    }
    
// this function is kind of like digitAt, except it replaces the value in 
    // the array with the int n at the index i
    public void putDigitAt(int i,int n) {
        //first find the index of the leftmost digit
        
        int index = 0;
         //go through each value and it if still zero add to the value of index
         for(int j = 0; j< A.length; j++){
             if(A[j] == 0){
                 index +=1;
             }
             else
                 break;
         }
         // use that value and add the value of i to get the right digit to return
         int digitAt = (i + index);
         
        A[digitAt] = n;
        
    }
    
    // this function will print out a string of the array
    public String toString() {
        
        
        String x = "";
        
        // get index of the leftmost digit
        int index = 0;
         //go through each value and it if still zero add to the value of index
         for(int j = 0; j< A.length-1; j++){
             if(A[j] == 0){
                 index +=1;
             }
             else
                 break;
         }
         
         
      //get the index and then start adding to the string
       for(int i = index; i < A.length; i++){
                 
                 x += A[i];
            }
            return (x); 
        }
       
    
 //for debuging
    public static void db(String s) {
    if (debug)
        System.out.println("\t" + s);      
}   
    public static void main(String [] args) {
       
        System.out.println("\nUnit Test for BigInt Class");
        
        int[] a = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6,2,6,4,3,3,8};
        String b = "314159265358979323846264338327950288";
        int c = 314159265;
        
        BigInt A = new BigInt();
        A.putValue(a);
        
        
        System.out.println("\nTest 1: Should be:\n27");
        System.out.println( A.length() );
        
        System.out.println("\nTest 2: Should be:\n314159265358979323846264338");
        System.out.println( A );
        
        
        BigInt B = new BigInt();
        B.putValue(b);
        
        System.out.println("\nTest 3: Should be:\n36");
        System.out.println( B.length() );
        
        System.out.println("\nTest 4: Should be:\n314159265358979323846264338327950288");
        System.out.println( B );
        
        BigInt C = new BigInt();
        C.putValue(c);  
        
        System.out.println("\nTest 5: Should be:\n9");
        System.out.println( C.length() );
        
        System.out.println("\nTest 6: Should be:\n314159265");
        System.out.println( C );
        
        BigInt Z = new BigInt();       // will be zero
        
        // Special case: 0 will have length 1 and print out as a single digit
        
        System.out.println("\nTest 7: Should be:\n1");
        System.out.println( Z.length() );
        
        System.out.println("\nTest 8: Should be:\n0");
        System.out.println( Z );
        
       
        BigInt One = new BigInt();
        One.putValue(1);
        
        System.out.println("\nTest 9: Should be:\n1 1");
        System.out.println( One.length() + " " + One);
        
        // Even if string or array has leading zeros, you just put them in, and they get
        // treated like any other leading zeros when you check length and print out. 
        
        System.out.println("\nTest 10: Should be:\n1");
        BigInt D = new BigInt();
        D.putValue("000000004");
        System.out.println( D.length() ); 
        
        System.out.println("\nTest 11: Should be:\n4");
        System.out.println( D ); 
        
        System.out.println("\nTest 12: Should be:\n3 234");
        BigInt E = new BigInt();
        int[] e = {0,0,0,0,2,3,4};
        E.putValue(e);
        System.out.println( E.length() + " " + E ); 
        
        System.out.println("\nTest 13: Should be:\n3 4 5");
        System.out.println( C.digitAt(0) + " " + C.digitAt(2) + " " + C.digitAt( C.length() - 1 ) );
        
        System.out.println("\nTest 14: Should be:\n1000001");
        BigInt F = new BigInt("1000001");
        System.out.println( F ); 
        
        System.out.println("\nTest 15: Should be:\n12345");
        int[] g = {1,2,3,4,5};
        BigInt G = new BigInt(g);
        System.out.println( G ); 
        
        System.out.println("\nTest 16: Should be:\n54321");
        System.out.println( new BigInt(54321) ); 
        
        System.out.println("\nTest 17: Should be:\n984159265");
        
        C.putDigitAt(0,9);
        C.putDigitAt(1,8);
        System.out.println( C );
        
        System.out.println("\nTest 18: Should be:\n984100005");
        C.putDigitAt(4,0);
        C.putDigitAt(5,0);
        C.putDigitAt(6,0);
        C.putDigitAt(7,0);
        System.out.println( C );
        
        System.out.println("\nTest 19: Should be:\n100005");
        C.putDigitAt(0,0);
        C.putDigitAt(0,0);
        C.putDigitAt(0,0);
        System.out.println( C );
        
        System.out.println("\nTest 20: Should be:\n5");
        C.putDigitAt(0,0);
        System.out.println( C );  
        
        int[] k = {1,0,0,0,0,0};
        
        System.out.println("\nTest 20: Should be:\n100000");
        BigInt K = new BigInt();
        K.putValue(k);  
        System.out.println( K );  
        
    }      
}
