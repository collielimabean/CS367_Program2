import java.util.Iterator;


public class MessageLoop<E> implements LoopADT<E> 
{
    
    private DblListNode<E> current;
    private int numItems;
    
    public MessageLoop()
    {
        numItems = 0;
    }
    
    @Override
    public void addBefore(E item) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addAfter(E item) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public E getCurrent() 
    {
        if(numItems <= 0)
            throw new EmptyLoopException();
        
        //TODO Implement this method
        
        return null;
    }

    @Override
    public E removeCurrent() 
    {
        if(numItems <= 0)
            throw new EmptyLoopException();
        
        // TODO Implement this method
        
        return null;
    }

    @Override
    public void forward() 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void back() 
    {
        // TODO Auto-generated method stub
        
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
