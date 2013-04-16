package searchengine;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Creates a Max Heap that will later be used to store WordResult objects for search engine access
 */

public class MaxHeap implements Serializable 
{
 private ArrayList<WordResult> heap; 
 
 /**
  * MaxHeap constructor, initializes the heap arrayList
  */
 public MaxHeap()
 {
  heap = new ArrayList();
 }
 
 /**
  * Gets the index of the left child from the parent's index, returns -1 if non-existent
  * @param parent Parent index
  * @return Left child index
  */
 private int getLeftChildIndex(int parent)
 {
  if (2 * parent + 1 < heap.size())
  {
   return 2 * parent + 1;
  }
  else
  {
   return -1;
  }
 }
 
 /**b
  * Gets the index of the right child from the parent's index, returns -1 if non-existent
  * @param parent Parent index
  * @return Right child index
  */
 private int getRightChildIndex(int parent)
 {
  if (2 * parent + 2 < heap.size())
  {
   return 2 * parent + 2;
  }
  else
  {
   return -1;
  }
 }
 
 /**
  * Gets the index of the parent from the child's index
  * @param child Child index
  * @return Parent index
  */
 private int getParentIndex(int child)
 {
  return (child - 1)/2;
 }
 
 /**
  * Adds a left child to the specified parent
  */
 public void addLeftChild(int parent, WordResult element)
 {
  heap.add(getLeftChildIndex(parent), element);
 }
 
 /**
  * Adds a right child to the specified parent
  */
 public void addRightChild(int parent, WordResult element)
 {
  heap.add(getRightChildIndex(parent), element);
 }
 
 /**
  * Adds a parent to the specified child
  */
 public void addParent(int child, WordResult element)
 {
  heap.add(getParentIndex(child), element);
 }
 
 /**
  * Gets the minimum element
  * @return The root of the heap
  */
 public WordResult getMax()
 { 
  if (heap.size() != 0)
  {
   return heap.get(0);
  }
  else
  {
   return null;
  }
 }
 
 /**
  * Fixes the heap when you add a new element
  */
 public void bubbleUp(WordResult bubblingElement)
 { 
  int elementIndex = heap.indexOf(bubblingElement);
                  
  while (bubblingElement.compareTo(heap.get(getParentIndex(elementIndex))) > 0)
  {
                        
   swap(elementIndex, getParentIndex(elementIndex));
   elementIndex = getParentIndex(elementIndex);
  }
 }
 
 /**
  * Gets a child given the index
  */
 private WordResult childHelper(int childIndex)
 {
  if (childIndex != -1)
  {
   return heap.get(childIndex);
  }
  else
  {
   return null;
  }
 }
 
 /**
  * Fixes the heap when you remove the maximum element
  */
 private void bubbleDown()
 { 
  int elementIndex = 0;
  WordResult movingElement = heap.get(elementIndex);
  int leftChildIndex = getLeftChildIndex(elementIndex);
  int rightChildIndex = getRightChildIndex(elementIndex);
  
  WordResult leftChild = childHelper(leftChildIndex);
  WordResult rightChild = childHelper(rightChildIndex);
   
  while (((leftChild != null) && movingElement.compareTo(leftChild) < 0) || ((rightChild != null) && movingElement.compareTo(rightChild) < 0))
  {
   if (leftChildIndex == -1)
   {
    swap(elementIndex, rightChildIndex);
    elementIndex = rightChildIndex;
   }
   else if (rightChildIndex == -1)
   {
    swap(elementIndex, leftChildIndex);
    elementIndex = leftChildIndex;
   }
   else if (leftChild.compareTo(rightChild) > 0)  //leftChild is larger
   {
    swap(elementIndex, leftChildIndex);
    elementIndex = leftChildIndex;
   }
   else           //rightChild is larger
   {
    swap(elementIndex, rightChildIndex);
    elementIndex = rightChildIndex;
   }
    
   leftChildIndex = getLeftChildIndex(elementIndex);
   rightChildIndex = getRightChildIndex(elementIndex);
   leftChild = childHelper(leftChildIndex);
   rightChild = childHelper(rightChildIndex);
  }
 }
 
 /**
  * Swaps elements at indexes i and j
  */
 private void swap(int i, int j)
 {
  WordResult temp = heap.get(i);
  heap.set(i, heap.get(j));
  heap.set(j, temp);
 }
 
 /**
  * Adds an element to the heap
  * @param element Element to be added
  */
 public void add(WordResult element)
 {
  heap.add(element);
  bubbleUp(element);
 }
 
 /**
  * Removes the smallest element from the heap
  */
 public void removeMax()
 {
  if (heap.size() != 0)
  {
   WordResult replacement = heap.get(heap.size() - 1);
   heap.set(0, replacement);
   heap.remove(heap.size() - 1);
  
   if (heap.size() > 1)
   {
    bubbleDown();
   }
  }
 }
 
 /**
  * Generic toString method
  */
 public String toString()
 { 
  String returnString = "";
  
  for (int i = 0; i < heap.size(); i++)
  {
   returnString += heap.get(i) + "\n";
  }
  
  return returnString;
 }
        
 /**
  * Gets the size of the heap
  */
    public int size() 
    {
        return heap.size();
    }
    
    /**
     * Gets an ArrayList representation of the heap
     */
    public ArrayList<WordResult> getHeap() 
    {
        return heap;
    }
 
    /**
     * Resets an element in the heap
     */
 public void set(int index, WordResult setElement)
 {
  heap.set(index, setElement);
 }
     
 /**
  * Gets a WordResult from the heap given an article name
  */
    public WordResult getElement(String _articleName) 
    {
        for (int i = 0; i < heap.size(); i++) 
        {
            String findArticle = heap.get(i).getArticle().getName();
            if (findArticle.equals(_articleName)) 
            {
                return heap.get(i);
            }
        }
        return null; 
    }
        
    /**
     * Helper function that clones this heap (useful abstraction)
     */
    public MaxHeap clone() 
    {
        MaxHeap newHeap = new MaxHeap();
        for (WordResult element : heap)
        {
            newHeap.add(element);
        }
        return newHeap;
    }
}