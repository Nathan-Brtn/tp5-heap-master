package tas;


import java.util.*;


public class Test {
    public static void printTest(String msg, boolean b){
        System.out.println("test : "+(b?" ok":" KO") + " (" + msg + ")");
    }

    public static <T extends Comparable<T>>  ArrayList<T> minSort(ArrayList<T> t){
        for(int i = 0;i < t.size();i++) {
            int min = i;
            for (int j = i + 1; j < t.size(); j++) {
                if (t.get(j).compareTo(t.get(min)) <= 0) {
                    min = j;
                }
            }
            Collections.swap(t,min,i);
        }
        return t;

    }

    public static <T extends Comparable<T>>  ArrayList<T> heapSort(ArrayList<T> t){
        //implémente le tri par tas par ordre croissant
        Heap heap = new Heap(false, t);

        return heap.toSortedArray();
    }
    public static void compareTri(int n){
        Random r = new Random();
        ArrayList<Integer> toSort = new ArrayList<>();
        for(int i=0;i<n;i++){
            toSort.add(r.nextInt(10*n));
        }
        ArrayList<Integer> copy = new ArrayList<>(toSort);



        long start = System.currentTimeMillis();
        toSort=heapSort(toSort);
        long stop = System.currentTimeMillis();
        long delta1 = stop-start;


        long start3 = System.currentTimeMillis();
        minSort(copy);
        long stop3 = System.currentTimeMillis();
        long delta3 = stop3-start3;



        System.out.println("heap sort :       time :" + delta1);
        System.out.println("min sort : time :" + delta3);
    }



    public static void testHeapifyUp() {
        Heap<Integer> tas = new Heap<>(true);
        ArrayList<Integer> t = new ArrayList<>(Arrays.asList(3,1,2,4));
        tas.setT(t);
        tas.heapifyUp(3);
        ArrayList<Integer> t2 = new ArrayList<>(Arrays.asList(4,3,2,1));
        printTest("HeapifyUp1", tas.getT().equals(t2)); //on aurait même pu écrire t.equals(t2)

        t = new ArrayList<>(Arrays.asList(3,1,2,2));
        tas.setT(t);
        tas.heapifyUp(3);
        t2 = new ArrayList<>(Arrays.asList(3,2,2,1));
        printTest("HeapifyUp2", tas.getT().equals(t2));

        t = new ArrayList<>(Arrays.asList(6,4,3,1,2,5));
        tas.setT(t);
        tas.heapifyUp(5);
        t2 = new ArrayList<>(Arrays.asList(6,4,5,1,2,3));
        printTest("HeapifyUp3", tas.getT().equals(t2));

        Heap<Integer> tasMin = new Heap<Integer>(false);
        ArrayList<Integer> tmin = new ArrayList<>(Arrays.asList(3,6,9,10,5));
        tasMin.setT(tmin);
        tasMin.heapifyUp(4);
        ArrayList<Integer> t2min = new ArrayList<>(Arrays.asList(3,5,9,10,6));
        printTest("HeapifyUp4", tasMin.getT().equals(t2min));

        tmin = new ArrayList<>(Arrays.asList(3,6,9,10,2));
        tasMin.setT(tmin);
        tasMin.heapifyUp(4);
        t2min = new ArrayList<>(Arrays.asList(2,3,9,10,6));
        printTest("HeapifyUp5", tasMin.getT().equals(t2min));

    }

    public static void testAdd() {
        Heap<Integer> tas = new Heap<>(true);
        tas.add(4);
        tas.add(8);
        tas.add(2);
        tas.add(30);
        tas.add(19);
        tas.add(5);
        tas.add(9);
        tas.add(40);
        ArrayList<Integer> t = new ArrayList<>(Arrays.asList(40, 30, 9, 19, 8, 2, 5, 4));
        printTest("testAdd1", tas.getT().equals(t));
    }

