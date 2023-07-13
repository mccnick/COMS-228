# COMS-228


<h2><p align="center">Get ready to scroll.</p> </h2>

<p align="center">Data Structures and Algorithms course, using Java. I was required to implement three large projects.</p>
<p align="center">-Nick</p>

___________

| **Assignment** | **Java Code** | **Quick Description** |**Score** 
| :-------------: | :-------------: | :-------------: | :-------------: |
| <a href="https://github.com/mccnick/COMS-228/blob/main/HW1.pdf">HW1 PDF</a> | <a href="https://github.com/mccnick/COMS-228/tree/main/src/edu/iastate/cs228/hw1">*HW1 code*</a> | Implement & compare multiple sorting algorithms, 2D points | 90/100 |
| <a href="https://github.com/mccnick/COMS-228/blob/main/HW2.pdf">HW2 PDF</a> | <a href="https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw2/StoutList.java">*HW2 code*</a> | Doubly-Linked List, Nodes, Iterators (800+ lines code) | 100/100 |
| <a href="https://github.com/mccnick/COMS-228/blob/main/HW3.pdf">HW3 PDF</a> | <a href="https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw3/MsgTree.java">*HW3 code*</a> | Binary-tree, decoding compressed message, statistics | 111/100 |
|  | | <i>Total Homework Score:</i> | 301/300 |
|  | | <b><i>Final Course Grade:</i></b> | <b>A</b> |

<img width="717" alt="image" src="https://github.com/mccnick/COMS-228/assets/91184284/2a1fa919-efd8-4371-b9b9-99e5d47251d2">

___________

<h2><p align="center"> Visuals & Descriptions </p> </h2>

<h4>Homework 1. </h4>

<p align="left">
This assignment involves creating a sorting program that reads 2D integer points, and calculates the median coordinate <a href="https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw1/Point.java">point</a> by applying different sorting algorithms: <a href="https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw1/SelectionSorter.java">selection sort</a>, <a href="https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw1/InsertionSorter.java">insertion sort</a>, <a href="https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw1/MergeSorter.java">merge sort</a>, and <a href="https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw1/QuickSorter.java">quick sort</a>. Points are read either randomly or from a file, and the program implements specific Point and Sorter classes with particular methods and constructors for organizing and storing the points. The program must also compare the execution times, in nanoseconds, of the different sorting algorithms to provide comparative performance data. A final element of the project is the generation of random points within a specific range for testing purposes.
</p>


![ezgif com-video-to-gif-4](https://github.com/mccnick/COMS-228/assets/91184284/3ef76668-03c0-4715-ab7e-664b561c23fc)


https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw1/MergeSorter.java



<a href=""></a>
<a href=""></a>
<a href=""></a>
<a href=""></a>
<a href=""></a>

___________

<h4>Homework 2. </h4>
<p align="left">
This assignment involves creating a special doubly-linked list called <a href="https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw2/StoutList.java">StoutList</a>, which extends the AbstractSequentialList, where each node can store up to a certain number of data elements (M). The task includes developing two iterator inner classes, StoutIterator (that implements Iterator<E>) and StoutListIterator (implementing ListIterator<E>), and also overriding specific methods from AbstractList. The implementation needs to follow specific rules for adding and removing elements, considering each logical index within the list as a combination of node and offset within the node's array. Lastly, the assignment emphasizes on effective debugging and understanding of list and listIterator interfaces.

The image below is of <a href="https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw2/StoutListTest.java">StoutListTest</a> (the provided JUnit tests) to confirm all tests passed as necessary.
</p>

![image](https://github.com/mccnick/COMS-228/assets/91184284/653bab2b-a4c1-4ae0-a01e-e314dc6728b4)


___________


<h4>Homework 3. </h4>
<p align="left">
This assignment involves implementing a program that can reconstruct a message archived with a binary-tree-based encoding scheme. The program reads the encoding scheme from a file, builds the corresponding binary tree, and then decodes the compressed message bit by bit. The program should print out the characters with their binary codes and the decoded message to the console. Students have the opportunity to earn extra credit by printing message-specific statistics or creating an iterative solution for building the tree. Below, the <a href="https://github.com/mccnick/COMS-228/blob/main/src/edu/iastate/cs228/hw3/MsgTree.java">MsgTree</a> class is decoding a compressed monalisa.arch file, and by decoding it correctly, displays a Mona Lisa figure and decoding statistics.
</p>


![monalisaarch2 gif](https://github.com/mccnick/COMS-228/assets/91184284/55e963c7-c444-4d03-bd7f-5c52fb59885c)

