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

public class MessageLoop<E> implements LoopADT<E> 
{
    
    private DblListNode<E> current;
    private int numItems;
    
    public MessageLoop()
    {
        numItems = 0;
        
        /*
         * IMPORTANT: MUST BE DECIDED ON FIRST!
         * 
         * Design 1:
         *  Do not init current until item added
         *  Then init on addBefore/addAfter, etc.
         * 
         * Design 2:
         *  Init current, but have it reference itself
         *  as previous/next node, but leave numItems = 0
         *  When addAfter, addBefore is called, since numItems = 0
         *  replace current data as the new item data
         *  (still referencing itself)
         */
    }
    
    @Override
    public void addBefore(E item) 
    {
        // TODO Auto-generated method stub
        
        /*
         * Methodology:
         * 
         * 1. Check if the next item before current is null
         *      a. if null, direct initialize and add to beginning
         *      b. ensure that new node is linked
         *      c. (reason: circular linked list)
         *      
         * 2. Initialize new Node
         *      a. params: current, current.getPrevious
         *      b. current == next node
         *      c. current.prev == prev node
         *      
         * 3. Set current.previous()'s next node as new node
         * 
         * 4. Set current's previous node as new node 
         */
    }

    @Override
    public void addAfter(E item) 
    {
        // TODO Auto-generated method stub
        
        /*
         * Methodology:
         * 
         * 1. Check if the next item after current is null
         *      a. if null, direct initialize and add
         *      b. ensure that new node is linked to beginning
         *      c. (reason: circular linked list)
         *      
         * 2. Initialize new Node
         *      a. params: current.getNext(), current
         *      b. current.getNext() == next node
         *      c. current == prev node
         *      
         * 3. Set current.next()'s prev as new node
         * 
         * 4. Set current's next node as new node 
         */
        
    }

    @Override
    public E getCurrent() 
    {
        if(numItems <= 0)
            throw new EmptyLoopException();
        
        //TODO Implement this method
        
        /*
         * Methodology:
         * 
         * 1. Return current.getData()
         * 
         * CAUTION: Depends on class design!
         * SEE the constructor notes.
         */
        
        return null;
    }

    @Override
    public E removeCurrent() 
    {
        if(numItems <= 0)
            throw new EmptyLoopException();
        
        // TODO Implement this method
        
        /*
         * Methodology:
         * 
         * 1. Store references to previous and next nodes
         * 
         * 2. Store current's data, then nullify current
         * 
         * 3. Set previous node next field to current.next
         * 4. Set next node previous field to current.prev
         * 5. Return current's data.
         */
        
        return null;
    }

    @Override
    public void forward() 
    {
        // TODO Auto-generated method stub
        
        /*
         * Methodology:
         * 
         * 1. Set current to next node
         * Be careful about linked lists of size 1!
         */
    }

    @Override
    public void back() 
    {
        // TODO Auto-generated method stub
        /*
         * Methodology:
         * 
         * 1. Set current to previous node
         * Be careful about linked lists of size 1!
         */
    }

    @Override
    public int size() 
    {
        return numItems;
    }

    @Override
    public Iterator<E> iterator() 
    {
        //TODO Determine if we need direct or indirect access
        return null;
    }

}
