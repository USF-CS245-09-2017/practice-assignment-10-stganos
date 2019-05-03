import java.util.*;

public class GraphImplementation implements Graph
{
	//data
	int[][] GraphMatrix;
	int size;

	//constructor
	public GraphImplementation(int vert)
	{
		GraphMatrix = new int[vert][vert];
		size = vert;
	}//Constructor

	//connects one vertex to the other using a directed edge
	public void addEdge(int v1, int v2)
	{
		GraphMatrix[v1][v2] = 1;
	}//addEdge
	
	//topological sort
	public Stack topologicalSort()
	{
		//list which will hold the sorted vertices
		Stack SortedList = new Stack();

		//created an array to keep track of which elements have been visited
		boolean[] visited = new boolean[size];
		
		//sets all values false
		for (int v = 0; v < size; v++)
		{
			visited[v] = false;
		}//for

		//goes through all vertices and uses overrided method if they have not been visited
		for (int i = 0; i < size; i++)
		{
			if (!visited[i])
			{
				topologicalSort(i, visited, SortedList);
			}//if
		}//for

		return SortedList;
	}//topologicalSort(no parameters)

	//overrided topologicalSort, which is recursively called for each vertex from the original method
	public void topologicalSort(int v, boolean[] traveled, Stack toSort)
	{
		traveled[v] = true;
		int curr = 0;

		int[] neighborsArr = neighbors(v);

		//finds neighbor if it has any, then does topologicalSort to that neighbor
		while (curr < neighborsArr.length)
		{
			if(!traveled[curr])
			{
				topologicalSort(curr, traveled, toSort);

			}//if
		}//while

		//adds when has no neighbors
		toSort.push(new Integer(v));
	}//topologicalSort(with parameters)
	
	//uses an array to show if the given vertex connects to any of the others
	public int[] neighbors(int vertex)
	{
		int aSize = 0;
		int nIndex = 0;

		//finds size for neighbor array
		for (int p = 0; p < size; p++)
		{
			if (GraphMatrix[vertex][p] > 0)
			{
				aSize++;
			}//if
		}//for


		int[] neighbors = new int[aSize];
		for (int i = 0; i < size; i++)
		{
			if (GraphMatrix[vertex][i] > 0)
			{
				neighbors[nIndex++] = i;
			}//if
		}//for
		
		return neighbors;
	}//neighbors

	public boolean hasNeighbor(int[] toCheck)
	{
		for (int index = 0; index < size; index++)
		{
			if(toCheck[index] == 1)
			{
				return true;
			}//if
		}//for

		return false;
	}//hasNeighbors
}//class