# AlgorithmTester
  
You will need to implement a class **"Sort"** that extends **"AbstractSorter"**, this is where you will implement the 5 sorts.

You also need to add your own **"MyLinkedList.java"** and **"Node.java"** files from the previous assignment to this folder.

An abstract version of **MyLinkedList** is provided. Yours should extend this to ensure
it at least has the required methods. The additional methods from the Linked List Assigment
are not required for this tester to work, but you might find them handy when implementing
the various sort algorithms.

Your **Node** class needs **"data"** and **"next"** to be **public**, and make sure the names match exactly,
and a constructor that takes in an int and sets its "data" to that int.

## Iterative Algorithms
### Insertion Sort
For each node in the unsorted portion list, place it in the correct location of the sorted portion of the list,
You can do this by 

* breaking and relinking the actual nodes or 
* swapping just the data into the right place after shifting the larger sorted data forward one space

### Selection Sort
repeatedly find the node with the smallest data from the unsorted portion and add it to a sorted portion at beginning (or end)
    
for example:

 1. find node with smallest data in entire list, swap data with head
 2. find node with smallest data in list excluding head, swap data with head.next
 3. find node with smallest data in list excluding head and head.next, swap data with head.next.next
 4. repeat until there is only one node left

### Bubble Sort
Compare each adjacent pair, swapping data if necessary. Repeat until no swaps are made in a full pass
    
for example:
1. compare head and head.next, swap data if out of order
2. compare head.next and head.next.next, swap data if out of order
3. continue to compare each adjacent pair in the whole list, swapping if needed
4. 
**if no swaps were performed, the list is already ordered**   

4. repeat steps 1-3
   
**if no swaps were performed, the list is now ordered** 

5. continue repeating steps 1-3 until one whole pass with no swaps 

## Recursive Algorithms
### Merge Sort
    
1. Split the list into 2 separate lists:
2. Sort the left list
3. Sort the right list
4. Merge the two newly sorted halves
5. Return the new head

### Quick Sort

1. partition the list from start to end, store the returned Node (pivot_previous)
2. Sort the left partition
3. Sort the right partition
            