/* File: PalindromeTest.java
 * Author: Megan Horan (mrhoran@bu.edu)
 * Date: January 29th, 2016
 * Purpose: Paldindrome test for Problem B.1 (Lab problem)
 * 
 */

//The purpose of this function to to check whether a given string, by the user, is a palindrome
// Input and out put are both strings

import java.util.Scanner;

public class PalindromeTest { 
  
  public static void main(String[] args) {
    
    // Print out welcome message
    
    System.out.println("\nWelcome to the Palindrome Test Program!");
    
    
    // Define a scanner for user input
    
    Scanner userInput = new Scanner(System.in);
  
    System.out.println("\nType in a sentence or Control-d to end:"); 
    
    // this while loop makes sure that the user has a next line of input and print it out
    while(userInput.hasNextLine()) {
       String line = userInput.nextLine();
       
     
   // turn the string to lowercase
       String lower = line.toLowerCase();
   
// for loop to check each character remove whitespace 
       for (int i = 0; i < lower.length(); i++) {
           char c = lower.charAt(i);
 //remove whitespace 
           if (Character.isWhitespace(c) == true) {
    
               String x = (Character.toString(lower.charAt(i))); 
               lower = lower.replace(x,"");
           }  
       }

//initializing the array of puntuation characters to remove
   char[] A = { '.' , ',',':', ';', '!','?','"','/','\'','-','(', ')', '~' };
   
//making the string lowercase 
// lower is the name of the new string created from the orignial String[]
   for (int j = 0; j < A.length; j++) {
       
       String newStr = (Character.toString(A[j])); 
       lower = lower.replace(newStr, "");
   }

//Checking its a palindrome:
// My approach is to make a mirror string by concatonating backwards all the letters of the original string
// and seeing if those two strings are equal
   String mirrorString = "";
   
   for (int m = ((lower.length())-1); m >= 0; m--) {
       mirrorString = mirrorString  + lower.charAt(m);
   } 
   
   if (lower.equals(mirrorString) == true) {
       System.out.println("Palindrome!");
   }
   else {
       System.out.println("Not a Palindrome.");
   }
  
    }
   // end of the while loop
    
  System.out.println("bye!");
}
}