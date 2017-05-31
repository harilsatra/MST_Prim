Prim's algorithm for the minimum spanning tree problem, using the heap data structure.

Input: you need to read the input graph from the file "input.txt". In the first line of the file, we have two positive integers n and m. n is the number of vertices in the graph and m is the number of edges in the graph. The vertices are indexed from 1 to n. You can assume that
1 <= n <= 10000 and 1 <= m <= 100000. In the next m lines, each line contains 3 integers: u, v and w, with 1 <= u < v <= n and 1 <= w <= 10^6. This indicates that there is an edge (u, v) of weight w. You can also assume that the graph is connected and there are no parallel edges.

Output: You need to output to the file "output.txt". The first line of the file is an integer indicating the total weight of the minimum spanning tree. From line 2 to line n, you need to output the n-1 edges in the minimum spanning tree. Each line contains 2 integers between 1
and n, indicating the two end-points of an edge.

Sample input and output files are present as "input.txt" and "output.txt".