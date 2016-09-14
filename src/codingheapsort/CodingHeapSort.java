/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingheapsort;

/**
 *
 * @author mxw13
 */
public class CodingHeapSort {


    public static void main(String[] args)
    {
        Heap myHeap = new Heap("C:\\Users\\mxw13\\Documents\\UNCC\\SPR 2016\\ECGR 2103\\CodingHeapSort\\src\\codingheapsort/textFile.txt");

        myHeap.buildHeap();

        myHeap.outputResult("buildheapResult.txt");

        myHeap.removeMaxElement();

        myHeap.removeMaxElement();

        myHeap.addElement(30);
        
        myHeap.heapSort();

        myHeap.outputResult("afterHeapsort.txt");
    }

    
}
