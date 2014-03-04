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

/**
 * This class exposes methods for a doubly linked node.
 * @param <E> Type of DblListNode
 */
class DblListNode<E>
{
    private E data;
    private DblListNode<E> previous;
    private DblListNode<E> next;
    
    /**
     * Default constructor. Initializes with null data, and references
     * are set to null.
     */
    DblListNode()
    {
        this(null, null, null);
    }
    
    /**
     * Initializes a DblListNode with specified data, with previous and next
     * references set to null.
     * @param data The object that the DblListNode will hold.
     */
    DblListNode(E data)
    {
        this(data, null, null);
    }
    
    /**
     * Initializes a DblListNode with specified data and specified next node.
     * Reference to previous node is set to null.
     * @param data The object that the DblListNode will hold.
     * @param next A DblListNode that will be next after this node
     */
    DblListNode(E data, DblListNode<E> next)
    {
        this(data, next, null);
    }
    
    /**
     * Initializes a DblListNode with specified data, next node, and prev node.
     * @param data The object that the DblListNode will hold.
     * @param next A DblListNode that will be next after this node
     * @param previous A DblListNode that will be before this node
     */
    DblListNode(E data, DblListNode<E> next, DblListNode<E> previous)
    {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
    
    /**
     * Gets the data that this node references.
     * @return The data referenced by this node.
     */
    public E getData()
    {
        return data;
    }
    
    /**
     * Gets the DblListNode before this node.
     * @return DblListNode that precedes this node.
     */
    public DblListNode<E> getPrevious()
    {
        return previous;
    }
    
    /**
     * Gets the DblListNode after this node.
     * @return DblListNode that comes after this node.
     */
    public DblListNode<E> getNext()
    {
        return next;
    }
    
    /**
     * Sets the data that this node will reference.
     * @param data Object that this node will hold.
     */
    public void setData(E data)
    {
        this.data = data;
    }
    
    /**
     * Sets the previous node that this node will reference.
     * @param node A DblListNode that will be before this node.
     */
    public void setPrevious(DblListNode<E> node)
    {
        previous = node;
    }
    
    /**
     * Sets the next node that this node will reference.
     * @param node A DblListNode that will be after this node.
     */
    public void setNext(DblListNode<E> node)
    {
        next = node;
    }
}
