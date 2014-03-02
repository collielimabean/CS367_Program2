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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This wrapper class exposes methods to operate on a DotMatrix. This class 
 * provides an entry point for the user to operate on a DotMatrix.
 */
public class DisplayEditor
{
    
    private MessageLoop<String> loop;
    private DotMatrix matrix;
    
    /**
     * Constructs a DisplayEditor object.
     * @param loop MessageLoop containing DotMatrix strings
     * @param alphabetPath Path to an alphabet to provide appropriate mappings.
     */
    public DisplayEditor(MessageLoop<String> loop, String alphabetPath)
    {
        //null checks?
        
        this.loop = loop;
        
        matrix = new DotMatrix();
        matrix.loadAlphabets(alphabetPath);
    }
    
    /**
     * Returns whether the loop has no elements or not.
     * @return true if message loop is empty
     */
    public boolean loopIsEmpty()
    {
        if(loop.size() <= 0)
            return true;
        
        return false;
    }
    
    /**
     * Displays every element in the MessageLoop.
     */
    public void displayLoop()
    {
        
    }
    
    /**
     * Displays the context of the current element.
     * i.e. display previous, current, and then next element.
     */
    public void printCurrentContext()
    {
        
    }
    
    /**
     * Loads a text file containing DotMatrix strings into the MessageLoop.
     * @param loadPath
     * @return true if load successful, false otherwise
     */
    public boolean loadData(String loadPath)
    {
        File load = new File(loadPath);
        
        if(!load.exists() || !load.canRead())
        {
            System.out.println("unable to load");
            return false;
        }
        
        //TODO load file
            
        return true;
    }
    
    /**
     * Saves the current MessageLoop into a text file with the specified path.
     * @param savePath 
     * @return true if successful, false otherwise.
     */
    public boolean saveData(String savePath)
    {
        File output = new File(savePath);
        
        if(output.exists())
            System.out.println("warning: file already exists" 
                                  + ", will be overwritten");
        
        if(!output.canWrite())
        {
            System.out.println("unable to save");
            return false;
        }
        
        //TODO Write file.
            
        return true;
    }
    
    /**
     * Adds the specified message into the MessageLoop
     * after the current element. Adds no elements if invalid chars found.
     * @param message Requested stream of characters to add after current.
     * @return true if no invalid characters found, false otherwise
     */
    public boolean addAfter(String message)
    {
        //TODO check for invalid chars!
        if(loop.size() == 0)
        {
            List<String> add = new ArrayList<String>();
            for (int x = 0; x < message.length(); x++)
            {
                add.add(Character.toString(message.charAt(x))); 
            }
        // make array, split string into char, check for exceptions, run through it, add dot matrix data                                                          
        }
        
        return true; //TODO Return properly
    }
    
    /**
     * Adds the specified message into the MessageLoop
     * before the current element. Adds no elements if invalid chars found.
     * @param message Requested stream of characters to add before current.
     * @return true if no invalid characters found, false otherwise
     */
    public boolean addBefore(String message)
    {
        //TODO Check for invalid chars!
        List<String> names = new ArrayList<String>();
        /*TODO unfinished
         *make array to store all the characters
         *add into loop with prev()
         *print out with special display settings
         */
        
        return true; //TODO return properly
    }
    
    /**
     * Removes the current DotMatrix string in the loop.
     * Returns if no elements to remove.
     */
    public void removeCurrent()
    {
        if(loopIsEmpty())
            return;
        
        loop.removeCurrent();
        loop.forward();
    }
    
    /**
     * Traverses the MessageLoop. A negative step indicates backward movement,
     * and a positive step indicates forward movement.
     * @param steps <p>An integer that specifies how many steps to go; 
     * Positive indicates forward, negative backward.</p>
     * @return true if loop traversed, false otherwise
     */
    public boolean traverseLoop(int steps)
    {
        if(loopIsEmpty()) 
        {
            //TODO print no messages
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
    
    /**
     * Replaces the current DotMatrix Character with a new one.
     * @param replace The DotMatrix character to replace Current.
     * @return true if successful replacement, false otherwise.
     */
    public boolean replaceCurrent(String replace)
    {
        if(loopIsEmpty())
        {
            //TODO Print no messages
            return false;
        }
        
        if(!matrix.isValidCharacter(replace))
        {
            //TODO Print invalid
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
            //TODO handle 0 arguments
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
                        boolean noInvalidChars = editor.addAfter(remainder);
                        
                        if(noInvalidChars)
                            editor.printCurrentContext();
                        
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
