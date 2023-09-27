package Week14;

import java.util.ArrayList;
import java.util.LinkedList;

public class GraphAlgos {


    static int[][] graphA = {
            {0,2,0,0,0,3,0,0},
            {2,0,5,0,4,0,3,0},
            {0,5,0,0,2,0,0,4},
            {0,0,0,0,2,4,2,0},
            {0,4,2,2,0,0,0,3},
            {3,0,0,4,0,0,1,0},
            {0,3,0,2,0,1,0,1},
            {0,0,4,0,3,0,0,1}
    };

    static int[][] graphB = {
            {0, 2, 4, 6, 0, 0, 0},
            {2, 0, 2, 0, 6, 0, 0},
            {4, 2, 0, 1, 3, 0, 0},
            {6, 0, 1, 0, 2, 3, 0},
            {0, 6, 3, 2, 0, 0, 5},
            {0, 0, 0, 3, 0, 0, 4},
            {0, 0, 0, 0, 5, 4, 0}
    };

    static int[][] graphC = {
            {0, 1, 0, 5, 0, 2, 0, 0},
            {1, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 4, 0, 0, 1},
            {5, 0, 0, 0, 3, 0, 2, 0},
            {0, 2, 4, 3, 0, 0, 2, 0},
            {2, 0, 0, 0, 0, 0, 4, 0},
            {0, 0, 0, 2, 2, 4, 0, 5},
            {0, 0, 1, 0, 0, 0, 5, 0},
    };

    static int[][] graphD = {
            {0, 1, 0, 2, 0, 0, 0, 0},
            {1, 0, 2, 0, 3, 0, 0, 0},
            {0, 2, 0, 4, 0, 0, 0, 0},
            {2, 0, 4, 0, 2, 2, 0, 0},
            {0, 3, 0, 2, 0, 5, 0, 3},
            {0, 0, 0, 2, 5, 0, 4, 5},
            {0, 0, 0, 0, 0, 4, 0, 1},
            {0, 0, 0, 0, 3, 5, 1, 0}
    };

    static ArrayList<Node> fringe = new ArrayList<Node>();
    static ArrayList<Node> tree = new ArrayList<Node>();
    static Node start;
    static ArrayList<LinkedList<Node>> list = new ArrayList<>();

    public static int[] marked;

    public static void clearMarks() {
        for(int i = 0; i < marked.length; i++) {
            marked[i] = 0;
        }
    }

    public static void clearFringeTree() { // Precautionary wipe by assigning new references
        fringe = new ArrayList<Node>(); // The fringe should be clear, but just to be safe.
        tree = new ArrayList<Node>();
    }
    public static class Node {
        int val; // Weight
        char nodeName;
        Node next;

        public Node(int val, int i) {
            this.val = val;
            this.nodeName = (char) i;
        }

        public String toString() {
            return val + " " + nodeName;
        }
    }
        public String toString() {
            String s = "";
            Node ref = start;
            while (ref != null) {
                s += ref.toString() + " ";
                ref = ref.next;
            }
            return s;
        }

    public static void Short(int[][] graph, int a, LinkedList<Node> n) {
        addFringeM(graph, 0);

        int current = 0;
        while (current != graph.length && a != n.getLast().nodeName-'A') {
            Node smallest = getSmallest();
            tree.add(smallest);
            n.add(smallest);
            fringe.remove(smallest);
            addFringeM(graph, n.getLast().nodeName - 'A');
        }
    }

    public static void addFringeM(int[][] graph, int a) {
        int[] nextNode = graph[a];

        for (int i = 0; i < nextNode.length; i++) {
            if(i == a || marked[i] != 0) {
                continue;
            }
            if (nextNode[i] != 0) {
                Node edge = new Node(nextNode[i], 'A' + i);
                fringe.add(edge);
                compareFringeTree();
            }
        }
    }

    public static Node getSmallest() {
        Node smallest = fringe.get(0);
        for(int i = 0; i < fringe.size(); i++) { // For all nodes in the fringe
            if (smallest.val > fringe.get(i).val) { // If initial smallest is not smallest
                smallest = fringe.get(i); // Replace with smaller node if initial is not smallest
            }
        }
        return smallest;
    }

    public static void compareFringeTree() { // This function essentially exists to prevent stuff thats on the tree from being put onto the fringe (redundancy prevention)
        for (int i = 0; i < tree.size(); i++) { // For every Node in tree
            for (int j = 0; j < fringe.size(); j++) { // For every node in fringe
                if (tree.get(i).nodeName == fringe.get(j).nodeName) { // If tree Node exists in fringe, remove matching Node from fringe
                    fringe.remove(j);
                    j = 0; // reset search for reiteration
                }
            }
        }
    }