    public static void testRemove() {
        Heap<Integer> tas = new Heap<>(true);
        ArrayList<Integer> t = new ArrayList<>(Arrays.asList(40, 30, 9, 19, 8, 2, 5, 4));
        tas.setT(t);
        tas.remove(2);
        ArrayList<Integer> t2 = new ArrayList<>(Arrays.asList(40, 30, 5, 19, 8, 2, 4));
        printTest("testRemove", tas.getT().equals(t2));
    }
    public static void testRemoveTop() {
        Heap<Integer> tas = new Heap<>(true);
        ArrayList<Integer> t = new ArrayList<>(Arrays.asList(40, 30, 5, 19, 8, 2, 4));
        tas.setT(t);
        tas.removeTop();
        ArrayList<Integer> t2 = new ArrayList<>(Arrays.asList(30, 19, 5, 4, 8, 2));
        printTest("testRemoveTop", tas.getT().equals(t2));

    }
    public static void testPQeueueAddgGetRemoveTop(){
        PriorityQ<String> p = new PriorityQ<String>(true);
        p.add("or",20);
        p.add("argent",15);
        p.add("argent",10);
        p.add("bronze",5);

        printTest("testPqueueAddgGetRemoveTop1",p.removeTop().equals("or"));
        printTest("testPqueueAddgGetRemoveTop2",p.getTop().equals("argent"));
        printTest("testPqueueAddgGetRemoveTop3",p.removeTop().equals("argent"));
        printTest("testPqueueAddgGetRemoveTop4",p.removeTop().equals("argent"));
        printTest("testPqueueAddgGetRemoveTop5",p.removeTop().equals("bronze"));

    }


    public static void testDijWithoutPQ(){
        Graph g = new Graph(5);
        g.addEdge(0,1,1);
        g.addEdge(0,2,10);
        g.addEdge(1,2,2);
        g.addEdge(1,3,30);
        g.addEdge(2,3,3);
        g.addEdge(0,4,2);
        g.addEdge(4,3,2);

        ArrayList<Integer> path = new ArrayList<>(Arrays.asList(0,4,3));
        printTest("testDijWithoutHeap1",g.dijkstraWithoutPriorityQ(0,3).equals(path));

        g = new Graph(5);
        g.addEdge(0,1,1);
        g.addEdge(0,2,10);
        g.addEdge(1,2,2);
        g.addEdge(1,3,30);
        g.addEdge(2,3,3);
        g.addEdge(0,4,2);
        g.addEdge(4,3,20);

        path = new ArrayList<>(Arrays.asList(0,1,2,3));
        printTest("testDijWithoutHeap2",g.dijkstraWithoutPriorityQ(0,3).equals(path));

    }

    public static void testDijWithPQ(){
        Graph g = new Graph(5);
        g.addEdge(0,1,1);
        g.addEdge(0,2,10);
        g.addEdge(1,2,2);
        g.addEdge(1,3,30);
        g.addEdge(2,3,3);
        g.addEdge(0,4,2);
        g.addEdge(4,3,2);

        ArrayList<Integer> path = new ArrayList<>(Arrays.asList(0,4,3));
        printTest("testDijWithHeap1",g.dijkstraWithPriorityQ(0,3).equals(path));

        g = new Graph(5);
        g.addEdge(0,1,1);
        g.addEdge(0,2,10);
        g.addEdge(1,2,2);
        g.addEdge(1,3,30);
        g.addEdge(2,3,3);
        g.addEdge(0,4,2);
        g.addEdge(4,3,20);

        path = new ArrayList<>(Arrays.asList(0,1,2,3));
        printTest("testDijWithHeap2",g.dijkstraWithPriorityQ(0,3).equals(path));

    }
    public static void testToSortedArray(){
        ArrayList<Integer> tf = new ArrayList<>(Arrays.asList(4,5,2,30,8,6,19,9,40));
        Heap<Integer> tas = new Heap<>(false, tf);
        ArrayList<Integer> t = new ArrayList<>(Arrays.asList(2,4,5,6,8,9,19,30,40));
        printTest("testToSortedArray", tas.toSortedArray().equals(t));
    }


    public static void main(String[] args){

       testHeapifyUp();
       testAdd();
       testToSortedArray();
       testRemove();
       testRemoveTop();
       //testDijWithoutPQ();
       //testDijWithPQ();
       compareTri(10);


    }

}
