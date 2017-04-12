import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

//DFS|BFS for undirected/directed connected/disconnected graph
public class Graph {
	private int V,E;//number of vertices,edges
	private int incidentMat[][];//incident matrix
	private AdjList[] adjList;//adjacency list
	private Queue<Integer> que;//Queue for BFS
	Graph(int v,int e){
		V=v;		
		E=e;
		adjList=new AdjList[V];//each element of this array stores the list of neighboring vertices
		
		
	}
	
	public void dfs(int start,boolean[] visited){
		visited[start]=true;
		System.out.print(start+" ");
		AdjList t=null;
		t=adjList[start];
		if(t!=null){
			for(Integer node:t.list){
				
				if(visited[node]==false){
					dfs(node,visited);
				}
			}
		}
	}
	public void bfs(int start, boolean[] visited){
		visited[start]= true;
		 //add the start vertex to the queue if not already added
		 if(!que.contains((int)start))
			 que.add(start);
		 
		 //get the neighbor list
		 AdjList t=adjList[start];
		 //add all the vertices in the neighbor list to the queue
		 if(t!=null){
			 for(int node:t.list){
				 if(visited[node]==false && !que.contains(node))
					 que.add(node);
			 }
		 }
		 //pop off the head of the queue and call bfs method recursively on the new head(neighbors of the popped off vertex)
		 if(!que.isEmpty()){
			 int head_elem=(int)que.remove();
			 System.out.print(head_elem+ " ");
			 if(!que.isEmpty())
				 bfs((int)que.peek(), visited);
		 }
	}
	public void displayMatrix(){
		System.out.print("** ");
		for(int k=0;k<E;k++)
			System.out.print(k+"\t");
		System.out.println("\n");
		for(int i=0;i<V;i++){
			System.out.print("v"+i+" ");
			for(int j=0;j<E;j++)
				System.out.print(incidentMat[i][j]+"\t");
			
			System.out.println();
		}
	}
	public void toAdjList(){
		for(int col=0;col<E;col++){
			ArrayList<Integer> tmp=new ArrayList<>();
			for(int row=0;row<V;row++){
				if(tmp.size()==2)
					break;
				
				if(incidentMat[row][col]==1 || incidentMat[row][col]==-1)
					tmp.add(row);
				
			}
			int vertex1=(int) tmp.get(0);
			int vertex2=(int) tmp.get(1);
			if(incidentMat[vertex1][col]==1 && incidentMat[vertex2][col]==1){//undirected graph
				if(adjList[vertex1]==null){
					adjList[vertex1]=new AdjList();
					
				}
				adjList[vertex1].list.add(vertex2);
				
				if(adjList[vertex2]==null){
					adjList[vertex2]=new AdjList();
					
				}
				adjList[vertex2].list.add(vertex1);
			}
			
			else{//directed graph
				if(incidentMat[vertex1][col]==-1){//vertex1-incoming;vertex2-outgoing
					if(adjList[vertex2]==null){
						adjList[vertex2]=new AdjList();
						
					}
					adjList[vertex2].list.add(vertex1);
				}
				else{//vertex1-outgoing and vertex2-incoming
					if(adjList[vertex1]==null){
						adjList[vertex1]=new AdjList();
						
					}
					adjList[vertex1].list.add(vertex2);
				}
			}
						
		}
	}
	public void displayAdjList(){
		for(int i=0;i<V;i++){
			System.out.print("\n"+i+" : ");
			if(adjList[i]!=null)
				adjList[i].display();
			else
				continue;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean[] visited=new boolean[11];//by default, all values are initialized to false 
		
		//populate adjacency matrix
		//DFS:undirected connected graph
		System.out.println("Undirected connected graph :");
		Graph g=new Graph(11,16);
		g.incidentMat=new int[][]{{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
								{1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
								{0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
								{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
								{0,0,0,1,1,0,0,0,0,0,0,0,1,0,0,0},
								{0,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1},
								{0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,1,1,0,1,0,1,0,0},
								{0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1}};
								
		System.out.println("\nIncident Matrix:");						
		g.displayMatrix();
		g.toAdjList();
		System.out.println("\nCorresponding Adjacency list :");
		g.displayAdjList();
		System.out.println("\n\nDFS : ");
		for(int i=0;i<g.V;i++){
			if(visited[i]==false)
				g.dfs(i,visited);
		}
		//BFS:undirected connected graph
		visited=new boolean[11];
		System.out.println("\n\nBFS :");
		g.que=new LinkedList<Integer>();
		for(int i=0;i<g.V;i++){
			if(visited[i]==false)
				g.bfs(i,visited);
		}
		
		//DFS: undirected disconnected graph
		System.out.println();
		Graph g1=new Graph(11,12);
		visited=new boolean[11];//reset the visited array to false(for reuse)
		System.out.println("\nUndirected disconnected graph :");
		g1.incidentMat=new int[][]{{1,0,0,0,0,0,0,0,0,1,0,0},
								{1,1,0,0,0,0,0,0,0,0,0,1},
								{0,1,1,0,0,0,0,0,0,0,0,0},
								{0,0,1,1,0,0,0,0,0,0,0,0},
								{0,0,0,1,1,0,0,0,0,0,0,0},
								{0,0,0,0,1,1,0,0,0,0,1,0},
								{0,0,0,0,0,1,1,0,0,0,0,0},
								{0,0,0,0,0,0,1,1,0,0,0,0},
								{0,0,0,0,0,0,0,1,1,0,1,0},
								{0,0,0,0,0,0,0,0,1,1,0,1},
								{0,0,0,0,0,0,0,0,0,0,0,0}};
		
		System.out.println("\nIncident Matrix:");	
		g1.displayMatrix();
		g1.toAdjList();
		System.out.println("\nCorresponding Adjacency list :");
		g1.displayAdjList();
		System.out.println("\n\nDFS :");
		
		for(int i=0;i<g1.V;i++){
			if(visited[i]==false)
				g1.dfs(i,visited);
		}
		//BFS: undirected disconnected graph
		visited=new boolean[11];
		System.out.println("\n\nBFS :");
		g1.que=new LinkedList<Integer>();
		for(int i=0;i<g1.V;i++){
			if(visited[i]==false)
				g1.bfs(i,visited);
		}
		
		//DFS:directed connected graph
		System.out.println();
		Graph g2=new Graph(11,16);
		visited=new boolean[11];//reset the visited array to false(for reuse)
		System.out.println("\nDirected connected graph :");
		g2.incidentMat=new int[][]{{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
								{-1,-1,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
								{0,1,-1,0,0,0,0,0,0,0,0,0,0,0,1,0},
								{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
								{0,0,0,-1,-1,0,0,0,0,0,0,0,1,0,0,0},
								{0,0,0,0,1,1,0,0,0,0,-1,0,0,0,0,-1},
								{0,0,0,0,0,-1,-1,0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,1,-1,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,1,-1,0,1,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,1,-1,0,1,0,-1,0,0},
								{0,0,0,0,0,0,0,0,0,0,0,-1,-1,0,-1,1}};
		
		System.out.println("\nIncident Matrix:");	
		g2.displayMatrix();
		g2.toAdjList();
		System.out.println("\nCorresponding Adjacency list :");
		g2.displayAdjList();
		System.out.println("\n\nDFS :");
		
		for(int i=0;i<g2.V;i++){
			if(visited[i]==false)
				g2.dfs(i,visited);
		}
		//BFS: directed connected graph
		visited=new boolean[11];
		System.out.println("\n\nBFS :");
		g2.que=new LinkedList<Integer>();
		for(int i=0;i<g2.V;i++){
			if(visited[i]==false)
				g2.bfs(i,visited);
		}
		
		//DFS:directed disconnected graph
		System.out.println();
		Graph g3=new Graph(11,12);
		visited=new boolean[11];//reset the visited array to false(for reuse)
		System.out.println("\nDirected disconnected graph :");
		g3.incidentMat=new int[][]{{1,0,0,0,0,0,0,0,0,0,0,0},
								{-1,-1,0,0,0,0,0,0,0,0,0,0},
								{0,1,-1,0,0,0,0,0,0,0,1,0},
								{0,0,1,1,0,0,0,0,0,0,0,0},
								{0,0,0,-1,-1,0,0,0,0,1,0,0},
								{0,0,0,0,1,1,0,0,-1,0,0,-1},
								{0,0,0,0,0,-1,-1,0,0,0,0,0},
								{0,0,0,0,0,0,1,-1,0,0,0,0},
								{0,0,0,0,0,0,0,1,1,0,0,0},
								{0,0,0,0,0,0,0,0,0,0,0,0},
								{0,0,0,0,0,0,0,0,0,-1,-1,1}};
		System.out.println("\nIncident Matrix:");	
		g3.displayMatrix();								
		g3.toAdjList();		
		System.out.println("\nCorresponding Adjacency list :");
		g3.displayAdjList();
		System.out.println("\n\nDFS :");
		
		for(int i=0;i<g3.V;i++){
			if(visited[i]==false)
				g3.dfs(i,visited);
		}
		//BFS: directed disconnected graph
		visited=new boolean[11];
		System.out.println("\n\nBFS :");
		g3.que=new LinkedList<Integer>();
		for(int i=0;i<g3.V;i++){
			if(visited[i]==false)
				g3.bfs(i,visited);
		}
	
	}
	
	private class AdjList{
		private ArrayList<Integer> list;
		AdjList(){
			list=new ArrayList<>();
		}
		public void display(){
			
			for(int j=0;j<list.size();j++)
				System.out.print(list.get(j)+" ");
			
		}
	}

}
