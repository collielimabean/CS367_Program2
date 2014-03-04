///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  DisplayEditor.java
// File:             MessageLoop.java
// Semester:         CS367 Spring 2014
//
// Author:           William Jen <wjen@wisc.edu>
// CS Login:         jen
// Lecturer's Name:  Professor Jim Skrentny
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Allen Hung
// CS Login:         ahung
// Lecturer's Name:  Professor Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Iterator;

/**
 * This class creates a circular, doubly-linked list, and exposes methods
 * to navigate the loop.
 * @see LoopADT.java
 * @param <E> Type that the MessageLoop will hold
 */
public class MessageLoop<E> implements LoopADT<E> 
{
    
    private DblListNode<E> current;
    private int numItems;
    
    /**
     * Constructs a MessageLoop object.
     */
    public MessageLoop()
    {
        numItems = 0;
        current = null;
    }
    
    /**
     * Adds an item before the current node in the MessageLoop.
     * @param item that will be added to the MessageLoop
     * @throws IllegalArgumentException if item is null
     */
    public void addBefore(E item) 
    {
        if(item == null)
            throw new IllegalArgumentException();
        
        //if current null, initialize the new item as the only element
    	if (numItems == 0)
    	{
    		current = new DblListNode<E>(item);
            current.setNext(current);
            current.setPrevious(current);
    		numItems++;
    		return;
    	}	
    	
    	DblListNode <E> newNode = new DblListNode<E> (item, current, current.getPrevious());
    	
    	//Set appropriate references
        current.getPrevious().setNext(newNode);
    	current.setPrevious(newNode);

    	//Increment the number of items in the loop
    	numItems++;
    }
    
    /**
     * Adds an item after the current node in the MessageLoop.
     * @param item that will be added to the MessageLoop
     * @throws IllegalArgumentException if item is null
     */
    public void addAfter(E item) 
    {
        if(item == null)
            throw new IllegalArgumentException();
        
        //if current null, initialize the new item as the only element
        if (numItems == 0)
        {
            current = new DblListNode<E>(item);
            current.setNext(current);
            current.setPrevious(current);
            numItems++;
            return;
        }   
        
        DblListNode <E> newNode = new DblListNode<E> (item, current.getNext(), current);
        
        //Set appropriate references
        current.getNext().setPrevious(newNode);
        current.setNext(newNode);
        
        //Increment the number of items in the loop
        numItems++;
        
    }

    /**
     * Gets the current item in the MessageLoop
     * @return the current item in the MessageLoop.
     */
    public E getCurrent() 
    {
        if(numItems <= 0)
            throw new EmptyLoopException();
        
        return current.getData();
    }
    
    /**
     * Removes the current node from the MessageLoop and advances.
     * @return the item being removed
     */
    public E removeCurrent() 
    {
        if(numItems <= 0)
            throw new EmptyLoopException();
        
        //Store the previous and next nodes relative to current
        DblListNode<E> previous = current.getPrevious();
        DblListNode<E> next = current.getNext();
        
        //Store current's data
        E data = current.getData();
        
        //Set current's data and references to null
        current.setData(null);
        current.setNext(null);
        current.setPrevious(null);
        
        //Reset previous/next references so that
        //they point to the correct node
        previous.setNext(next);
        next.setPrevious(previous);
        
        //Set the current reference to next
        current = next;
        
        //After successful removal, decrease # of items
        numItems--;
        
        return data;
    }

    /**
     * Advances the current node to the next node in the MessageLoop.
     */
    public void forward() 
    {
        if (current == null || numItems <= 0)
        	throw new EmptyLoopException();
        
        current = current.getNext();
    }
    
    /**
     * Moves the current node to the previous node in the MessageLoop.
     */
    public void back() 
    {
        if(current == null || numItems <= 0)
            throw new EmptyLoopException();
        
        current = current.getPrevious();
    }

    /**
     * Gets the number of items in the MessageLoop.
     * @return An int containing the number of items in the MessageLoop
     */
    public int size() 
    {
        return numItems;
    }
    
    /**
     * Returns the iterator for the MessageLoop.
     * @return an Iterator for the MessageLoop
     */
    public Iterator<E> iterator() 
    {
        return new MessageLoopIterator<E>(current);
    }

}
