/* File MaxHeap.java
 * Author: Megan Horan
 * CS 112 HW09
 * 
 * CITATION: This code was taken and modified from the cs112 website. It was created by
 * Wayne Snyder. I made a few small changes, so instead of using ints in the array, I used articles.
 * And I compared the cs values of the given articles to compare inorder to find the max child and other operations
 */ 


class MaxHeap {
   
   private final int SIZE = 50;       // initial length of array
   private int next = 0;              // limit of elements in array
   private Article[] A = new Article[SIZE];   // implements tree by storing elements in level order
   
   // standard resize to avoid overflow
   
   private void resize() {
      Article[] B = new Article[A.length*2];
      for(int i = 0; i < A.length; ++i)
         B[i] = A[i];
      A = B; 
   }
   
   // methods to move up and down tree as array
   
   private int parent(int i) { return (i-1) / 2; }
   private int lchild(int i) { return 2 * i + 1; }
   private int rchild(int i) { return 2 * i + 2; }
   
   private boolean isLeaf(int i) { return (lchild(i) >= next); }
   private boolean isRoot(int i) { return i == 0; }
   
   // standard swap, using indices in array
   private void swap(int i, int j) {
      Article temp = A[i];
      A[i] = A[j];
      A[j] = temp;
   }
   
   // basic data structure methods
   
   public boolean isEmpty() {
      return (next == 0);
   }
   
   public int size() {
      return (next);
   }
   
   // insert an Article into array at next available location
   //    and fix any violations of heap property on path up to root
   
   public void insert(Article k) {
      
      if(size() == A.length) resize(); 
      
      A[next] = k; 
      
      int i = next;
      int p = parent(i);
      
      while(!isRoot(i) && A[i].getCS() > A[p].getCS()) {
         
         swap(i,p);
         i = p;
         p = parent(i);
         
      }
      
      ++next;
   }
   
   
   // Remove top (maximum) element, and replace with last element in level
   //    order; fix any violations of heap property on a path downwards
   
   public Article getMax() {
      
       --next;
      swap(0,next);                // swap root with last element
      int i = 0;                   // i is location of new key as it moves down tree
 
      // while there is a maximum child and element out of order, swap with max child
      int mc = maxChild(i); 
      
      // // instead of comparing integers , you are comparing the cs of the articles in the certain index
      while(!isLeaf(i) && A[i].getCS() < A[mc].getCS()) { 
         
         swap(i,mc);
         i = mc; 
         mc = maxChild(i);
         

      }
        
        return A[next];
   }
   

    // return index of maximum child of i or -1 if i is a leaf node (no children)
   
   int maxChild(int i) {
      
      
      if(lchild(i) >= next)
         return -1;
      if(rchild(i) >= next)
         return lchild(i);
      //instead of comparing ints, you want to compare the cs of the articles in the certain index
      
      else if(A[lchild(i)].getCS() > A[rchild(i)].getCS())
         return lchild(i);
      else
         return rchild(i); 
   }
     
   // Apply heapsort to the array A. To use, fill A with keys and then call heapsort
   
   public  void heapSort() {
      next = 0;
      for(int i = 0; i < A.length; ++i)      // turn A into a heap
         insert(A[i]);
      for(int i = 0; i < A.length; ++i)      // delete root A.length times, which swaps max into
         getMax();                           //  right side of the array
   }

   // debug method
   
   private void printHeap() {
      for(int i = 0; i < A.length; ++i)
         System.out.print(A[i] + " ");
      System.out.println("\t next = " + next);
   }
   
   private void printHeapAsTree() {
      
      printHeapTreeHelper(0, ""); 
      System.out.println(); 
   }
   
   private void printHeapTreeHelper(int i, String indent) {
      if(i < next) {
         
         System.out.println("\nCosine Similarity " + A[i].getCS());
         printHeapTreeHelper(rchild(i), indent + "   "); 
         System.out.println(indent + A[i]);
         printHeapTreeHelper(lchild(i), indent + "   "); 
      }
   }
   
  
   public static  void main(String [] args) {
      
      MaxHeap H = new MaxHeap(); 
      
      //unit test just to make sure it runs with articles and CS values now
 
      Article a = new Article("caterpiller", " caterpillars are people too");
      Article b = new Article("dog", "dogs are not cats");
      Article c = new Article("cats", "cats are the best");
      
      a.putCS(0.98);
      b.putCS(0.005);
      c.putCS(0.0221);
      
      System.out.println("Insert a and print heap:"); 
      
         H.insert(a);
         H.printHeapAsTree(); 
         
     System.out.println("Insert b and c and print heap:"); 
      
         H.insert(b);
         H.insert(c);
         H.printHeapAsTree(); 

         
     System.out.println("getMax() should print out 0.98"); 
         
     System.out.println(H.getMax());
     
     //Article e = H.getMax();
     //System.out.println(e.getCS());
    
   }
}