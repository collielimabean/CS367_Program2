///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  DisplayEditor.java
// File:             DblListNode.java
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

class DblListNode<E>
{
    
    private E data;
    private DblListNode<E> previous;
    private DblListNode<E> next;
    
    public DblListNode(E data)
    {
        this(data, null, null);
    }
    
    public DblListNode(E data, DblListNode<E> next)
    {
        this(data, next, null);
    }
    
    public DblListNode(E data, DblListNode<E> next, DblListNode<E> previous)
    {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
    
    public E getData()
    {
        return data;
    }
    
    public DblListNode<E> getPrevious()
    {
        return previous;
    }
    
    public DblListNode<E> getNext()
    {
        return next;
    }
    
    public void setData(E data)
    {
        this.data = data;
    }
    
    public void setPrevious(DblListNode<E> node)
    {
        previous = node;
    }
    
    public void setNext(DblListNode<E> node)
    {
        next = node;
    }
}
