//Prim's algorithm to find Minimum Spanning Tree
public class Prims {
	private int V,E;
	private int adjacentMat[][];
	private int weight[];
	private int prev[];
	private boolean mst[];
	Prims(int v,int e){
		V=v;
		E=e;
		weight=new int[V];//stores updated minimum weights to reach each vertex from its previous vertex
		for(int i=0;i<V;i++)
			weight[i]=Integer.MAX_VALUE;
		prev=new int[V];//stores the index of the previous vertex
		mst=new boolean[V];
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Prims pr=new Prims(11,16);//(Vertices, Edges)
		pr.adjacentMat=new int[][]{{0,30,0,110,0,0,0,0,0,70,0},
			{30,0,40,0,0,0,0,0,0,0,0},
			{0,40,0,40,0,0,0,0,0,0,0},
			{110,0,40,0,100,0,0,0,0,0,50},
			{0,0,0,100,0,70,0,0,0,0,70},
			{0,0,0,0,70,0,80,0,0,0,0},
			{0,0,0,0,0,80,0,50,0,0,90},
			{0,0,0,0,0,0,50,0,120,110,0},
			{0,0,0,0,0,0,0,120,0,80,0},
			{70,0,0,0,0,0,0,110,80,0,50},
			{0,0,0,50,70,0,90,0,0,50,0}};//adjacency matrix
		System.out.println("Undirected graph : Vertex1----Vertex2 (Edge Weight)");
	    pr.displayInpGraph();
		pr.primMST();
		System.out.println();
		pr.displayMST();
		
		System.out.println("\n\nDirected graph : Vertex1---->Vertex2 (Edge Weight)");
		pr.adjacentMat=new int[][]{{0,30,0,110,0,0,0,0,0,0,0},
			{0,0,40,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,40,0,100,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,70},
			{0,0,0,0,70,0,80,0,0,0,0},
			{0,0,0,0,0,0,0,50,0,0,90},
			{0,0,0,0,0,0,0,0,0,110,0},
			{0,0,0,0,0,0,0,120,0,80,0},
			{70,0,0,0,0,0,0,0,0,0,50},
			{0,0,0,50,0,0,0,0,0,0,0}};
		pr.displayInpGraph();
		pr.primMST();
		System.out.println();
		pr.displayMST();
									
	}
	private void displayInpGraph() {
		// TODO Auto-generated method stub
		
		System.out.println("Input Graph:");
		
		for(int row=0;row<V;row++){
			
			for(int col=0;col<V;col++){
				if(adjacentMat[row][col]!=0)
					System.out.print(row+" - "+col+" ("+adjacentMat[row][col]+")\t");//row+" - "+
			}
			System.out.println();
		}
		
	}
	private void displayMST() {
		// TODO Auto-generated method stub
		System.out.println("MST using Prim's algorithm: ");
		for(int i=1;i<V;i++){
			if(adjacentMat[i][prev[i]]!=0)
				System.out.println(i+" - "+prev[i]+" ("+weight[i]+")");
			else
				System.out.println(prev[i]+" - "+i+" ("+weight[i]+")");
		}
		
	}
	private void primMST() {
		// TODO Auto-generated method stub
		weight[0]=0;
		prev[0]=-1;
		for(int src=0;src<V;src++){
			int min_vertex=minimumVertex();
			mst[min_vertex]=true;
			for(int dst=0;dst<V;dst++){//for all vertices adjacent to min_vertex
				if(mst[dst]==false && adjacentMat[min_vertex][dst]!=0 && adjacentMat[min_vertex][dst]<weight[dst]){
					weight[dst]=adjacentMat[min_vertex][dst];
					prev[dst]=min_vertex;
				}
					
			}
			
		}
		
	}
	private int minimumVertex() {
		// TODO Auto-generated method stub
		//find the vertex which can be reached via minimum weight among all unvisited vertices
		int minimum=Integer.MAX_VALUE;
		int min_vertex=0;
		for(int i=0;i<V;i++){
			if(mst[i]==false && weight[i]<minimum){
				minimum=weight[i];
				min_vertex=i;
			}
		}
		return min_vertex;
	}
}


