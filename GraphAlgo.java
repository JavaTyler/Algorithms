package Week12;

import java.util.*;

public class GraphAlgo {

    // MATRIX PORTION

    // Problem 1 Matrice
    public int[][] graph =
            {{0, 1, 0, 1, 0, 0, 0},
             {1, 0, 0, 0, 1, 0, 1},
             {0, 0, 0, 1, 0, 1, 1},
             {1, 0, 1, 0, 1, 0, 0},
             {0, 1, 0, 1, 0, 1, 1},
             {0, 0, 1, 0, 1, 0, 0},
             {0, 1, 1, 0, 1, 0, 0}}; // Mirror test passes

    // p2
    int[][] graph2 =
            {{0,0,0,1,1,0,0},
            {0,0,1,1,0,0,1},
            {0,1,0,1,0,0,0},
            {1,1,1,0,0,0,1},
            {1,0,0,0,0,1,0},
            {0,0,0,0,1,0,1},
            {0,0,0,1,0,1,0}};

    //p3
    int[][] graph3 =
            {{0,1,0,1,1,0,0},
            {0,0,1,1,0,0,1},
            {0,0,0,1,0,0,0},
            {0,0,0,0,0,1,1},
            {0,0,0,1,0,0,0},
            {0,0,0,0,1,0,0},
            {0,0,0,0,0,1,0}};

    //p4
    int[][] graph4 =
            {{0,1,0,1,1,0,0},
            {0,0,1,0,0,0,0},
            {0,0,0,0,0,0,1},
            {0,0,1,0,0,1,1},
            {0,0,0,1,0,0,0},
            {0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0}};

    // -- keep track of nodes that have been visited
    public static int[] marks = {0, 0, 0, 0, 0, 0, 0};
    public static int[] path = {0, 0, 0, 0, 0, 0, 0};

    public static void clearmarks() {
        int[] temp = {0, 0, 0, 0, 0, 0, 0};
        marks = temp;
    }

    // -- visitation is just printing the node being visited
    public static void Visit(int v) {
        System.out.print((v + 1) + ", ");
    }

    /*public static void clearpath() {
        int[] temp = {0, 0, 0, 0, 0, 0, 0};
        path = temp;
    }*/

    // -- mark node v as visited
    public static void Mark(int v) {
        marks[v] = 1;
    }

    // -- perform the depth first traversal from node v
    public void DepthFirstTraversal(int[][] graph, int v) {
        // -- visit node v
        Visit(v);
        // -- mark node v as visited
        Mark(v);
        // -- for all edges vw in graph
        // -- process smaller node number first
        for (int w = 0; w < graph[v].length; ++w) {
            if (graph[v][w] == 1) {
                // -- if w is not marked
                if (marks[w] == 0) {
                    // -- recursive call from node w
                    DepthFirstTraversal(graph, w);
                }
            }
        }
    }

    /*public void BreadthFirstTraversal(int[][] graph, int v) {
        Visit(v);
        Mark(v);

        Queue<Integer> Enqueue = new LinkedList<Integer>();
        while(!Enqueue.isEmpty()) {
            int current =
        }

    }*/


    public void MBreadthFirstTraversal(int[][] graph, int v) {
        Visit(v);
        Mark(v);

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(v);

        while(!queue.isEmpty()) {
            int current = queue.remove();
            for (int w = 0; w < graph[v].length; ++w) {
                // If there exists an edge at the node (row) at index (0 or 1, 1 for edge)
                if (graph[current][w] == 1) {
                    // if not marked, then mark
                    if (marks[w] == 0) {
                        Visit(w);
                        Mark(w);
                        queue.add(w);
                    }
                }
            }
        }
       // clearmarks(); // Clear marks since it is a static variable
    }

    // ADJACENCY LIST PORTION
    static class ListGraph {
        // According to the textbook an Adjacency graph consists of an array of LinkedLists
        public LinkedList<Integer>[] nodes;

        public ListGraph(int numNodes) {
            nodes = new LinkedList[numNodes];
        }
    }

