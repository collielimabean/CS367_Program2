///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Displayjava
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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * This wrapper class exposes methods to operate on a DotMatrix. This class 
 * provides an entry point for the user to operate on a DotMatrix.
 */
public class DisplayEditor
{
    static final String NO_MESSAGES = "no messages";
    
    static final String MATRIX_OFFLINE_SEPARATOR = "#";
    static final String LOOP_SEPARATOR = "*";
    static final int NUMBER_SEPARATORS = 10;
    
    static MessageLoop<ArrayList<String>> loop;
    static DotMatrix matrix;
    
    /**
     * Returns whether the loop has no elements or not.
     * @return true if message loop is empty
     */
    static boolean loopIsEmpty()
    {
        if(loop.size() <= 0)
            return true;
        
        return false;
    }
    
    /**
     * Displays every element in the MessageLoop.
     */
    static void displayLoop()
    {
        if(loopIsEmpty())
        {
            System.out.println(NO_MESSAGES);
            return;
        }
        
        Iterator<ArrayList<String>> it = loop.iterator();
        
        int counter = 0;
        
        while(it.hasNext() && counter < loop.size())
        {
            List<String> data = it.next();
            
            for(String s : data)
                System.out.println(s);
            
            printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
            
            counter++;
        }
    }
    
    /**
     * Displays the context of the current element.
     * i.e. display previous, current, and then next element.
     */
    static void printCurrentContext()
    {
        switch(loop.size())
        {
            case 0:
                return;
                
            case 1:
                printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
                
                ArrayList<String> list = loop.getCurrent();
                
                for(String s : list)
                    System.out.println(s);
                
                printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
                
                return;
            case 2:
                printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
                
                ArrayList<String> list1 = loop.getCurrent();
               
                for(String s : list1)
                    System.out.println(s);
                
                printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
                
                loop.forward();
                
                ArrayList<String> list2 = loop.getCurrent();
                
                for(String s : list2)
                    System.out.println(s);
                
                return;
            default:
                loop.back();
                
                for(int i = 0; i < 3; i++)
                {
                    ArrayList<String> list3 = loop.getCurrent();
                    
                    for(String s : list3)
                        System.out.println(s);
                    
                    if(i < 2)
                    {
                        loop.forward();
                        printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
                    }
                }
                
                loop.back();
                
                return;
        }
    }
    
    /**
     * Loads a text file containing DotMatrix strings into the MessageLoop.
     * @param loadPath
     * @return true if load successful, false otherwise
     */
    static boolean loadData(String loadPath)
    {
        File load = new File(loadPath);
        
        if(!load.exists() || !load.canRead())
        {
            System.out.println("unable to load");
            return false;
        }
        
        try 
        {
            Scanner scan = new Scanner(load);
            ArrayList<String> input = new ArrayList<String>();
            
            while(scan.hasNextLine())
            {
                String line = scan.nextLine();
                
                if(line.contains(MATRIX_OFFLINE_SEPARATOR))
                {
                    loop.addAfter(input);
                    loop.forward();
                    input = new ArrayList<String>();
                }
                
                else input.add(line);
            }
            
            loop.forward();
            
            scan.close();
            
        } 
        
        catch (FileNotFoundException e)
        {
            System.out.println("unable to load");
            return false;
        }
            
        return true;
    }
    
    /**
     * Saves the current MessageLoop into a text file with the specified path.
     * @param savePath 
     * @return true if successful, false otherwise.
     */
    static boolean saveData(String savePath)
    {
        if(loopIsEmpty())
        {
            System.out.println("no messages to save");
            return false;
        }
        
        File output = new File(savePath);
        
        if(output.exists())
            System.out.println("warning: file already exists" 
                                  + ", will be overwritten");
       
        try 
        {
            PrintWriter writer = new PrintWriter(output);
            
            Iterator<ArrayList<String>> it = loop.iterator();
            
            int count = 0;
            
            while(it.hasNext() && (count < loop.size()))
            {
                ArrayList<String> list = it.next();
                
                for(String s : list)
                    writer.println(s);
                
                for(int i = 0; i < 10; i++)
                    writer.print(MATRIX_OFFLINE_SEPARATOR);
                
                writer.println();
                
                count++;
            }
            
            writer.close();
        } 
        
        catch (FileNotFoundException e) 
        {
            System.out.println("unable to save");
            return false;
        }
        
        return true;
    }
    
