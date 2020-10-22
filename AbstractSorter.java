/**
 * DO NOT EDIT
 * Implememnt a class *Sort* that implements SortInterface
 */
public abstract class AbstractSorter {
    public abstract void insertionSort(MyLinkedList list);
    /* TODO insertionSort
    ***For each node in the unsorted portion list, place it in the correct location of the sorted portion of the list,
    You can do this by breaking and relinking the actual nodes or 
    swapping just the data into the right place after shifting the larger sorted data forward one space
    */

    public abstract void selectionSort(MyLinkedList list);
    /* TODO selectionSort
    ***repeatedly find the node with the smallest data from the unsorted portion 
    and add it to a sorted portion at beginning (or end)
    
    for example:
    1. find node with smallest data in entire list, swap data with head
    2. find node with smallest data in list excluding head, swap data with head.next
    3. find node with smallest data in list excluding head and head.next, swap data with head.next.next
    4. repeat until there is only one node left
    */

    public abstract void bubbleSort(MyLinkedList l);
    /* TODO bubbleSort
    ***compare each adjacent pair, swapping data if necessary. Repeat until no swaps are made in a full pass
    
    for example:
    1. compare head and head.next, swap data if out of order
    2. compare head.next and head.next.next, swap data if out of order
    3. continue to compare each adjacent pair in the whole list, swapping if needed
    **if no swaps were performed, the list is already ordered**
    
    4. repeat steps 1-3
    **if no swaps were performed, the list is now ordered
    
    5. continue repeating steps 1-3 until one whole pass with no swaps 
    */

    public void mergeSortHandler(MyLinkedList list) {
        /*  
        *** DO NOT OVERRIDE THIS METHOD, just the mergeSort, findMiddle, and sortedMerge methods below
        *** This method just exists so the Tester program can be a bit cleaner
        */
        Node newHead = mergeSort(list.head);
        if (newHead != null) {
            list.head = newHead;

        }
    };

    public abstract Node mergeSort(Node head);

    /* TODO mergeSort
    *** mergeSort works Recursively, so this method should call itself
    
    *** if head or head.next is null, just return head
        otherwise:
            1. Split the list into 2 separate lists:
                -get the Node in the middle of the list (middle=findMiddle(head)),
                    and the one right after it (nextOfMiddle=middle.next)
                -detach the link from middle to middle.next (middle.next=null)
            2. Sort the left list (mergeSort(head)) **returns a Node, store this as "left"
            3. Sort the right list (mergeSort(nextOfMiddle)) **returns a Node, store this as "right"
            4. Merge the two newly sorted halves (sortedMerge(left,right)) **returns a Node which is the new head
            5. Return the new head
    
    */
    public abstract Node findMiddle(Node head);
    /* TODO findMiddle
    *** A small utility to find and return the Node in the middle of a list (rounding down)
    *** for example:
    -a list with 0 Nodes (head==null) should return null
    -a list with 1 Node should return head
    -a list with 2 Nodes should return head
    -a list with 3 Nodes should return the 2nd Node
    -a list with 4 Nodes should return the 2nd Node
    -...
    -a list with 19 Nodes should return the 10th Node
    -a list with 20 Nodes should return the 10th Node
    
    
    *** There are a few ways to do this, implement it however you like
    
    */

    public abstract Node sortedMerge(Node a, Node b);
    /* TODO sortedMerge
    *** Merges two sorted lists recursively
    1. Make a node to store the result (Node result = null)
    2. if a or b are null, just return the other one (this means one list has been fully merged already)
    3. set result to whichever node has the smaller data, then recur:
    eg. if a.data < b.data:
    result = a
    result.next = sortedMerge(a.next, b)
    and vise-versa if b.data is smaller
    4. return result
    
    */

    public void quickSortHandler(MyLinkedList list) {
        /*  
        *** DO NOT OVERRIDE THIS METHOD, just the partition and sort methods below
        *** This method just exists so the Tester program can be a bit cleaner
        */

        Node last = list.head;
        while (last.next != null) {
            last = last.next;
        }

        quickSort(list.head, last);
    };

    public abstract Node quickSortPartition(Node start, Node end);
    /* TODO quickSortPartition
    *** This method should select a node to be the "pivot" (typically the "end" node) and place it in it's correct position
    *** Here is one common algorithm for doing this:
    
    0. Handle base cases: if start is the same as end, or either start or end is null, just return start  
    1. select a pivot (eg the last node's data)
    2. rearrange the data in the rest of the nodes:
        -keep track of the node at the boundary between smaller and bigger nodes, initally at start, this will become pivot_prev
        -also keep track of the node after the last swapped position (current)
        -for each node other than pivot (end), if its data < pivot:
            -swap its data with current's data
            -set pivot_prev to current
            -set current to current.next
    Now the list is split into 3 portions: smaller than pivot (but still unordered), 
    larger than pivot (unordered), and finally the pivot itself, with "current" pointing to the proper position for pivot
        -finally, swap pivot's data with current's data
            
        -return pivot_prev so mergeSort knows where the split is
        
        */

    public abstract void quickSort(Node start, Node end);
    /* TODO quickSort
    *** quickSort works Recursively, so this method should call itself
    
    *** if start is the same as end, that means there is only one Node, so just return
        otherwise:
            -partition the sub-list from start to end, store the returned Node (pivot_previous)
            -call quickSort from start to pivot_previous to sort the left partition
            -for the right partition, quickSort from the node after the pivot to end:
                -if pivot_previous is the same as start, then pivot_previous is actually the pivot, so the node after pivot
                    is pivot_prev.next.next
                -otherwise, as long as pivot_previous isn't at the end or one back from the end
                    (AKA pivot_previous.next isn't null, and pivot_previous.next != end)    
                    then sort from two after pivot_previous (.next.next) to end
    */
}