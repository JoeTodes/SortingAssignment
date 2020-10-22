import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * AlgorithmTester
 * 
 * You will need to implement a class "Sort" that extends "AbstractSorter"
 * 
 * You also need to add your own "MyLinkedList.java" and "Node.java" files to this folder
 * 
 * An abstract version of MyLinkedList is provided. Yours should extend this to ensure
 * it at least has the required methods. The additional methods from the Linked List Assigment
 * are not required for this tester to work, but you might find them handy when implementing
 * the various sort algorithms.
 * 
 * Your Node class needs "data" and "next" to be public, and make sure the names match exactly,
 * and a constructor that takes in an int and sets its "data" to that int 
 */
public class AlgorithmTester {
    // 
    static Sort sorter = new Sort();

    static MyLinkedList masterList;

    static Random randomGen = new Random();

    static BufferedWriter writer;

    public static void main(String[] args) throws IOException {

        runSimpleTests();
        //runStressTests();

    }

    public static void runSimpleTests() {
        masterList = makeRandomList(10);
        masterList.print();
        Node last = masterList.head;
        while (last.next != null) {
            last = last.next;
        }

        System.out.println("Bubble Sort:");
        runSort(l -> sorter.bubbleSort(l));
        System.out.println("Selection Sort:");
        runSort(l -> sorter.selectionSort(l));
        System.out.println("Insertion Sort:");
        runSort(l -> sorter.insertionSort(l));
        System.out.println("Quick Sort:");
        runSort(l -> sorter.quickSortHandler(l));
        System.out.println("Merge Sort:");
        runSort(l -> sorter.mergeSortHandler(l));
    }

    public static void runStressTests() throws IOException {

        File log = new File("sortlog.txt");
        log.createNewFile();
        writer = new BufferedWriter(new FileWriter("sortlog.txt", true));

        writer.append("------------------\n");
        writer.append(new Date().toString());
        writer.newLine();
        writer.newLine();

        writer.append("5000 Nodes:\n");
        masterList = makeRandomList(5000);
        performAllTimedSorts();

        writer.append("~~~~~~~~~~~~~~\n");
        writer.append("10000 Nodes:\n");
        masterList = makeRandomList(10000);
        performAllTimedSorts();

        writer.append("~~~~~~~~~~~~~~\n");
        writer.append("50000 Nodes:\n");
        masterList = makeRandomList(50000);
        performAllTimedSorts();

        writer.append("~~~~~~~~~~~~~~\n");
        writer.append("75000 Nodes:\n");
        masterList = makeRandomList(75000);
        performAllTimedSorts();

        writer.newLine();
        writer.close();
    }

    public static MyLinkedList makeRandomList(int size) {
        MyLinkedList l = new MyLinkedList();
        for (int i = 0; i < size; i++) {
            l.push(randomGen.nextInt(size));
        }
        return l;
    }

    public static MyLinkedList deepClone(MyLinkedList l) {
        MyLinkedList clone = new MyLinkedList();
        clone.head = new Node(l.head.data);
        Node tail = clone.head;
        Node current = l.head.next;
        while (current != null) {
            tail.next = new Node(current.data);
            tail = tail.next;
            current = current.next;
        }
        return clone;
    }

    public static boolean isSorted(MyLinkedList l) {
        Node a = l.head;
        Node b = a.next;
        while (b != null) {
            if (a.data > b.data) {
                return false;
            }
            a = b;
            b = b.next;
        }
        return true;
    }

    public static void runSort(Func sort) {
        MyLinkedList clone = deepClone(masterList);

        sort.apply(clone);
        boolean sorted = isSorted(clone);

        clone.print();
        System.out.printf("%s\n", sorted ? "Success" : "Failed");
    }

    public static void runTimedSort(Func sort) throws IOException {
        long startTime, endTime;
        double duration;
        MyLinkedList clone = deepClone(masterList);

        startTime = System.currentTimeMillis();
        sort.apply(clone);

        endTime = System.currentTimeMillis();
        duration = (double) (endTime - startTime);

        boolean sorted = isSorted(clone);
        writer.append(String.format("%s in %.0f milliseconds\n", sorted ? "Success" : "Failed", duration));
        writer.newLine();

    }

    @FunctionalInterface
    public interface Func {
        void apply(MyLinkedList list);
    }

    public static void performAllTimedSorts() throws IOException {

        writer.write("Bubble Sort:\n");
        runTimedSort(l -> sorter.bubbleSort(l));
        writer.write("Selection Sort:\n");
        runTimedSort(l -> sorter.selectionSort(l));
        writer.write("Insertion Sort:\n");
        runTimedSort(l -> sorter.insertionSort(l));
        writer.write("Quick Sort:\n");
        runTimedSort(l -> sorter.quickSortHandler(l));
        writer.write("Merge Sort:\n");
        runTimedSort(l -> sorter.mergeSortHandler(l));
    }
}