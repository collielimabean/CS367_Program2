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
 * TODO More descriptive class documentation
 * This class provides an entry point for the user to operate on a DotMatrix.
 */
public class DisplayEditor
{
    /** A String containing the path to the alphabet for the DotMatrix */
    static String ALPHABET_PATH = "";
    
    /** A String containing the "no messages" display.*/
    static final String NO_MESSAGES = "no messages";
    
    /** A String containing the pattern to separate dot matrix chars
     *  when serializing the MessageLoop.
     */
    static final String MATRIX_OFFLINE_SEPARATOR = "#";
    
    /** A String containing the pattern to separate dot matrix chars
     *  when displaying the MessageLoop in the console.
     */
    static final String LOOP_SEPARATOR = "*";
    
    /** An integer specifying how many times to print the separator pattern. */
    static final int NUMBER_SEPARATORS = 10;
    
    private static final char[] ADDTL_INPUT_COMMANDS = {'s', 'l', 'a', 'i'
                                                            , 'r', 'j'};
    
    /** A Scanner that will process any input in DisplayEditor */
    private static Scanner scanner;
    
    /** A MessageLoop of DotMatrix (ArrayList<String>) characters. */
    private static MessageLoop<ArrayList<String>> loop;
    
    /** A DotMatrix object to verify characters and load the alphabet.*/
    private static DotMatrix matrix;
    
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
            {
                System.out.println(s);
            }

            System.out.println();
            
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
                System.out.println(NO_MESSAGES);
                return;
                
            case 1:
                printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
                
                List<String> list = loop.getCurrent();
                
                for(String s : list)
                    System.out.println(s);
                
                printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
                
                return;
                
            case 2:
                printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
                
                List<String> list1 = loop.getCurrent();
               
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
                    List<String> list3 = loop.getCurrent();
                    
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
        //Verify that every character in message is valid
        for(int i = 0; i < message.length(); i++)
        {
            if(!matrix.isValidCharacter(message.substring(i, i + 1)))
            {
                System.out.println("An unrecognized character" 
                                        +  "has been entered.");
                return false;
            }
        }
        
        for(int i = 0; i < message.length(); i++)
        {
            ArrayList<String> addition = new ArrayList<String>(
                    matrix.getDotMatrix(message.substring(i , i + 1)));

            loop.addAfter(addition);
            loop.forward();
        } //TODO FIX ME
        
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
                System.out.println("An unrecognized character " 
                                        +  "has been entered.");
                return false;
            }
        }
        
        for(int i = 0; i < message.length(); i++)
        {
            ArrayList<String> addition = new ArrayList<String>(
                    matrix.getDotMatrix(message.substring(i , i + 1)));
            
            loop.addBefore(addition);
            loop.back(); //TODO Fix me
        }
        
        loop.back();
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
        
        if(loopIsEmpty())
        {
            System.out.println(NO_MESSAGES);
            return;
        }
        
        else loop.forward();
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
     * This method also assumes that the input string has length 1.
     * @param replace A one character String to replace the current char.
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
            System.out.println("An unrecognized character " 
                    +  "has been entered.");
            return false;
        }
        
        //remove the current item
        loop.removeCurrent(); 
        
        //because removeCurrent advances, we need to move back
  //      loop.back();
        
        //TODO simplify into addBefore
        loop.addBefore(new ArrayList<String>(matrix.getDotMatrix(replace)));
        
        return true;
    }
    
    /**
     * Prints a specified separator a specified number of times.
     * e.g. printSeparator("$", 10) prints $$$$$$$$$$ and moves to a new line.
     * @param separator A String containing a pattern to repeat
     * @param times the number of times to repeat a pattern
     */
    static void printSeparator(String separator, int times)
    {
        for(int i = 0; i < times; i++)
            System.out.print(separator);
        System.out.println();
    }
    
    static String removeTrailingWhitespace(String edit)
    {
        String complete = "";
        int count;
        
        for(count = edit.length(); count > 0; count--)
            if(!Character.isWhitespace(edit.substring(count - 1, count).charAt(0)))
                break;
        
        complete = edit.substring(0, count);
        
        return complete;
    }
    
    static String[] splitCommand(String input)
    {
        if(input.length() == 1)
        {
            String[] pack = new String[2];
            pack[0] = input;
            pack[1] = "";
            
            return pack;
        }
        
        //find first whitespace
        int fws = 0;
        
        for(fws = 0; fws < input.length(); fws++)
            if(input.substring(fws, fws + 1).charAt(0) == ' ')
                break;
        
        //make two strings by substring, then input into string array
        String initial = input.substring(0, fws).trim();
        String data = removeTrailingWhitespace(input.substring(fws + 1));
        
        String[] pack = new String[2];
        pack[0] = initial;
        pack[1] = data;
        
        return pack;        
    }
    /**
     * 
     * @param input
     * @return true if input is a valid command that requires additional inputs
     * or if input does not require additional inputs (e.g. x); false otherwise
     */
    static boolean isValidAdditionalInputCommand(String input)
    {
        char option = input.charAt(0);
        
        if(input.length() > 1) 
        {
            boolean requiresSpace = false;
            
            for(char c : ADDTL_INPUT_COMMANDS)
            {
                if(option == c)
                {
                    requiresSpace = true;
                    break;
                }
            }
            
            if(!requiresSpace)
                return true;
            
            if(!Character.isWhitespace(input.charAt(1)))
                return false;
            
            String seq = input.substring(1).trim();
            
            if(seq.length() > 1 && option == 'r')
                return false;
            
            if(seq.length() < 1)
                return false;
            
            if(option == 'j')
            {
                try
                {
                    Integer.parseInt(seq);
                }
                
                catch (NumberFormatException e)
                {
                    return false;
                }
            }
        }
        
        else
            for(char c : ADDTL_INPUT_COMMANDS)
                if(option == c)
                    return false;
        
        return true;
    }
    
    /**
     * Entry point for this program.
     * @param args Contains Command-line arguments
     */
    public static void main(String[] args)
    {        
        //Check if valid number of command line arguments
        if(args.length > 2 || args.length == 1)
        {
            System.out.println("Invalid command-line arguments.");
            return;
        }
        
        else if(args.length == 0)
        {
            System.out.println("Enter the dot-matrix alphabets file:");
            
            scanner = new Scanner(System.in);
            
            ALPHABET_PATH = scanner.nextLine();
        }
        
        else
        {
            ALPHABET_PATH = args[1];
            
            File commandFile = new File(args[0]);
            
            if(!commandFile.exists() || !commandFile.canRead())
            {
                System.err.println("Problem with input file!");
                return;
            }
            
            try
            {
                scanner = new Scanner(commandFile);
            }
            
            catch(FileNotFoundException e)
            {
                System.err.println("Problem with input file!");
                return;
            }
        }

        loop = new MessageLoop<ArrayList<String>>();
        matrix = new DotMatrix();
        matrix.loadAlphabets(ALPHABET_PATH);
        
        boolean stop = false;
        
        while (!stop) 
        {
            System.out.print("enter command (? for help)> ");
            String input = scanner.nextLine();
            System.out.print(input + "\n");
            
            String[] commandData = splitCommand(input);
            char option = commandData[0].charAt(0);
            String remainder = commandData[1];
            
            if(input.length() > 0) 
            {
                if(!isValidAdditionalInputCommand(input))
                {
                    System.out.println("invalid command");
                    continue;
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
