/* File: Dictionary.java
 * Author: Megan Horan
 * Date: 2/29/2016
 * Purpose: This program will create a dictionary class which contains key value paris that are both represented by strings 
 */
import java.util.Arrays;

public class Dictionary { 
    public static boolean debug = true; 
    private int LENGTH = 10;
  
    private Pair[] A = new Pair[LENGTH];
    private int next = 0; 
   
    // these next variables function for the iterator methods
    private int nextPair = 0;                  //  pointing to next pair to be read in array A in when nextPair is called
    private int nextStudent = 0;               // used for nextStudent(....) is called
    private String className;                  // class the student iterator is enumerating the class list for
    
    // basic definition of the Pair class, inside the Dictionary class
    
    private class Pair {
        String key;
        String[] value;
        
        public Pair() { }              
        
        public Pair(String k, String[] v) {
            key = k;
            value = v;
            
        }
        // this function will print out the key values pairs
        public String toString() {
            
           String S = key;
           S += " [";
           
           for(int j = 0; j < value.length;j++){
               if(j == value.length-1)
                   S += (value[j]);
               else{
                   S += (value[j] + ",");
               }
            }
            S += "]";
            return S;
        }
        
    }
    // this function add a new keyvalue pair
    public void insertStudent(String k, String[] val){
                
     //need to resise if its two big!!
        
        if(A.length == next){
            resize();
            insertStudent(k, val);
        }

        //if K is already in the table, then just replace the value
        else if(member(k) == true){
            //iterate through the values until you find the matchin key, and the update its value
            for(int i = 0; i < next; i++){
                if((A[i].key).equals(k))
                   
                    A[i].value = val;
            }
        }
        
        else{
            //create new pair
            Pair X = new Pair(k, val);
            
            //input the pair into the unsorted dictionary
            A[next] = X;
            next++;
            
            // sort all other values in the array greater than 1 (the already sorted initial section)
            if (next > 1){
                
                //want to look at the most recent value put in, not next
                int j = (next-1);
                //compare it to the last value
                int val1 = (A[j-1].key.compareTo(A[j].key));
                
                //if the comparison is greater than 0, the new value is lexographically smaller than the preceding char
                // in other words, sort it!
                //else the pair is lexographically bigger than the last pair and so it is sorted
                while(j > 0 && (val1 > 0)) {
                    
                    //move the larger value over 
                    A[j] = A[j-1];
                    // put the pair into the array
                    A[j-1] = X;
                    j--;
                    
                    //compare it with the next preceding value, ie keep moving
                    if (j > 0){
                        val1 = (A[j-1].key.compareTo(A[j].key));
                        
                    }
                    
                }
                
            }
        }
            
 }
   // helper function, will make array A twice as big
    
