/*Graph.java
 * 
 * CS112 Homework 10
 * Author: Megan Horan (mrhoran@bu.edu)
 * Partner: Andrew Goo (agoo@bu.edu)
 * 
 * This program will represent the "board" on which the game plays, and it will function in the SIM Game as a 6x6 int matrix: 
 * all the moves will be made in the graph, and players will add edges and remove edges as they try various moves
 * 
 */


public class Graph{
    
    int[][] body;
    
        
    public Graph(int SIZE){  //contsruct a new graph
        
    this.B = new int[SIZE][SIZE];  
    
        
    for(int i = 0; i< SIZE; i++){
            
        for(int j = 0; j < SIZE; j++){
               
               
               B[i][j] = 0;
        }
    
    }}
          
   
    private static int[][] B;
  
    private static final int SIZE = 6; // set the size
    
    private static boolean[][] visited = new boolean[SIZE][SIZE];  // make a matrix of boolean values: will be used to keep track of 
                                                                   //visited Nodes
    
    public void addEdge(int u, int v, int w){
        // add an edge from vertex u to v with value w (which in this hw will be  only 0, -1, or 1)
        
        B[u][v] = w;
        B[v][u] = w;
       
    }
    
    public void removeEdge(int u, int v){           // remove the edge from u to v and the (duplicate) edge from v to u
    
         B[u][v] = 0;
         B[v][u] = 0;
    }
    
    public int getEdge(int u, int v){
                                             // return the value (-1, 0, or 1) of the edge that goes from u to v
        return(B[u][v]);
    }
 
    
    public boolean isEdge(int u, int v){            // return true or false depending on whether there is an edge (of either color) from u to v
        
        if(B[u][v] != 0){
            
            return true;
        }
        else
            return false;
        
    }
    
        
    public boolean isEdgeHelper(int u, int v, int w){            // return true or false depending on whether there is an edge (of either color) from u to v
        
        if(B[u][v] == w){
            
            return true;
        }
        else
            return false;
        
    }
    
    public int degree(int v){                       // return the number of edges of either color connected to vertex v
     
        int sum = 0;
        
        for(int i = 0; i< SIZE; i++){
            
            if (B[v][i] == 1 || B[v][i] == -1){
                
                sum++;
            }
            else
                continue;
        }
        
        return sum;

    }
    
    public int degree(int v, int w){                // return the number of edges of color w connected to vertex v
    
        int sum = 0;
        
        for(int i = 0; i< SIZE; i++){
            
            if (B[v][i] == w){
                
                sum++;
            }
            else
                continue;
        }
        
        return sum;
    }
    
    public void printEdges(){                       // print out the edge matrix, as shown above; this is only for debugging
        
       String init = ("   ");
       for (int x = 0; x <= SIZE -1; x++)
       
           init += " " + x + " ";
       
       System.out.println(init);
       
       for (int y = 0; y <= SIZE -1; y++) {
           
           String row = y + ":  ";
           for (int z = 0; z <= SIZE -1 ; z++) {
               if(B[y][z] == -1)
                   row += ""+B[y][z] +" ";
               else 
                   row += " "+B[y][z] +" ";
                   
           }
           System.out.println(row);
    }
    }
   
    public void visitHelper(boolean[][] A){            // return true or false depending on whether there is an edge (of either color) from u to v
        
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A.length; j++)
            
                A[i][j] = false;
        }
    }
   
    public boolean isCycleOfLength(int n, int w) {  // return true iff there is a cycle of length n among edges of color w  
                                                   //this will be used to check if there is a win in the game
        
        for (int x = 0; x <= SIZE -1; x++) { //iterate through all the vertices
            
            visitHelper(visited);   //reset thevisited values to all false
            
            if (isCycleHelper(x, x, n, w) == true) {
                
                return true; //if you find a cycle, look no more! return true
            }
        
        }
        return false;
    }
        
    private boolean isCycleHelper(int u, int v, int n, int w) { //helper function
                 
         if(n == 0){  //base case: see if the cycle of length n is actually a cycle(ie use v, the first vertix you input)
             
            return v == u; // if not, return to iscycle
        }
        
        else{
            
            for (int x = 0; x <= SIZE - 1; x++) {
                
              
                if (isEdgeHelper(u, x , w) && (visited[u][x] == false)) { //if there is an edge of a certain value(w)
                                                                           //and it hasnt been visited before!
                    
                    visited[x][u] = true;  // set visited to true in the boolean matrix I made
                    
                    return isCycleHelper(x, v, (n - 1), w);  // keep lookingat the next vertix(at x now), subract from n 
                }
                
            }
        
        return false;
    
        }
    }
    
    public boolean helperFullBoard(){  //helper function to see if the board is full(will use as the base case in player.java
        
        for(int i = 0; i < SIZE; i++) {
            for(int j= 0; j < SIZE; j++){
                
                if(getEdge(i,j) == 0)
                    
                    return false;
                
            }
        }
        return true;
        
    }

    
    public static  void main(String [] args) {
    
        Graph A = new Graph(6);
        
        System.out.println("Test 2: should print everything in the grap \n");
       
        A.addEdge(0, 1, -1);

        A.addEdge(0, 3, -1);
        
        A.addEdge(0, 2, -1);
        
        A.addEdge(5,1, 1);
        
        A.addEdge(1,3, 1);
        
        A.addEdge(3,5, 1);
        
        A.addEdge(5,5, 1);
        
        A.printEdges();
        
        System.out.println("Test 2: should have removed the value at B[2][5] \n");
        
        A.removeEdge(5,5);
        
        A.printEdges();
        
        System.out.println("\n Test 2: should print out True \n");
        System.out.println(A.isEdge(0,1));
        
        System.out.println("\n Test 3: should print out -1 \n");
        System.out.println(A.getEdge(0,1));
        
        System.out.println("\n Test 4: should print out 3 \n");
        System.out.println(A.degree(0));
        
        System.out.println("\n Test 5: should print out 0 \n");
        System.out.println(A.degree(0,1));
    
 
         A.addEdge(1,4, -1);

        A.addEdge(4, 2, -1);
        
        A.addEdge(2, 1, -1);
        
        
        A.printEdges();        
        
        System.out.println("\n Test 5: should print out true \n");
        System.out.println(A.isCycleOfLength(3, -1));
        
        System.out.println("\n Test 6: should print out false \n");
        System.out.println(A.helperFullBoard());
        
    }



}
