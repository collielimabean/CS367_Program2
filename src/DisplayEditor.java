///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            DisplayEditor.java
// Files:            DblListNode.java, MessageLoop.java,
//                   MessageLoopIterator.java, EmptyLoopException.java
//                   UnrecognizedCharacterException.java
//
// Semester:         CS367 Spring 2014
//
// Author:           William Jen
// Email:            wjen@wisc.edu
// CS Login:         jen
// Lecturer's Name:  Professor Jim Skrentny
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Allen Hung
// Email:            athung2@wisc.edu
// CS Login:         ahung
// Lecturer's Name:  Professor Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides an entry point for the user to perform operations on
 * a text-file containing a dot-matrix.
 */
public class DisplayEditor
{
    
    /**
     * Entry point for this program.
     * @param args Contains Command-line arguments
     */
    public static void main(String[] args)
    {
        //Check if valid number of command line arguments
        if(args.length != 2 && args.length != 0)
        {
            System.out.println("Invalid command-line arguments.");
            return;
        }
        
        else
        {
         //not sure what to put here  
        }
        
        MessageLoop<String> loop = new MessageLoop<String>();
        DotMatrix matrix = new DotMatrix();
        matrix.loadAlphabets("alphabets.txt");
        boolean stop = false;
        Scanner scanner = new Scanner(System.in);
        
        while (!stop) 
        {
            System.out.println("Enter commands (? for help)>");
            String input = scanner.nextLine();
            String remainder = null;
            
            if(input.length() > 0) 
            {
                char option = input.charAt(0);
                
                if(input.length() > 1) 
                {
                    remainder = input.substring(1).trim();
                }
                
                //TODO Implement command options
                switch(option)
                {
                    case '?':
                        System.out.println("s (save) \t l (load)" 
                                            + "\t d (display)");
                        
                        System.out.println("n (next) \t p (previous)"
                                            + "\t j (jump");
                        
                        System.out.println("x (delete) \t a (add after)"
                                            + "\t i (insert before)");
                        
                        System.out.println("c (context) \t r (replace)"
                                            + "\t q (quit)");
                        
                        break;
                        
                    case 's':
                    	if( loop.size() == 0)
                    		System.out.println("No messages to save"); //yea don't know how to use printwriter
                        break;
                        
                    case 'n':
                    	if( loop.size() == 0)
                    		System.out.println("No messages");
                    	loop.forward();
                    	System.out.println(loop.getCurrent()); 
                    	//need to add display settings
                    	//need to add dotmatrix data
                        break;
                        
                    case 'x':
                    	if( loop.size() == 0)
                    		System.out.println("No messages");
                    	loop.removeCurrent();
                    	if(loop.size() == 0)
                    		System.out.println("No messages");
                    	else
                    	{
                    		loop.forward();
                    		System.out.println(loop.getCurrent());
                    	}
                    	//complete
                        break;
                        
                    case 'c': //lol don't understand
                    	
                        break;
                        
                    case 'l':
                    	if( loop.size() == 0)
                    		System.out.println("No messages");
                        break;
                        
                    case 'p':
                    	if( loop.size() == 0)
                    		System.out.println("No messages");
                    	loop.back();
                    	System.out.println(loop.getCurrent()); 
                    	//need to add display settings
                    	//need to add dotmatrix data
                        break;
                        
                    case 'a':
                    	if( loop.size() == 0)
                    	{
                    		List<String> add = new ArrayList<String>();
                    		String s = input.substring(1);
                    		s.trim();
                      		for (int x = 0; x < s.length(); x++)
                      		{
                      			add.add(s.charAt(x)); 
                      			//why doesn't this work? incomplete 
                      		}
                      	// make array, split string into char, check for exceptions, run through it, add dot matrix data						  					  	    	  
                    	}
                        break;
                        
                    case 'r':
                    	if( loop.size() == 0)
                    		System.out.println("No messages");
                    	String temp = input.substring(1);
                    	temp.trim();
                    	loop.addAfter(temp);
                    	loop.back();
                    	loop.removeCurrent(); 
                    	//need to add display settings
                    	//need to add dotmatrix data                        
                    	break;
                        
                    case 'd':
                    	if( loop.size() == 0)
                    		System.out.println("No messages"); //need add display settings
                        break;
                        
                    case 'j':
                    	if( loop.size() == 0)
                    		System.out.println("No messages");
                    	String integer = input.substring(1);
                    	integer.trim();
                    	int jump = Integer.parseInt(integer);
                    	if (jump < 0)
                    	{
                    		for (int x = 0; x < jump; x++)
                    		{
                    			loop.back();
                    		}
                    	}
                    	else 
                    	{
                    		for (int x = 0; x < jump; x++)
                    		{
                    			loop.forward();
                    		}
                    	}
                    	System.out.println(loop.getCurrent()); //complete	                    		
                        break;
                        
                    case 'i':
                    	if( loop.size() == 0)
                    		System.out.println("No messages");
                    	List<String> names = new ArrayList<String>();
                    	/*unfinished
                    	 *make array to store all the characters
                    	 *add into loop with prev()
                    	 *print out with special display settings
                    	 */
                        break;
                        
                    case 'q':
                        stop = true;
                        System.out.println("Quitting...");
                        break;
                    
                    default:
                        System.out.println("Invalid command!");
                        main(args);
                }
            }
        }
        
        scanner.close();
    }
}
