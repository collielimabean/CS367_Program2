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

public class MessageLoopIterator<E> implements Iterator<E> 
{
    private MessageLoop <E> temp;
    //TODO Implement constructor AFTER decision on direct/indirect access
    public MessageLoopIterator(MessageLoop <E> looper)
    {
    	temp = looper;
    }
    @Override
    public boolean hasNext()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    /**
     * returns the next reference in the listNode
     */
    public E next() 
    {
    	E data = temp.getCurrent();
    	temp.forward();
        return data;
    }

    @Override
    public void remove()
    {
        // TODO Auto-generated method stub
        
    }

}