    static ListGraph GetListGraph(int graphnum) {
        int num_nodes = 7;

        if (graphnum == 1) {
            // Problem #1
            ListGraph list = new ListGraph(num_nodes);

            // Node 1 - Connected to 2 and 4
            LinkedList<Integer> node1 = new LinkedList<>();
            node1.add(4);
            node1.add(2);
            list.nodes[0] = node1;

            // Node 2 - Connected to 1, 5, and 7
            LinkedList<Integer> node2 = new LinkedList<>();
            node2.add(5);
            node2.add(7);
            node2.add(1);

            list.nodes[1] = node2;

            // Node 3 - Connected to 4, 6, and 7
            LinkedList<Integer> node3 = new LinkedList<>();
            node3.add(4);
            node3.add(6);
            node3.add(7);

            list.nodes[2] = node3;

            // Node 4 - Connected to 1, 3, and 5
            LinkedList<Integer> node4 = new LinkedList<>();
            node4.add(1);
            node4.add(3);
            node4.add(5);
            list.nodes[3] = node4;

            // Node 5 - Connected to 2, 4, 6, and 7
            LinkedList<Integer> node5 = new LinkedList<>();
            node5.add(2);
            node5.add(4);
            node5.add(6);
            node5.add(7);
            list.nodes[4] = node5;

            // Node 6 - Connected to 3 and 5
            LinkedList<Integer> node6 = new LinkedList<>();
            node6.add(3);
            node6.add(5);
            list.nodes[5] = node6;

            // Node 7 - Connected to 2, 3, and 5
            LinkedList<Integer> node7 = new LinkedList<>();
            node7.add(2);
            node7.add(3);
            node7.add(5);

            list.nodes[6] = node7;

            for (int i = 0; i < num_nodes - 1; i++) {
                Collections.sort(list.nodes[i]);
            }

            return list;
        }

        // Ignore the unusual numbering, error with braces threw me off, I was wondering why it kept saying it was declared in the same area. Turns out it was due to braces.

        if (graphnum == 2) {
            // Problem #2
            ListGraph list1 = new ListGraph(num_nodes);

            LinkedList<Integer> node11 = new LinkedList<>();
            node11.add(4); node11.add(5);
            list1.nodes[0] = node11;

            LinkedList<Integer> node22 = new LinkedList<>();
            node22.add(4); node22.add(3);
            list1.nodes[1] = node22;

            LinkedList<Integer> node33 = new LinkedList<>();
            node33.add(2); node33.add(4);
            list1.nodes[2] = node33;

            LinkedList<Integer> node44 = new LinkedList<>();
            node44.add(1); node44.add(2); node44.add(3); node44.add(7);
            list1.nodes[3] = node44;

            LinkedList<Integer> node55 = new LinkedList<>();
            node55.add(6); node55.add(1);
            list1.nodes[4] = node55;

            LinkedList<Integer> node66 = new LinkedList<>();
            node66.add(5); node66.add(7);
            list1.nodes[5] = node66;

            LinkedList<Integer> node77 = new LinkedList<>();
            node77.add(4); node77.add(6);
            list1.nodes[6] = node77;

            for (int i = 0; i < num_nodes - 1; i++) {
                Collections.sort(list1.nodes[i]);
            }
            return list1;
        }

        if (graphnum == 3) {
            // Problem #3
            ListGraph list1 = new ListGraph(num_nodes);

            LinkedList<Integer> node11 = new LinkedList<>();
            node11.add(2); node11.add(4); node11.add(5);
            list1.nodes[0] = node11;

            LinkedList<Integer> node22 = new LinkedList<>();
            node22.add(4); node22.add(3); node22.add(7);
            list1.nodes[1] = node22;

            LinkedList<Integer> node33 = new LinkedList<>();
            node33.add(4);
            list1.nodes[2] = node33;

            LinkedList<Integer> node44 = new LinkedList<>();
            node44.add(6); node44.add(7);
            list1.nodes[3] = node44;

            LinkedList<Integer> node55 = new LinkedList<>();
            node55.add(4);
            list1.nodes[4] = node55;

            LinkedList<Integer> node66 = new LinkedList<>();
            node66.add(5);
            list1.nodes[5] = node66;

            LinkedList<Integer> node77 = new LinkedList<>();
            node77.add(6);
            list1.nodes[6] = node77;

            for (int i = 0; i < num_nodes - 1; i++) {
                Collections.sort(list1.nodes[i]);
            }
            return list1;
        }

        if (graphnum == 4) {
            // Problem #4
            ListGraph list1 = new ListGraph(num_nodes);

            LinkedList<Integer> node11 = new LinkedList<>();
            node11.add(2); node11.add(4); node11.add(5);
            list1.nodes[0] = node11;

            LinkedList<Integer> node22 = new LinkedList<>();
            node22.add(3);
            list1.nodes[1] = node22;

            LinkedList<Integer> node33 = new LinkedList<>();
            node33.add(7);
            list1.nodes[2] = node33;

            LinkedList<Integer> node44 = new LinkedList<>();
            node44.add(2); node44.add(3); node44.add(7); node44.add(6);
            list1.nodes[3] = node44;

            LinkedList<Integer> node55 = new LinkedList<>();
            node55.add(4);
            list1.nodes[4] = node55;

            LinkedList<Integer> node66 = new LinkedList<>();
            node66.add(7);
            list1.nodes[5] = node66;

            LinkedList<Integer> node77 = new LinkedList<>();
            list1.nodes[6] = node77;

            for (int i = 0; i < num_nodes - 1; i++) {
                Collections.sort(list1.nodes[i]);
            }
            return list1;
        }

        return new ListGraph(1);
    }