    /**
     * Adds the specified message into the MessageLoop
     * after the current element. Adds no elements if invalid chars found.
     * @param message Requested stream of characters to add after current.
     * @return true if no invalid characters found, false otherwise
     */
    static boolean addAfter(String message)
    {
        for(int i = 0; i < message.length(); i++)
        {
            if(!matrix.isValidCharacter(message.substring(i, i + 1)))
            {
                System.out.println("An unrecognized character" 
                                        +  "has been entered.");
                return false;
            }
        }
        
        if(loop.size() == 0)
        {
            for(int i = 0; i < message.length(); i++)
            {
                ArrayList<String> addition = new ArrayList<String>(
                        matrix.getDotMatrix(message.substring(i , i + 1)));

                loop.addAfter(addition);
                loop.forward();
            }
            
            return true;
        }
        
        for(int i = 0; i < message.length(); i++)
        {
            ArrayList<String> addition = new ArrayList<String>(
                    matrix.getDotMatrix(message.substring(i , i + 1)));

            loop.addAfter(addition);
        }
        
        return true;
    }
    
    /**
     * Adds the specified message into the MessageLoop
     * before the current element. Adds no elements if invalid chars found.
     * @param message Requested stream of characters to add before current.
     * @return true if no invalid characters found, false otherwise
     */
    static boolean addBefore(String message)
    {
        for(int i = 0; i < message.length(); i++)
        {
            if(!matrix.isValidCharacter(message.substring(i, i + 1)))
            {
                System.out.println("An unrecognized character" 
                                        +  "has been entered.");
                return false;
            }
        }
        
        if(loop.size() == 0)
        {
            for(int i = 0; i < message.length(); i++)
            {
                ArrayList<String> addition = new ArrayList<String>(
                        matrix.getDotMatrix(message.substring(i , i + 1)));

                loop.addBefore(addition);
                loop.back();
            }
            
            return true;
        }
        
        for(int i = 0; i < message.length(); i++)
        {
            ArrayList<String> addition = new ArrayList<String>(
                    matrix.getDotMatrix(message.substring(i , i + 1)));

            loop.addBefore(addition);
        }
        
        return true;
    }
    
    /**
     * Removes the current DotMatrix string in the loop.
     * Returns if no elements to remove.
     */
    static void removeCurrent()
    {
        if(loopIsEmpty())
        {
            System.out.println(NO_MESSAGES);
            return;
        }
        
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
    static boolean traverseLoop(int steps)
    {
        if(loopIsEmpty()) 
        {
            System.out.println(NO_MESSAGES);
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
    static boolean replaceCurrent(String replace)
    {
        if(loopIsEmpty())
        {
            System.out.println(NO_MESSAGES);
            return false;
        }
        
        if(!matrix.isValidCharacter(replace))
        {
            //TODO Print invalid
            return false;
        }
        
        loop.addAfter(new ArrayList<String>(matrix.getDotMatrix(replace)));
        loop.back();
        loop.removeCurrent(); 
        
        return true;
    }
    
    static void printSeparator(String separator, int times)
    {
        for(int i = 0; i < times; i++)
            System.out.print(separator);
        System.out.println();
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
        
        loop = new MessageLoop<ArrayList<String>>();
        matrix = new DotMatrix();
        matrix.loadAlphabets("alphabets.txt");
        
        boolean stop = false;
        Scanner scanner = new Scanner(System.in);
        
        while (!stop) 
        {
            System.out.print("enter command (? for help)> ");
            String input = scanner.nextLine();
            String remainder = null;
            
            //TODO Implement handling of invalid commands.
            if(input.length() > 0) 
            {
                char option = input.charAt(0);
                
                if(input.length() > 1) 
                {
                    remainder = input.substring(1).trim();
                }
                
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
                        saveData(remainder.trim());
                        break;
                        
                    case 'n':
                        boolean traversed = traverseLoop(1);
                        
                        if(traversed)
                            printCurrentContext();
                        
                        break;
                        
                    case 'x':
                        removeCurrent();
                        
                        if(!loopIsEmpty())
                            printCurrentContext();
                        
                        break;
                        
                    case 'c': 
                    	printCurrentContext();
                        break;
                        
                    case 'l':
                    	loadData(remainder);
                        break;
                        
                    case 'p':
                        boolean moved = traverseLoop(-1);
                        
                        if(moved)
                            printCurrentContext();
                        
                        break;
                        
                    case 'a':
                        boolean noInvalidChars = addAfter(remainder);
                        
                        if(noInvalidChars)
                            printCurrentContext();
                        
                        break;
                        
                    case 'r':
                        replaceCurrent(remainder.substring(0, 1));
                        
                    	if(loopIsEmpty())
                    	    System.out.println(NO_MESSAGES);
                    	
                    	else printCurrentContext();
                    	
                    	break;
                        
                    case 'd':
                    	displayLoop();
                        break;
                        
                    case 'j':
                        boolean stateChanged = traverseLoop(
                                            Integer.parseInt(remainder.trim()));
                        
                        if(stateChanged)
                            printCurrentContext();
                        
                        break;
                        
                    case 'i':
                        addBefore(remainder.trim());
                        printCurrentContext();
                        break;
                        
                    case 'q':
                        stop = true;
                        System.out.println("quit");
                        break;
                    
                    default:
                        System.out.println("invalid command");
                        main(args);
                }
            }
        }
        scanner.close();
    }
}
