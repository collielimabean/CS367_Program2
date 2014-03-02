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
    
    private MessageLoop<String> loop;
    private DotMatrix matrix;
    
    public DisplayEditor(MessageLoop<String> loop, String alphabetPath)
    {
        //null checks?
        
        this.loop = loop;
        
        matrix = new DotMatrix();
        matrix.loadAlphabets(alphabetPath);
    }
    
    public boolean loopIsEmpty()
    {
        if(loop.size() <= 0)
            return true;
        
        return false;
    }
    
    public void displayLoop()
    {
        
    }
        
    public void printCurrentContext()
    {
        
    }
    
    public boolean loadData(String loadPath)
    {
        return true;
    }
    
    public boolean saveData(String savePath)
    {
        return true;
    }
    
    public void addAfter(String message)
    {
        if(loop.size() == 0)
        {
            List<String> add = new ArrayList<String>();
            for (int x = 0; x < message.length(); x++)
            {
                add.add(Character.toString(message.charAt(x))); 
                //why doesn't this work? incomplete 
            }
        // make array, split string into char, check for exceptions, run through it, add dot matrix data                                                          
        }
    }
    
    public void addBefore(String message)
    {
        List<String> names = new ArrayList<String>();
        /*unfinished
         *make array to store all the characters
         *add into loop with prev()
         *print out with special display settings
         */
    }
    
    public boolean removeCurrent()
    {
        if(loopIsEmpty())
            return true;
        
        loop.removeCurrent();
        loop.forward();
        
        if(loopIsEmpty())
            return true;
        
        return false;
    }
    
    public boolean traverseLoop(int steps)
    {
        if(loopIsEmpty()) 
        {
            //print no messages
            return false;
        }          
        
        if(Math.abs(steps) > loop.size())
            steps = steps % loop.size();
        
        for(int i = 0; i < Math.abs(steps); i++)
        {
            if(steps > 0)
                loop.forward();
            
            else loop.back();
        }
        
        return true;
    }
    
    public boolean replaceCurrent(String replace)
    {
        if(!matrix.isValidCharacter(replace))
        {
            //Print invalid
            return false;
        }
            
        loop.addAfter(replace);
        loop.back();
        loop.removeCurrent(); 
        
        return true;
    }
    
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
        
        DisplayEditor editor = new DisplayEditor(new MessageLoop<String>(),
                                                    "alphabets.txt");
        
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
                        editor.saveData(remainder.trim());
                        break;
                        
                    case 'n':
                        boolean traversed = editor.traverseLoop(1);
                        
                        if(traversed)
                            editor.printCurrentContext();
                        
                        break;
                        
                    case 'x':
                        boolean isEmpty = editor.removeCurrent();
                        
                        if(isEmpty)
                            editor.printCurrentContext();
                        
                        else System.out.println("No messages");
                       
                        break;
                        
                    case 'c': 
                    	editor.printCurrentContext();
                        break;
                        
                    case 'l':
                    	editor.loadData(remainder);
                        break;
                        
                    case 'p':
                        boolean moved = editor.traverseLoop(-1);
                        
                        if(moved)
                            editor.printCurrentContext();
                        
                        break;
                        
                    case 'a':
                        editor.addAfter(remainder);
                        break;
                        
                    case 'r':
                        editor.replaceCurrent(remainder.substring(0, 1));
                        
                    	if(editor.loopIsEmpty())
                    	    System.out.println("no messages");
                    	
                    	break;
                        
                    case 'd':
                    	editor.displayLoop();
                        break;
                        
                    case 'j':
                        boolean stateChanged = editor.traverseLoop(
                                            Integer.parseInt(remainder.trim()));
                        
                        if(stateChanged)
                            editor.printCurrentContext();
                        
                        break;
                        
                    case 'i':
                        editor.addBefore(remainder.trim());
                        editor.printCurrentContext();
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
