// Filename: Histogram.java
// Author: Megan Horan
// Date: 1/30/16
// This program will print out a histogram based off user input

import java.util.Scanner; 
    
public class Histogram {
    
//  // declare final variables 
    public static final int NUM_INPUTS = 20;
    public static final int NUM_BINS = 10;
    public static final int BIN_SIZE = 100 / NUM_BINS;
    
///// printHeading method ########################################################### 
    public static void printHeading() { 
    
        System.out.println("Welcome to the Histogram Program! \n This program will print out a histogram of the numbers \n input by the user; enter uo to 20 doubles and hit Control-d to end.");;
           
}  
//    
//// valid Input method ############################################################
    public static boolean validInput(double x) {

        if (x > 100 == true){
            return false;
        }
        else if (x < 0 == true){
            return false;
        }
        else 
            return true;               
}  
/// printHistogram  method ######################################################
    public static void printHistogram(int[] a) { 
       
        //creating a new array called s, which I will use to get the correct string of asterics 
        String[] S = new String[a.length];
        
        // this next for loops sets the values of array S to empty strings
        for(int m = 0; m < S.length; m++)
            S[m] = "";
       
        //These next for loops will set the values of S to contain the correct number of asterics
        // I do this by iterating through the array that was input into this fuction, ie a
       for(int j = 0; j < a.length; j++) {
            
            for(int k = 0; k < a[j]; k++) {
                
                if(a[j] == 0){
                    S[j] = "";
                }
                else if(a[j] != 0){
                    S[j] += "*";
                }
            } 
         }

     // here I will now iterate through my new array to print out the correct asterics and print
        
        int m = 0;
        for(int i = 0; i < S.length; i++){
              
              if (S[i] == ""){
              System.out.println("(" +m +"..."+ (m += BIN_SIZE) + "]:");  
            }
            else if (S[i] != ""){
                System.out.println(("(" +m +"..."+ (m += BIN_SIZE) + "]:"+ S[i]));  
            }
        }
        
    }    
public static void main(String args[]){ 
     
    printHeading();
   
// Define a scanner for user input  
    Scanner x = new Scanner(System.in);
  
// counter variable used to see how many numbers were in input    
    int counter = 0;

//creating an array that can't be more than 20 doubles, ie 20 inputs
    double[] N = new double[NUM_INPUTS];
    
//going through the user input by using a while loop
    while (x.hasNext()) {
    
        Double userInput = x.nextDouble();
        
        //checking to make sure there are no more that 20 user inputs
        if(counter == NUM_INPUTS) {
            System.out.println("Maximum number of inputs (20) exceeded, proceeding to calculate Histogram..");
            break;
        }
        
        //using the valid input function to make sure the user input is valid
        else if  (validInput(userInput) == false) {
        System.out.println("Input must be a double in range [0...100], try again!"); 
        }
        
        //if input is valid, add the double to the array N!
        else if (validInput(userInput) == true){
             N[counter] = userInput;
             counter++;          
        }
     }   
    
    System.out.println("You entered: " + (counter) + " values:");       
  
// These next lines are turning the 20 integer length array into the appropriate size one only containing the userInputs
    //ie array of doubles, N2
   double[] N2 = new double[counter];
   for(int j = 0; j < counter;j++)
        N2[j] = N[j];

// printing out that array for the user
   System.out.print("[");
   for(int i = 0; i < N2.length-1;i++)
        System.out.print( N2[i] + ",");
   System.out.println(N2[ N2.length-1] + "]");


// creating an array to store the counts of the various ranges
   int[] histogram = new int[BIN_SIZE];
   
   for(int k = 0; k< N2.length; k++){
      
       for(int m = BIN_SIZE; m <= 100.0; m += BIN_SIZE){
           if (N2[k] <=m) {
               //store the values to the appropriate place in the array
               // ie, if its in <= 30 put it in bin 3 or bin index 2 (ie ((30 / 10 =3 -1 = 2)
               histogram[(m/BIN_SIZE)-1] += 1;
               break;
           }
       }
   }
   printHistogram(histogram);
}
}

     
    
    
    
    