  public void resize(){
      //make an array twice the size
      Pair[] B = new Pair[(LENGTH * 2)];
      
      //put in all the values of A
      for(int j =0; j < A.length ; j++)
          
          B[j] = A[j];
      
      // set A equal to B
      A = B;
     
  }
//*********************************************************************************************************************
     private boolean memberArray(String k, String[] C) {
        // this helper function returns true if string k is in the array C, and false if not ...
         
         for(int i =0; i < C.length; i++){
            
             if(k.equals(C[i]) == true){
                 
                 return true;
             }
             
         }
         return false;
    }
 //*********************************************************************************************************************
    public boolean member(String k){
        // this function uses binary search to find if a key is alread in the dict
        // initiate man min and median values
        int max = size();
        int min = 0;
        int median = (max + min) / 2;
        
        // while min is smaller than max, ie they dont cross, which means that the key was never found
        while(min < max){
  
            // comparisons using lexographical comparisons, if the key is found, return true
            if (k.compareTo(A[median].key) == 0)
                
                return true;
            //else if the key is alphabetically bigger than the middle, reset the max and min to the right of the median
            if (k.compareTo(A[median].key) < 0){
               
                max = median;
                median = ((max + min) / 2);
            }
            //else if the key is alphabetically smaller than the middle, reset the max and min to the left of the median
            else if (k.compareTo(A[median].key) > 0){
                min = median+1;
              
                median = ( (max+min) / 2);  
                
            }
        }
        return false; 
    }
 //*********************************************************************************************************************
    //Return the value (array of Strings) associated with the key k, or throw an exception
    public String[] lookupClasses(String k) throws KeyNotFoundException { 
        int index = 0;
        
        if(member(k) == false){
            throw new KeyNotFoundException();
        }
        //find the key otherwise
        
        else {
            //iterate through the values until you find the matchin key, and the update its value
            for(int i = 0; i < next; i++){
                if((A[i].key).equals(k))
                   index = i;
            }
        }
        return(A[index].value);
        
    }
//*****************************************************************************************************    
    public boolean enrolled(String k, String c){

        //find location of the key
         int index = location(k);
         //Return true if student k is enrolled in class
        if((memberArray(c, A[index].value)) == true)
            return true;
        
        else
            return false;
    }
 //*****************************************************************************************************
    private int location(String k) {
        //helper function to find the index of the key!
        int index = 0;
        // make index variable to find the index where the key exists
        if(member(k) == true){
            //for loop to iterate 
            for(int i = 0; i < next; i++){
                if((A[i].key).equals(k)){
                   
                   index = i;  
                }          
            }
        }
        return index;
        
    }
 
//*********************************************************************************************************************  
    //this function will removes a class from a vertain Key's array
    public void dropClass(String k, String c) {
         
       //find the index of the key k
        int index = location(k);
        
        String[] B = new String[(A[index].value.length)-1];
        
        int classdrp = 0;
        //if the key is in the dict and the class is in its value
        if(member(k) == true && memberArray(c, A[index].value) == true){
        
        // find index of the class to drop
            for(int j =0; j < (A[index].value.length); j++){
                  
                if ((A[index].value[j]).equals(c) == false) {
                    classdrp++;
                 }
                else
                    break;
            }
            // knowing the index of the class to drop, go through and shift values all the way to the left in the value (ie A)
            //this will remove the class we dont want 
            for(int j = classdrp; j < (A[index].value.length)-1; j++){
                  
                A[index].value[j] = A[index].value[j+1];
            }
            //now that it is removed the A[index].value = ["cs111", ect, null], simply iterated those values into the smaller array B
            for(int i = 0; i < (B.length); i++)
                B[i] = A[index].value[i];
            
            //insert this array (smaller by one) into the value.
           
            A[index].value = B;
        }
    }

//*********************************************************************************************************************   
     //this function will remove the Pair containing k from the table if its there 
    public void deleteStudent(String k) {
       
        int index = 0;
        // make index variable to find the index where the key exists
        if(member(k) == true){
            //for loop to iterate 
            for(int i = 0; i < next; i++){
                if((A[i].key).equals(k)){
                   A[i] = null;
                   index = i;
                   
                }
            }
        }
        
        --next;
        
     //When removing the Pair, you must shift everything to the left to get rid of the slot
        for(int i = index; i < next; i++){
  
             A[i] = A[i+1];
             
        }
        //set the last value, that wasnt moved over and should be deleted because of the new smaller size, to null!
        A[next] = null;
    }
    public int size(){
        return next;
    }
    
