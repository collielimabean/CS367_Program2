///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            DisplayEditor.java
// Files:            DblListNode.java, MessageLoop.java,
//                   MessageLoopIterator.java, EmptyLoopException.java
//                   UnrecognizedCharacterException.java, DisplayEditor.java
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
    
    /** A char array specifying commands that should be alone (by itself) */ 
    private static final char[] SINGLE_INPUT_COMMANDS = {'n', 'x', 'c', 'p'
                                                            , 'd', 'q', '?'};
    
    /** A char array specifying commands that should have additional inputs */
    private static final char[] ADDTL_INPUT_COMMANDS = {'s', 'l', 'a', 'i'
                                                           , 'r', 'j'};
    
    /** A Scanner that will process any input in DisplayEditor */
    private static Scanner scanner;
    
    /** A MessageLoop of DotMatrix (ArrayList<String>) characters. */
    private static MessageLoop<ArrayList<String>> loop;
    
    /** A DotMatrix object to verify characters and load the alphabet.*/
    private static DotMatrix matrix;
    
    /**
     * Displays every element in the MessageLoop.
     */
    static void displayLoop()
    {
        if(loop.size() <= 0)
        {
            System.out.println(NO_MESSAGES);
            return;
        }
        
        Iterator<ArrayList<String>> it = loop.iterator();
        
        int counter = 0;
        
        System.out.println();
        
        //Iterate over each element and print associated data
        while(it.hasNext() && counter < loop.size())
        {
            List<String> data = it.next();
            
            for(String s : data)
                System.out.println(s);
            System.out.println();
            
            counter++;
        }
    }
    
    /**
     * Displays the context of the current element.
     * [Current] for 1 element
     * [Current], [Next] for 2 elements
     * [Previous item], [Current], [Next] for >= 3 elements
     */
    static void printCurrentContext()
    {
        if(loop.size() == 0)
        {
            System.out.println(NO_MESSAGES);
            return;
        }
        
        int count = 0;
        
        //stop condition is either 3 or the loop size if loop has < 3 elements
        int stop = (loop.size() < 3) ? loop.size() : 3;
        
        //cases 1 and 2 require a separator line, but 3 requires a step back
        if(stop == 3)
            loop.back();
        else 
            printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
        
        //print out the data
        while(count < stop)
        {
            List<String> data = loop.getCurrent();
            
            for(String s : data)
                System.out.println(s);
            
            //prints out separator lines up to the last element
            //also prevents us from moving too far!
            if(count < stop - 1)
            {
                printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
                loop.forward();
            }
            
            count++;
        }
        
        //move back to current
        loop.back();
        
        if(stop == 1)
            printSeparator(LOOP_SEPARATOR, NUMBER_SEPARATORS);
        
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
                
                //if we hit this, then we know that we hit the
                //end of a DotMatrix character and can
                //process and add to the MessageLoop
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
        if(loop.size() <= 0)
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
                
                //write loop element data
                for(String s : list)
                    writer.println(s);
                
                //separator between DotMatrices printer
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
     * @throws UnrecognizedCharacterException if any character is not 
     * in the mapping as defined in the alphabets.txt
     */
    static void addAfter(String message)
    {
        //Verify that every character in message is valid
        for(int i = 0; i < message.length(); i++)
            if(!matrix.isValidCharacter(message.substring(i, i + 1)))
                throw new UnrecognizedCharacterException();
        
        //add each character to the MessageLoop
        for(int i = 0; i < message.length(); i++)
        {
            ArrayList<String> addition = new ArrayList<String>(
                    matrix.getDotMatrix(message.substring(i , i + 1)));

            loop.addAfter(addition);
            
            if(loop.size() != 0)
                loop.forward();
        }
    }
    
    /**
     * Adds the specified message into the MessageLoop
     * before the current element. Adds no elements if invalid chars found.
     * @param message Requested stream of characters to add before current.
     * @return true if no invalid characters found, false otherwise
     * @throws UnrecognizedCharacterException if any character is not
     * in the mapping as defined in the alphabets.txt
     */
    static void addBefore(String message)
    {
        //verify if each character in message is valid
        for(int i = 0; i < message.length(); i++)
            if(!matrix.isValidCharacter(message.substring(i, i + 1)))
                throw new UnrecognizedCharacterException();
        
        //add each character to the MessageLoop
        for(int i = 0; i < message.length(); i++)
        {
            ArrayList<String> addition = new ArrayList<String>(
                    matrix.getDotMatrix(message.substring(i , i + 1)));
            
            loop.addBefore(addition);
            
            if(loop.size() != 0)
                loop.back();
        }
    }
    
    /**
     * Removes the current DotMatrix string in the loop.
     * Returns if no elements to remove.
     */
    static void removeCurrent()
    {
        if(loop.size() <= 0)
        {
            System.out.println(NO_MESSAGES);
            return;
        }
        
        loop.removeCurrent();
        
        if(loop.size() <= 0)
        {
            System.out.println(NO_MESSAGES);
            return;
        }
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
        if(loop.size() <= 0) 
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
     * @throws UnrecognizedCharacterException if the specified replacement
     * character is not in the mapping as defined by alphabets.txt
     */
    static boolean replaceCurrent(String replace)
    {
        if(loop.size() <= 0)
        {
            System.out.println(NO_MESSAGES);
            return false;
        }
        
        if(!matrix.isValidCharacter(replace))
            throw new UnrecognizedCharacterException();
        
        //remove the current item
        loop.removeCurrent(); 
        loop.addBefore(new ArrayList<String>(matrix.getDotMatrix(replace)));
        
        //return to current
        //since removeCurrent() moves us forward
        loop.back();
        
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
    
    /**
     * Removes trailing whitespace from a String.
     * @param edit String to remove trailing whitespace
     * @return String with trailing whitespace removed
     */
    static String removeTrailingWhitespace(String edit)
    {
        int count;
        
        for(count = edit.length(); count > 0; count--)
        {
            //Gets the last character of the string
            char spaceCheck = edit.substring(count - 1, count).charAt(0);
            
            //exit loop if not whitespace (indicating we can substring @ count)
            if(!Character.isWhitespace(spaceCheck))
                break;
        }

        return edit.substring(0, count);
    }
    
    /**
     * Splits a String command into its two components. The first component
     * comprises of the command character (String length 1), and the second
     * component is any other remaining data.
     * @param input String command to split
     * @return A string array [0 = command] [1 = remaining data]
     */
    static String[] splitCommand(String input)
    {
        //if the length is one, we set the extra data field to an empty String
        if(input.length() == 1)
        {
            String[] pack = new String[2];
            pack[0] = input;
            pack[1] = "";
            
            return pack;
        }
        
        //first whitespace character indexer
        int fws = 0;
        
        //find first whitespace
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
     * Validates all commands, including single and add'l input commands.
     * @param input Commmand to be checked
     * @return true if valid command, false otherwise
     */
    static boolean isValidCommand(String input)
    {
        char command = input.charAt(0);
        
        //verify if it is a single input command 
        for(char c : SINGLE_INPUT_COMMANDS)
        {
            if(command == c)
            {
                if(input.length() > 1)
                    return false;
                
                else return true;                            
            }
        }
        
        //if it is an add'l input command, validate
        for(char c : ADDTL_INPUT_COMMANDS)
            if(command == c)
                return isValidAdditionalInputCommand(input);
        
        return false;
    }
    
    /**
     * Validates commands that require additional input, e.g. jump 'j'
     * @param input Command to be checked
     * @return true if input is a valid command that requires additional inputs
     * or if input does not require additional inputs (e.g. x); false otherwise
     */
    private static boolean isValidAdditionalInputCommand(String input)
    {
        //grab the command
        char option = input.charAt(0);
        
        if(input.length() > 1)
        {
            //all add'l command require whitespace after initial char
            if(!Character.isWhitespace(input.charAt(1)))
                return false;
            
            //trim the add'l data
            String seq = input.substring(1).trim();
            
            //The r command MUST have an add'l data length of 1
            //corresponding to one character
            if(seq.length() > 1 && option == 'r')
                return false;
            
            //if there is no add'l data -> not valid
            if(seq.length() < 1)
                return false;
            
            //attempt to parse add'l data if jump command
            //if cannot parse, then invalid
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
        
        else return false;
        
        //if all tests are passed
        return true;
    }
    
    /**
     * Entry point for this program.
     * @param args Contains Command-line arguments
     */
    public static void main(String[] args)
    {        
        boolean userInput = (args.length == 0) ? true : false;
        
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
            
            //if the user manually inputs, we don't want
            //to repeat what they just input
            if(!userInput)
                System.out.print(input + "\n");
            
            String[] commandData = splitCommand(input);
            char option = commandData[0].charAt(0);
            String remainder = commandData[1];
            
            if(input.length() > 0) 
            {
                if(!isValidCommand(input))
                {
                    System.out.println("invalid command");
                    continue;
                }
                
                try
                {
                    switch(option)
                    {
                        
                        /** Prints out available commands*/
                        case '?':
                            System.out.println("s (save)" 
                                                + "    l (load)" 
                                                + "       d (display)");
                            
                            System.out.println("n (next)" 
                                                + "    p (previous)" 
                                                + "   j (jump)");
                            
                            System.out.println("x (delete)" 
                                                + "  a (add after)" 
                                                + "  i (insert before)");
                            
                            System.out.println("c (context)" 
                                                + " r (replace)" 
                                                + "    q (quit)");
                            break;
                            
                        /** Serializes/Saves the MessageLoop into the 
                         *  specified file path.
                         * */    
                        case 's':
                            saveData(remainder.trim());
                            break;
                            
                        /** Moves the loop forward by one */
                        case 'n':
                            boolean traversed = traverseLoop(1);
                            
                            if(traversed)
                                printCurrentContext();
                            break;
                        
                        /** Delete the current item in the loop.*/
                        case 'x':
                            removeCurrent();
                            
                            if(loop.size() > 0)
                                printCurrentContext();
                            break;
                        
                        /** Print the context of current
                         * @see printCurrentContext method
                         */
                        case 'c':
                            printCurrentContext();
                            break;
                        
                        /** Load serialized data into the MessageLoop*/
                        case 'l':
                            loadData(remainder);
                            break;
                        
                        /** Move the loop back by one*/
                        case 'p':
                            boolean moved = traverseLoop(-1);
                            
                            if(moved)
                                printCurrentContext();
                            break;
                        
                        /** Add specified chars after the current item */
                        case 'a':
                            addAfter(remainder);
                            printCurrentContext();
                            
                            break;
                        
                        /** Replace the current char with specified char*/
                        case 'r':
                            replaceCurrent(remainder.substring(0, 1));
                            
                            if(loop.size() <= 0)
                                System.out.println(NO_MESSAGES);
                            
                            else printCurrentContext();
                            
                            break;
                        
                        /** Display the entire loop, starting with current */
                        case 'd':
                            displayLoop();
                            break;
                        
                        /** 
                         * Jump/traverse the list the specified 
                         * number of times
                         */
                        case 'j':
                            boolean stateChanged = traverseLoop(
                                               Integer.parseInt(
                                                       remainder.trim()));
                            
                            if(stateChanged)
                                printCurrentContext();
                            break;
                        
                        /** Insert the specified chars before the current */
                        case 'i':
                            addBefore(remainder.trim());
                            printCurrentContext();
                            break;
                        
                        /** Exit the program */
                        case 'q':
                            stop = true;
                            System.out.println("quit");
                            break;
                        
                        /** Invalid command handling*/
                        default:
                            System.out.println("invalid command");
                            main(args);
                    }
                }
                
                catch (UnrecognizedCharacterException e)
                {
                    System.out.println("An unrecognized character " 
                            +  "has been entered.");
                }
            }
        }
        
        scanner.close();
    }
}