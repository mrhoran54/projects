/*
 * TermFrequencyTable.java
 * Author: Megan Horan (mrhoran@bu.edu) 
 * CS112 HW09 
 * Date: 4/13/15
 * 
 * purpose: this program will initialize a hash table, which will have term frequency of two inserted documents
 * 
 */
import java.lang.Math;
import java.util.Arrays;
import java.util.StringTokenizer;  

public class TermFrequencyTable {
    
      
      public static boolean debug = true;
      
      private class Node{
          
          String term;
          int[] termFreq = new int[2];                    // this gives the term frequency in each of two documents for this term
          Node next;
          Node next2;
      
      
      public Node(String term) {  //constructor without a next2 pointer
              
           this.term = term;
           this.termFreq[0] = 0;    //need to make these zero instead of null
           this.termFreq[1] = 0;
           this.next = null;
           
       }
      
      }
       
      private Node head = null;
       
       private final int SIZE = 103;  
       
       private Node [] T = new Node[SIZE];
       
       private static final String [] blackList = { "the", "of", "and", "a", "to", "in", "is", 
           "you", "that", "it", "he", "was", "for", "on", "are", "as", "with", 
           "his", "they", "i", "at", "be", "this", "have", "from", "or", "one", 
           "had", "by", "word", "but", "not", "what", "all", "were", "we", "when", 
           "your", "can", "said", "there", "use", "an", "each", "which", "she", 
           "do", "how", "their", "if", "will", "up", "other", "about", "out", "many", 
           "then", "them", "these", "so", "some", "her", "would", "make", "like", 
           "him", "into", "time", "has", "look", "two", "more", "write", "go", "see", 
           "number", "no", "way", "could", "people",  "my", "than", "first", "water", 
           "been", "call", "who", "oil", "its", "now", "find", "long", "down", "day", 
    "did", "get", "come", "made", "may", "part" }; 

      
       /*CITATION: this hash function was taken from the cs112 website from the hashing tutorial website:
        * http://research.cs.vt.edu/AVresearch/hashing/strings.php
        * The purpose of the funciton is to add up all the acsii values of the string and hash that number 
        */
        
       int hash(String x) {
           char ch[];
           ch = x.toCharArray();
           int xlength = x.length();

           int i, sum;
           for (sum=0, i=0; i < x.length(); i++)
               sum += ch[i];
           return (sum * 251) % SIZE;   // I added a prime number multiplier 
       }
       
      //helper function to insert
     //this function will get all the non-blacklisted terms from a document and put them in an array
       //mainly for test cases
       public static String[] processDoc(String a){  
       
      
           String[] A = a.split(" ");//first split the string
           
           int counter=0;
           
           for (int j=0; j < A.length; j++){         //these two for loops go through the string and remove the blacklist items
                   
               for(int i =0; i < blackList.length; i++){
                   
                   if(A[j].equals(blackList[i])){
                           
                       A[j] = "";
                       counter ++;                    //this counter variables determines the # words deleted
                   }   
               }
           
           }
           
           String[] B = new String[A.length-counter];   //now we can make an array the size of the string minus the black listed words
           
           int m = 0;
               
           for(int k = 0;k < A.length; k++){          //now put all the non-blacklisted terms into this new array!!
               
               if(A[k] != ""){
                   
                   B[m] = A[k];
                   m++;
               }
           }
           
           return B;
          
       }
            
       
   
       public void insert(String term, int docNum) {
       
            T[hash(term)] = insertHelper(term, docNum, T[hash(term)]);
            
       }
       
