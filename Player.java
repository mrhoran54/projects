/*Player.java
 * 
 * Author: Megan Horan (mrhoran@bu.edu)
 * Partner: Andrew Goo (agoo@bu.edu)
 * CS112 HW10
 * 
 * 
 * This function creates a Player, based on an evaluation function and min-max search. This player will score each potential move
 * and choose the best possible outcome.
 * 
 */


public class Player{
    

    private static final int D = 6; //DEPTH
    
    private static final int Inf = 100000; //max/min value
    
    public static int SIZE = 6; // size of the graph
    
    public static int eval(Graph G) { //for comp playing blue, triangle means infinite loss
        
        // base case if there is a triangle, is one player wins
        
        if (G.isCycleOfLength(3, -1)) return -1000000;  
        if (G.isCycleOfLength(3, 1)) return 1000000;
        
        int red = 0;
        int blue = 0;
        
        for(int u = 0; u < SIZE - 1; ++u) {
            
            int sumR = 0;
            int sumB = 0;
            
            for(int v = 0; v < SIZE - 1; ++v) {
                
                if(G.getEdge(u,v) == 1) {
                    sumR++;
                }
                
                if(G.getEdge(u,v) == -1) {
                    sumB++;
                }
            }
             // check both colors for paths of length 2
              // subtract your paths from opponents
            if (sumR > 1) red++;
            if (sumB > 1) blue++;
        }
        
        int answer = red - blue;
        return answer;
        
        
    }
      
    public static int max(int x, int y){ //helper function for minmax tree
        
        if(x < y)
            return y;
        else 
            return x;
    }
    
    public static int min(int x, int y){ //helper function for minmax tree
        
        if(x < y)
            return x;
        else 
            return y;
    }
    // CITATION WARNING: the stucture of this function  is very heavily gleaned from the lecture slides about min-max trees
    public Move chooseMove(Graph G) { 
        
        int max = -Inf; 
        Move best = new Move(0,0);
        
        int best1; //initialize the scores for the move
        int best2;
        
        for (int i = 0; i < SIZE; ++i) {  
                for (int j = i + 1; j < SIZE; ++j) {
                   
                if(G.getEdge(i,j) == 0){  // go through all the potential moves in the graph, ie where it equals 
                     
                     G.addEdge(i,j,-1); //add the move
                     
                     int val = minMax(G, 1, -Inf, Inf ); // get the score of the move
                     
                     if(val > max) {  //if that value is higher than another score, save it to max value
                         
                         best.source = i; 
                         best.target = j;
                         max = val; 
                     }
                     
                     G.removeEdge(i,j);                          //remove that potential score so as not to ruin the grap
                                 
                 }
            }     
        }
        
        return best; 
    } 
  
    public static int minMax(Graph A, int depth, int alpha, int beta ) {
        
        if( alpha == -Inf || beta == Inf || depth == D )  //see if there are no more moves!!
            
            return eval(A); // stop searching and return eval
        
        else if(depth % 2 == 1) {  //you know if you are at a max chil if depth is an odd number!
            
            int val = -Inf;
            
             for (int i = 0; i < SIZE; ++i) {
                for (int j = i + 1; j < SIZE; ++j) {
                 
                    A.addEdge(i,j,-1);
                
                    alpha = max(alpha, val); // update alpha with max so far
                
                    if(beta < alpha) break; // terminate loop if you don't need to search any more! 
                
                    val = max(val, minMax( A, depth+1, alpha, beta )); //update the value if the val from the recursive call is better
                    
                    A.removeEdge(i,j);
                }
                
                if(beta < alpha) break;  // check again if you dont need to search again
                return val;
            }
             
        }
            
            else { // is a min node, same idea as the code for the max node
            
            int val = Inf;
            
             for (int i = 0; i < SIZE; ++i) {
                for (int j = i + 1; j < SIZE; ++j) {
                 
                    A.addEdge(i,j,-1);
                    
                    beta = min(beta, val); // update beta with min so far
                
                    if(beta < alpha) break; // terminate loop
                
                    val = min(val, minMax(A, depth+1, alpha, beta) ); // since its a min node, want tofind the smaller value
                    
                    A.removeEdge(i,j);
                }
                if(beta < alpha) break;
            }
            return val;
        }
            return 0; //this line to make it compile...
    }
    
 

}