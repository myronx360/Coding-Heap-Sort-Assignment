/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingheapsort;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Myron Williams
 */
public class Heap
{
    ArrayList<Integer> elements;
    ArrayList<Integer> sortedElements;

    public Heap(String filename)
    {
        this.elements = new ArrayList<>();
        try{
           // Open the file and load the numbers into elements[]
        FileReader file = new FileReader(filename);  
        BufferedReader bufferReader = new BufferedReader(file);
        String line;
        // add null for heapying
        elements.add(null);
       
        
        // Read each line of the file
         while ((line = bufferReader.readLine()) != null)   {
            // Add numbers to elements array
            elements.add(Integer.parseInt(line));
          }
          //Close the buffer reader
          bufferReader.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
      
        //
    }

    public void outputResult(String filename)
    {
		// Create a file and write the content of elements[] in it
 
        PrintWriter writer;
        try {
            writer = new PrintWriter(filename, "UTF-8");       
            writer.println(elements.toString());
            writer.close();
 } catch (FileNotFoundException ex) {
            Logger.getLogger(Heap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Heap.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public void siftUp(int indexOfNode)
    {
        // swap with parent if elements[indexOfNode] > parentValue
        // if you swap, siftUp(index of parent)
        //
          
        int parentIndex = indexOfNode/2;
        int parentValue = elements.get(parentIndex);
        int nodeValue = elements.get(indexOfNode);
        
        if (nodeValue > parentValue){
            elements.set(indexOfNode, parentValue);
            elements.set(parentIndex, nodeValue);
        }
    }

    public int removeMaxElement()
    {
        int root = elements.get(1);
        int lastElementIndex = elements.size() - 1;
        
        // replace root with last element
        elements.set(1, elements.remove(lastElementIndex));
     
        // heapify(1)
        heapify(1);
        
        // return original root
        return root;
    }

    public void addElement(int value)
    {
        
		
        
        // insert value
        elements.add(value);
        
        int nodeIndex = elements.size() - 1;
        int parentIndex = nodeIndex/2;
        int parentValue = elements.get(parentIndex);
        
        // siftUp value
        while (value > parentValue){
            siftUp(nodeIndex);
            nodeIndex = parentIndex;
            parentIndex = nodeIndex/2;
            if(parentIndex == 0) // if value is on top of heap stop siftUp
                break;
            parentValue = elements.get(parentIndex);
            
            
        }
    }

    public void heapSort()
    {
        // loop the following until elements[0] = 0
            // put the root in a new array
            // move the very last node (get from elements[0]) to the top(new root)
            // decrement the size of the array (decrement elements[0])
            // call heapify(1)
        this.sortedElements = new ArrayList<>();
        int secondLastElement = 0;
        int lastElement = 0;
        
        // loop through the heap removeing the max element and adding it to the sorted list
        for (int i = 0; i < elements.size(); i++) {
           sortedElements.add(i, removeMaxElement()); 
           heapify(1);  
        }
        
        secondLastElement = elements.get(elements.size() - 2);
        lastElement = elements.get(elements.size() - 1);
        
        // add the larger of of the last two elements to the sorted lists
        if(secondLastElement > lastElement){
            sortedElements.add(secondLastElement);
            sortedElements.add(lastElement);
            
        }else{
            sortedElements.add(lastElement);
            sortedElements.add(secondLastElement);
           
        }
        // remove remaining elements
        elements.remove(elements.size() - 1);
        elements.remove(elements.size() - 1);
        elements.remove(0);
        
        // add sorted elements to orignal list
        elements.addAll(sortedElements);

      
        
        
    }

    public void buildHeap()
    {
        // loop i from n/2 to 1
        // heapify(i)
        //
        int x = 2;
        int i = 0;
        
        
        
        for (i = (int)Math.floor(elements.size()/x); i > 1; x++) {
            i = (int)Math.floor(elements.size()/x);
            heapify(i);
        }
        
    }

    public void heapify(int nodeIndex)
    {
        int leftChild = -1;
        int rightChild = -1;
        int leftChildIndex = nodeIndex*2;
        int rightChildIndex = nodeIndex*2 + 1;
        // Find the largest value between the nodeIndex both of its children
        //
        // location of left child is nodeIndex * 2. Value is elements[nodeIndex*2]
        if(elements.size()-1 >= leftChildIndex)
            leftChild = elements.get(leftChildIndex);
      
        // location of right child is nodeIndex * 2 + 1. Value is elements[nodeIndex*2 + 1]
       if(elements.size()-1 >= rightChildIndex)
            rightChild = elements.get(rightChildIndex);
       
        // swap nodeIndex with larger of children.
        // Check if the swapped (largest) element is a parent, if so, heapify(indexOflargerChild)
       if(leftChild != -1 && rightChild != -1){// if left and right child exist
        if(leftChild > rightChild){ // if left child has larger value
            siftUp(leftChildIndex);  // siftUp left child
            if(isParent(leftChildIndex)) // if left child is a parent heapify
                heapify(leftChildIndex);
        }else{                              //else right child has larger value
            siftUp(rightChildIndex);
             if(isParent(rightChildIndex))
                heapify(rightChildIndex);
        }
       }else if(leftChild == -1){// if left child deosn't exist
           siftUp(rightChildIndex); // siftUp right child
           if(isParent(rightChildIndex)) // if child is a parent heapify
             heapify(rightChildIndex);
       }else if(rightChild == -1){ // if right child deosn't exist
           siftUp(leftChildIndex); // siftUp left child
           if(isParent(leftChildIndex)) // if child is a parent heapify
                heapify(leftChildIndex);
       }

        
    }
    
    private boolean isParent(int index){ // return false if index is a leaf node
        return (index * 2 < elements.size() || (index * 2) + 1 < elements.size());
    }
}