        public Node insertHelper(String term, int docNum, Node p) {    // this inserts a new node into the termFreq table, and returns the result 
       
           if (p == null){                 // if the linked list bucket is empty, just add a new node to the front.
               
               p = new Node(term);         // this adds a new node to the bucket
               
               p.termFreq[docNum]++;       //this increments the termFreq value
              
               p.next2 = head; 
               head = p;                   // this will then reorient head to the newly added node
               
               return p; 
           }
                                  
           else if (p.term.equals(term)){  //else if the title is already a member, don't add it, just increment the correct int[]
               
               p.termFreq[docNum]++;
               
               return p;
               
           }
                                          //otherwise, keep chainging along
           else {
               
               p.next = insertHelper(term, docNum, p.next);
               return p;
           }
       }
        
        public double cosineSimilarity(){      //this function will find the cosine similarrity for two docs
            
            int dotProductAA = 0;
            int dotProductBB = 0;
            int dotProductBA = 0;
            
            for(int i = 0; i < SIZE; i++){
                
                Node p = T[i];
                
                while (p != null){
                    
                    dotProductAA += (p.termFreq[0] * p.termFreq[0]);
                    dotProductBB += (p.termFreq[1] * p.termFreq[1]);
                    dotProductBA += (p.termFreq[0] * p.termFreq[1]);
                    p = p.next;
                }
            }
           
            
            return dotProductBA / (Math.sqrt(dotProductAA) * Math.sqrt(dotProductBB));
        
        }
        
        public String printFreqTable(){      //this function will find the cosine similarrity for two docs
            
            String s = "";
            for(int i = 0; i < SIZE; i++){
                
                Node p = T[i];
                
                while (p != null){
                    
                    s+= p.term + " : ";
                    s+= "\n 0 : "+ p.termFreq[0];
                    s+= "\n 1 : "+ p.termFreq[1] + "\n";
                    
                    p = p.next;
                }
            }
            return s;
        }
         
        public static void main(String[] args) {
            
            TermFrequencyTable L = new TermFrequencyTable(); 
           
            String a = "the man with the hat ran up to the man with the dog";
            
            String[] b = processDoc(a);
            
            System.out.println("\nUnit Test 1, testing out processDoc: should print out [man, hat, ran, man, dog]  \n");
            
            System.out.print(Arrays.toString(b)+" \n");
            
            String d = "a man with a hat approached a dog and a man";
            
           System.out.println("\nUnit Test 2, testing out processDoc: should print out [man, hat, approached, dog, man] \n");
            
            String[] c =processDoc(d);
            
            System.out.print(Arrays.toString(c)+"\n");
            
            System.out.println("\nUnit Test 2.5: should print out the whole table \n");
            
            for(int i = 0; i < b.length; i++)
           
                L.insert(b[i],0);
            
            for(int i = 0; i < b.length; i++)
           
                L.insert(c[i],1);
            
            System.out.println(L.printFreqTable());
            
            System.out.println("\nUnit Test 3: should be 0.8571 \n");
            
            double x = L.cosineSimilarity();
            System.out.println(x);
            
            System.out.println("\nUnit Test 4: should be 1.0 \n");
            
            TermFrequencyTable H = new TermFrequencyTable(); 
            
            String e = "A A B B";
            String f = "A B";
            
            String[] g = processDoc(e);
            
            String[] h = processDoc(f);
            
            for(int i = 0; i < g.length; i++)
           
                H.insert(g[i],0);
            
            for(int i = 0; i < h.length; i++)
           
                H.insert(h[i],1);
            
           
            double z = H.cosineSimilarity();
            System.out.println(z);
            
            System.out.println("\nUnit Test 5: should be 0.9487 \n");
            
            TermFrequencyTable M = new TermFrequencyTable();
            
            String p = "CS112 HW10";
            
            String q = "CS112 HW10 HW10";
            
            String[] r = processDoc(p);
            
            String[] s = processDoc(q);
           
            
            for(int i = 0; i < r.length; i++)
           
                M.insert(r[i],0);
            
            for(int i = 0; i < s.length; i++)
           
                M.insert(s[i],1);
            
            double t = M.cosineSimilarity();
            System.out.println(t);
            
            
        }
        
}
    
