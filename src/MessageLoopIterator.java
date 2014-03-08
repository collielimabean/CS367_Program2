///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  DisplayEditor.java
// File:             MessageLoopIterator.java
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
 * This class implements the Iterator interface, and allows users to
 * traverse a MessageLoop using iterators.
 * @param <E> Type that the MessageLoopIterator will hold
 */
class MessageLoopIterator<E> implements Iterator<E> 
{
    private DblListNode<E> current;
    private DblListNode<E> head;
    private boolean traversed;
    
    /**
     * Constructs a MessageLoopIterator object.
     * @param current Current DblListNode that MessageLoop is referencing
     */
    MessageLoopIterator(DblListNode<E> current)
    {
        this.current = current;
        head = current;
        traversed = false;
    }
    
    /**
     * Checks whether if the next node exists and if the loop has come
     * full circle.
     * @return true if the iterator has looped to the beginning, false otherwise
     */
    public boolean hasNext()
    {
        return !traversed;
    }

    /**
     * Advances the iterator to the next node.
     * @return the item that the iterator passed
     */
    public E next() 
    {
        E data = current.getData();
        
        if(hasNext())
        {
            current = current.getNext();
            
            if(current == head)
                traversed = true;
        }
        
        return data;
    }
    
    /**
     * Removes the current item, and advances the iterator forward.
     */
    public void remove()
    {
        //Store previous and next references
        DblListNode<E> previous = current.getPrevious();
        DblListNode<E> next = current.getNext();
        
        //wipe current
        current.setData(null);
        current.setNext(null);
        current.setPrevious(null);
        
        //set references
        previous.setNext(next);
        next.setPrevious(previous);
        
        //set current to next
        current = next;
        
        if(current == head)
            traversed = true;
    }

}
