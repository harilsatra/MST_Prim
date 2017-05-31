/**
 * @author Haril Satra
 *
 */
 /*
 --------------------------ALGORITHM -> MST-Prim(G,w):
 
 s <- arbitrary vertex in G
 S <- null, d(s) <- 0 and d(v) <- INF for every v which belongs to V\{s}
 while S != V, do
 	u <- vertex in V\S with minimum d(u)
 	S <- S U {u}
 	for each v which belongs to V\S s.t (u,v) belongts to E
 		if w(u,v) < d(v) then
 			d(v) <- w(u,v)
 			pi(v) <- u
 return {(u,pi(u))|u belongs to V\{s}}
*/
/*
 --------------------------ALGORITHM -> Heap (Concrete Data Structure for Priority Queue) :
 insert(v,key_value){
	s <- s+1
	A[s] <- v
	p(v) <- s
	key(v) <- key_value
	heapify_up(s)
 }

 heapify_up(i){
	while i > 1
		j <- floor(i/2)
		if key(A[i]) < key(A[j]) then
			swap A[i] and A[j]
			p(A[i]) <- i, p(A[j]) <- j
			i <- j
		else
			break
 }

extract_min(){
	ret <- A[1]
	A[1] <- A[s]
	p(A[1]) <- 1
	s <- s-1
	if s >= 1 ten
		heapify_down(1)
	return ret
}

heapify_down(i){
	while 2i <= s
		if 2i ==s or key(A[2i]) <= key(A[21+1]) then
			j <- 2i
		else
			j <- 2i + 1
		if key(A[j]) < key(A[i]) then
			swap A[i] and A[j]
			p(A[i]) <- i, p(A[j]) <- j
			i <- j
		else break
}

decrease_key(v,key_value){
	key(v) <- key_value
	heapify_up(p(v))
}
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MST_Haril_Satra_50208283{
	
	static class Node{
		int n;
		int wt;
		
		public Node(int n, int wt){
			this.n=n;
			this.wt=wt;
		}
	}
	
	static class Vertex{
		int node;
		int weight;
		
		public Vertex(int node, int weight){
			this.node=node;
			this.weight=weight;
		}
	}
	//Creating a Priority Queue
	static class PriorityQueue{
		Vertex[] heap;
		int heapSize;
		int capacity;
		int[] p;
		
		PriorityQueue(int capacity){
			this.capacity=capacity;
			heap=new Vertex[this.capacity+1];
			heapSize=0;
			p=new int[capacity+1];
		}
		
		public void insert(int node, int weight){
			Vertex newVertex=new Vertex(node,weight);
			heapSize++;
			heap[heapSize]=newVertex;
			p[newVertex.node]=heapSize;
			heapify_up(heapSize);
		}
		
		public void heapify_up(int i){
			while(i>1)
			{
				int j=(int) Math.floor(i/2);
				if(heap[i].weight<heap[j].weight)
				{
					Vertex temp=heap[i];
					heap[i]=heap[j];
					heap[j]=temp;
					p[heap[i].node]=i;
					p[heap[j].node]=j;
					i=j;
				}
				else
					break;
			}
		}
		
		public Vertex extract_min(){
			Vertex ret=heap[1];
			heap[1]=heap[heapSize];
			p[heap[1].node]=1;
			heapSize--;
			if(heapSize>=1){
				heapify_down(1);
			}
			return ret;
		}
		
		public void decrease_key(int node, int weight){
			heap[p[node]].weight=weight;
			heapify_up(p[node]);
		}
		
		public void heapify_down(int i){
			int j;
			while((2*i)<=heapSize){
				if((2*i)==heapSize || heap[2*i].weight<=heap[(2*i)+1].weight){
					j=2*i;
				}
				else
					j=(2*i)+1;
				if(heap[j].weight<heap[i].weight){
					Vertex temp=heap[i];
					heap[i]=heap[j];
					heap[j]=temp;
					p[heap[i].node]=i;
					p[heap[j].node]=j;
					i=j;
				}
				else
					break;
			}
		}
		
	}
	static ArrayList<ArrayList<Node>> a= new ArrayList<ArrayList<Node>>(); 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	
		PriorityQueue pq= new PriorityQueue(5);
		ArrayList<Node> y=new ArrayList<Node>();
		int l=0;
		int no_of_nodes,no_of_edges;
		File inputFile=new File("input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile),"UTF-8"));
		File outputFile=new File("output.txt");
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8"));
		PrintWriter out=new PrintWriter(output);
		String sCurrentLine;
		ArrayList<String> inputLines=new ArrayList<String>();
		while ((sCurrentLine = br.readLine()) != null) {
			inputLines.add(sCurrentLine);
			l++;
		}
		String[] firstLine=inputLines.get(0).split("\\s+");
		no_of_nodes=Integer.parseInt(firstLine[0]);
		no_of_edges=Integer.parseInt(firstLine[1]);
		int temp=0;
		while(a.size()<=no_of_nodes){
			ArrayList<Node> tmp=new ArrayList<Node>(); 
			a.add(temp,tmp);
			temp++;  
		}
				
		for(int j=1;j<inputLines.size();j++){
			String[] Line=inputLines.get(j).split("\\s+");
			Node tmp1=new Node(Integer.parseInt(Line[1]),Integer.parseInt(Line[2]));
			Node tmp2=new Node(Integer.parseInt(Line[0]),Integer.parseInt(Line[2]));
			a.get(Integer.parseInt(Line[0])).add(tmp1);
			a.get(Integer.parseInt(Line[1])).add(tmp2);
		}
		prim(a,no_of_nodes);
		printPrim(out);
		br.close();
		output.close();
	}
	
	static int pi[];
	static int w[];
	static int finalWeight=0;
	static ArrayList<Integer> result=new ArrayList<Integer>();
	public static void prim(ArrayList<ArrayList<Node>> a, int no_of_nodes){
		PriorityQueue pq= new PriorityQueue(no_of_nodes);
		
	    int key[]=new int[no_of_nodes+1];
	    pi=new int[no_of_nodes+1];
	    w=new int[no_of_nodes+1];
	    int rand=(int) (Math.random()*no_of_nodes+1);
		pq.insert(rand,0);
		for (int v = 1; v <= no_of_nodes; v++)
	    {
			if(v!=rand){
				 key[v] = Integer.MAX_VALUE;
			        pq.insert(v,key[v]);
			}
	       
	    }
		while(result.size()!=no_of_nodes){
			int u=pq.extract_min().node;
			result.add(u);
				for(int j=0;j<a.get(u).size();j++){
					if(!result.contains(a.get(u).get(j).n))
					{
						int pos=pq.p[a.get(u).get(j).n];
						if(a.get(u).get(j).wt<pq.heap[pos].weight){
							pq.decrease_key(a.get(u).get(j).n,a.get(u).get(j).wt );
							pi[a.get(u).get(j).n]=u;
							w[a.get(u).get(j).n]=a.get(u).get(j).wt;
						}
					}
			}
		}
	}
	
	public static void printPrim(PrintWriter out){
		for(int i=1;i<a.size();i++){
			finalWeight=finalWeight+w[i];
		}
		out.println(finalWeight);
		for(int i=1;i<a.size();i++){
			if(pi[i]!=0){
				out.println(i+" "+pi[i]);
			}
				
		}
	}
	
	
}