    static ArrayList<Integer> BreadthFirstSearchList(ListGraph graph, int currentNode)
    {
        // Sorts node order so if input is out of order, ex: Node1 -> 3, 2, 1 ... It will now be 1, 2, 3.
        //Collections.sort(graph.nodes[currentNode]);

        ArrayList<Integer> visitedNodes = new ArrayList<>();
        Queue<Integer> visitQueue = new LinkedList<Integer>();

        // Visit(v) / Mark(v) - Visiting and marking is the same thing here, if you were searching for a value you'd
        // check for that value when you visit it.
        visitedNodes.add(currentNode);

        // Enqueue(v)
        visitQueue.add(currentNode);

        // while the queue is not empty do
        while (!visitQueue.isEmpty())
        {
            // Dequeue(x)
            int current = visitQueue.remove();
            LinkedList<Integer> currentGraphEntry = graph.nodes[current - 1];
            // For every edge in G
            for (Integer node : currentGraphEntry) {
                if (!visitedNodes.contains(node)) {
                        // Visit
                        visitedNodes.add(node);
                        // Enqueue
                        visitQueue.add(node);
                }
            }
        }

        return visitedNodes;
    }

    static void DepthFirstSearchList(ListGraph graph, int currentNode, ArrayList<Integer> nodesVisited)
    {
        // Nodes are already sorted via for loop and sort function of Collections library.
        // Visit / Mark node
        nodesVisited.add(currentNode);

        // For each edge in the current node
        LinkedList<Integer> currentGraphEntry = graph.nodes[currentNode - 1];
        for (Integer node : currentGraphEntry) {
            if (!nodesVisited.contains(node)) {
                DepthFirstSearchList(graph, node, nodesVisited);
            }
        }
    }

    public static void main(String[] args) {
        // Problem #1
        ListGraph graph = GetListGraph(1);

        System.out.println("Problem 1:\n \nList: ");

        int startingNode = 1;
        ArrayList<Integer> breadthFirstResult = BreadthFirstSearchList(graph, startingNode);

        System.out.println("Breadth first traversal result: " + breadthFirstResult.toString());

        ArrayList<Integer> depthFirstResult = new ArrayList<>();
        DepthFirstSearchList(graph, startingNode, depthFirstResult);
        System.out.println("Depth first traversal result: " + depthFirstResult);

        System.out.println("\nMatrix: ");

        GraphAlgo prob1 = new GraphAlgo();
        System.out.print("Depth: " );
        prob1.DepthFirstTraversal(prob1.graph, 0);
        clearmarks();
        //clearpath();
        System.out.print("\nBreadth: ");
        prob1.MBreadthFirstTraversal(prob1.graph, 0);
        clearmarks();
        //clearpath();

        // Problem #2
        graph = GetListGraph(2);
        System.out.println("\n\nProblem 2:\n \nList: ");
        breadthFirstResult = BreadthFirstSearchList(graph, startingNode);

        System.out.println("Breadth first traversal result: " + breadthFirstResult.toString());

        depthFirstResult = new ArrayList<>();
        DepthFirstSearchList(graph, startingNode, depthFirstResult);
        System.out.println("Depth first traversal result: " + depthFirstResult);

        System.out.println("\nMatrix: ");

        System.out.print("Depth: " );
        prob1.DepthFirstTraversal(prob1.graph2, 0);
        clearmarks();
        //clearpath();
        System.out.print("\nBreadth: ");
        prob1.MBreadthFirstTraversal(prob1.graph2, 0);
        clearmarks();
       // clearpath();

        // Problem #3
        graph = GetListGraph(3);
        System.out.println("\n\nProblem 3:\n \nList: ");
        breadthFirstResult = BreadthFirstSearchList(graph, startingNode);

        System.out.println("Breadth first traversal result: " + breadthFirstResult.toString());

        depthFirstResult = new ArrayList<>();
        DepthFirstSearchList(graph, startingNode, depthFirstResult);
        System.out.println("Depth first traversal result: " + depthFirstResult);

        System.out.println("\nMatrix: ");

        System.out.print("Depth: " );
        prob1.DepthFirstTraversal(prob1.graph3, 0);
        clearmarks();
       // clearpath();
        System.out.print("\nBreadth: ");
        prob1.MBreadthFirstTraversal(prob1.graph3, 0);
        clearmarks();
       // clearpath();

        // Problem #4
        graph = GetListGraph(4);
        System.out.println("\n\nProblem 4:\n \nList: ");
        breadthFirstResult = BreadthFirstSearchList(graph, startingNode);

        System.out.println("Breadth first traversal result: " + breadthFirstResult.toString());

        depthFirstResult = new ArrayList<>();
        DepthFirstSearchList(graph, startingNode, depthFirstResult);
        System.out.println("Depth first traversal result: " + depthFirstResult);

        System.out.println("\nMatrix: ");

        System.out.print("Depth: " );
        prob1.DepthFirstTraversal(prob1.graph4, 0);
        clearmarks();
       // clearpath();
        System.out.print("\nBreadth: ");
        prob1.MBreadthFirstTraversal(prob1.graph4, 0);
        clearmarks();
       // clearpath();
    }

}