    public boolean isEmpty(){
        if (size() == 0)
            return true;
        else 
            return false;
    }
    

//*********************************************************************************************************************  
public void addClass(String k, String c){
     //this function is going to add a class to the value of a certian key K
 
    //If k is in the dictionary, find the index of the key so we can access its value!
     int index = location(k);
     
     //If k is not in the dictionary, add them with the class c in the value 
    if(member(k) == false){
        //make a new string []
        String[] B = new String[1];
        B[0] = c;
        insertStudent(k, B);
    }
     
    //If k is in the dictionary but c is not in the value array, then allocate another array one slot bigger 
    else if(member(k) == true &&  memberArray(c, A[index].value) == false){
        //find the length of the value at key
        
        int length1 = ((A[index].value.length)+1);
        // add one to it to get get the length of the new array
        
        String[] C = new String[length1];
        //iterate through the value and put the values in the new array
        
        for(int i = 0; i < A[index].value.length; i++){
            C[i] = A[index].value[i];
        }
        
        //add the new class to the end
        C[(length1-1)] = c;
        // set the value to this new length
        A[index].value = C;
    }

    }
//*********************************************************************************************************************  
// Iterator methods for pairs in dictionary

//Initialize a general iterator which can enumerate all Pairs in the dictionary in order (from smallest key to largest).
public void initPairIterator() {
 
     //set nextPair to 0 
    nextPair = 0;
  
}
 //Return true if there is another Pair to be enumerated by the general iterator 
public boolean hasNextPair(){

    //check to see that at index nextPair in the array, contains a Pair. ]
    if(A[nextPair] == null){
       
        return false;
    }
    else
        
        return true;
}
 // Return a String representation of the next pair to be enumerated by the general iterators.
public String nextPair() {

    //put String representation of the current pair in a variable, 
    
    String x = "";
   
    x += A[nextPair].toString();

    //advance the nextPair pointer
    ++nextPair;
    return x;
    
}
// Initialize an enumeration of the students in the class c.
public void initStudentIterator(String c) { 
    //nextStudent to 0 
    nextStudent = 0;
    // store c in the variable className
    className = c;
      
}
public boolean hasNextStudent() { 

  
     //check to see that at index nextPair in the array, contains a Pair. ]
    if(A[nextStudent] == null){
       
        return false;
    }
    else
        
        return true;
}

 public String nextStudent() { 
     
     String s = "";
    // if the pair at next student has the class, return it
     // check to see if next student has the class
     if(memberArray(className, A[nextStudent].value) == true){
        // if it does add it to string s!
        s += A[nextStudent].toString();
        ++nextStudent;
        
     }
    else
        //otherise, just increment nextStudent
        ++nextStudent; 
    //return s
   return s;
 }
 
    private void printDictionary() {
        for(int i = 0; i < next; ++i)
            System.out.println(i + ": " + A[i]);
    }    
    
    public static void db(String s) { 
        if(debug)
            System.err.println("\t" + s);
    }    
  