    public static void addFringe(int[][] graph, int a) {
        int[] nextNode = graph[a]; // You get the row of index a and store that into an int array

        for (int i = 0; i < nextNode.length; i++) { // For the length of the array
            if (i == a) { // if i equals a, skip over to the next iteration. Basically ensures that you dont connect same node to same node.
                continue;
            }

            if (nextNode[i] != 0) {
                Node edge = new Node(nextNode[i], 'A' + i); // Edge weight + node it connects to 'A' + 1
                fringe.add(edge);
                compareFringeTree();
            }
        }
    }

    public static void algoDkistra(int[][] graph, int a) {
        addFringe(graph, a);

        while(!fringe.isEmpty()) {
            Node smallest = getSmallest();
            tree.add(smallest);
            fringe.remove(smallest);
            algoDkistra(graph, (int) tree.get(tree.size() - 1).nodeName - 'A'); // recursion, get the last Node of tree repeat algorithm. This works as it will move on and keep going till everything is completed (Fringe empty).
        }
    }

    public static void main(String[] args) {

        GraphAlgos instance = new GraphAlgos();
        instance.start = new Node(0, 'A');

        //making this here so that my addToFringe works, does nothing as of now
        marked = new int[graphA[0].length];
        int a = 0;
        int b = 0;

            instance.start = new Node(0,'A');
            tree.add(instance.start);
            System.out.println("Graph" + (char) ('A' + a));
            algoDkistra(graphA,0);
            System.out.println(tree.toString());
            tree.clear();
            //clearFringeTree(); This works, clearing Fringe isn't necessary though.

        System.out.println();
        a++;

        instance.start = new Node(0,'A');
        tree.add(instance.start);
        System.out.println("Graph" + (char) ('A' + a));
        algoDkistra(graphB,0);
        System.out.println(tree.toString());
        tree.clear();

        System.out.println();
        a++;

        instance.start = new Node(0,'A');
        tree.add(instance.start);
        System.out.println("Graph" + (char) ('A' + a));
        algoDkistra(graphC,0);
        System.out.println(tree.toString());
        tree.clear();

        System.out.println();
        a++;

        instance.start = new Node(0,'A');
        tree.add(instance.start);
        System.out.println("Graph" + (char) ('A' + a));
        algoDkistra(graphD,0);
        System.out.println(tree.toString());
        tree.clear();

        System.out.println();
        a++;


            marked = new int[graphA.length];
            for(int j = 0; j < graphA.length; j++) {
                instance.start = new Node(0,'A');
                LinkedList<Node> l = new LinkedList<Node>();
                l.add(instance.start);
                list.add(l);
                marked[0]++;
                Short(graphA,j,list.get(j));

                clearMarks();
                fringe.clear();
                tree.clear();
            }
            System.out.println("Graph" + (char) ('A' + b));
            System.out.println(list.toString());
            list.clear();
            b++;

        marked = new int[graphB.length];
        for(int j = 0; j < graphB.length; j++) {
            instance.start = new Node(0,'A');
            LinkedList<Node> l = new LinkedList<Node>();
            l.add(instance.start);
            list.add(l);
            marked[0]++;
            Short(graphB,j,list.get(j));

            clearMarks();
            fringe.clear();
            tree.clear();
        }
        System.out.println("Graph" + (char) ('A' + b));
        System.out.println(list.toString());
        list.clear();
        b++;

        marked = new int[graphC.length];
        for(int j = 0; j < graphC.length; j++) {
            instance.start = new Node(0,'A');
            LinkedList<Node> l = new LinkedList<Node>();
            l.add(instance.start);
            list.add(l);
            marked[0]++;
            Short(graphC,j,list.get(j));

            clearMarks();
            fringe.clear();
            tree.clear();
        }
        System.out.println("Graph" + (char) ('A' + b));
        System.out.println(list.toString());
        list.clear();
        b++;

        marked = new int[graphD.length];
        for(int j = 0; j < graphD.length; j++) {
            instance.start = new Node(0,'A');
            LinkedList<Node> l = new LinkedList<Node>();
            l.add(instance.start);
            list.add(l);
            marked[0]++;
            Short(graphD,j,list.get(j));

            clearMarks();
            fringe.clear();
            tree.clear();
        }
        System.out.println("Graph" + (char) ('A' + b));
        System.out.println(list.toString());
        list.clear();
        b++;
        }
    }