    public static void main(String[] args) {
        
        Dictionary D = new Dictionary();
       
        String[] A = { "CS111", "CS131", "WR99", "EC101" };
        String[] B = { "CS111", "MA123", "WR100", "SO100" };
        String[] C = { "CS111", "MA294", "WR150", "CL212" };
        String[] E = { "CS350", "CS235", "EC101", "MU101" };
        String[] F = { "CS111", "MA124", "BI108", "SO105" };
        String[] G = { "CS591", "MA442", "EN212", "EC101" };
        
      
        D.insertStudent("Trump,Donald", C);
        D.insertStudent("Carson,Ben", B);
        D.insertStudent("Kasich,John",E);
        D.insertStudent("Bush,Jeb", G);
        D.insertStudent("Cruz,Ted", F);
        D.insertStudent("Christie,Chris", A);
       
        System.out.println("\n[1] Should print out:"); 
        System.out.println("0: Bush,Jeb: [CS591,MA442,EN212,EC101]");
        System.out.println("1: Carson,Ben: [CS111,MA123,WR100,SO100]");
        System.out.println("2: Christie,Chris: [CS111,CS131,WR99,EC101]");
        System.out.println("3: Cruz,Ted: [CS111,MA124,BI108,SO105]");
        System.out.println("4: Kasich,John: [CS350,CS235,EC101,MU101]");
        System.out.println("5: Trump,Donald: [CS111,MA294,WR150,CL212]\n"); 
        
        
        D.printDictionary();
         
        System.out.println("\n[2] Should print out:\n6"); 
        System.out.println(D.size()); 
        
        System.out.println("\n[3] Should print out:\nfalse"); 
        System.out.println(D.isEmpty()); 
        
        
        System.out.println("\n[4] Should print out:\ntrue"); 
        System.out.println(D.member("Cruz,Ted")); 
        
        System.out.println("\n[5] Should print out:\nfalse"); 
        System.out.println(D.member("Jindal,Bobby"));
         D.printDictionary();
        
        
        D.deleteStudent("Bush,Jeb");
        D.deleteStudent("Christie,Chris");
       
        System.out.println("\n[6] Should print out:\nfalse  false"); 
        System.out.println(D.member("Bush,Jeb") + "  " + D.member("Christie,Chris")); 
        
        System.out.println("\n[7] Should print out:\n[CS111, MA123, WR100, SO100]"); 
        String name = "Carson,Ben";
        try {
            System.out.println(Arrays.toString(D.lookupClasses(name))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: " + name);
        }
        
        name = "Jindal,Bobby";
        System.out.println("\n[8] Should print out:");
        System.err.println("Key not found: " + name); 
        try {
            System.out.println(Arrays.toString(D.lookupClasses(name))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: " + name);
        }

       
        System.out.println("\n[9] Should print out:\n[CS111, WR100, SO100]");  
        D.dropClass("Carson,Ben", "MA123");
        D.dropClass("Carson,Ben", "EC102"); 
        try {
            System.out.println(Arrays.toString(D.lookupClasses("Carson,Ben"))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: Carson,Ben");
        } 

        System.out.println("\n[10] Should print out:\n[CS111, MA294, WR150, CL212, CS591]");  
        D.addClass("Trump,Donald", "CS591");
        D.addClass("Trump,Donald", "WR150"); 
        try {
            System.out.println(Arrays.toString(D.lookupClasses("Trump,Donald"))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: Carson,Ben");
        } 
        
        System.out.println("\n[11] Should print out:\nfalse  true"); 
        D.dropClass("Walker,Scott","PH150");
        System.out.print(D.member("Walker,Scott") + "  " );
        D.addClass("Walker,Scott","PH110"); 
        System.out.println(D.member("Walker,Scott"));   
       
        System.out.println("\n[12] Should print out:\ntrue"); 
        System.out.println(D.enrolled("Trump,Donald", "CS591"));  
        
        System.out.println("\n[13] Should print out:\nfalse"); 
        System.out.println(D.enrolled("Trump,Donald", "CS101"));        
        
        
         //testing iterators       
       
        System.out.println("\n[14] Should print out:");
        System.out.println("0: Carson,Ben: [CS111,WR100,SO100]");
        System.out.println("1: Cruz,Ted: [CS111,MA124,BI108,SO105]");
        System.out.println("2: Kasich,John: [CS350,CS235,EC101,MU101]");
        System.out.println("3: Trump,Donald: [CS210,MA294,WR150,CL212,CS591]");
        System.out.println("4: Walker,Scott: [PH110]\n");
        
        D.printDictionary();
         
        System.out.println("\n[15] Should print out same but without index numbers:");
        
        D.initPairIterator(); 
            
        while(D.hasNextPair()) {
            
            System.out.println(D.nextPair());
        }
        
        
        System.out.println("\n[16] Should print out:\nCarson,Ben:  [CS111,WR100,SO100]");
        D.initPairIterator(); 
        System.out.println(D.nextPair());
        
        System.out.println("\n[17] Should print out:");  
        
        D.initStudentIterator("CS111");
        System.out.println("Carson,Ben: [CS111,WR100,SO100]");
        System.out.println("Cruz,Ted: [CS111,MA124,BI108,SO105]");
        System.out.println("Trump,Donald: [CS111,MA294,WR150,CL212,CS591]\n");
        
        
        while(D.hasNextStudent()) {
            
            System.out.println(D.nextStudent());
        }
        
        System.out.println("\n[18] Should print out:\nTrump,Donald:[CS111,MA294,WR150,CL212,CS591]");
        D.initStudentIterator("CL212"); 
        
        while(D.hasNextStudent()) {
            System.out.println(D.nextStudent());
        } 
        
        System.out.println("\n[19] Testing resizing; should print out a dictionary with 14 pairs.\n"); 
        D.insertStudent("Clinton, Hillary",A); 
        D.insertStudent("Sanders,Bernie", B);
        D.insertStudent("Lincoln,Abraham", C);
        D.insertStudent("Kennedy,John",E); 
        D.insertStudent("Bush,George", F);
        D.insertStudent("Reagan,Ronald", G);
        D.insertStudent("Nixon,Dick",A); 
        D.insertStudent("Carter,Jimmy", B);
        D.insertStudent("Johnson,Lyndon", C);
 
        D.printDictionary(); 
        
    }
    
}



class KeyNotFoundException extends Exception {}