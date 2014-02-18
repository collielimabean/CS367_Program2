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

import java.util.Scanner;

public class DisplayEditor
{
    public static void main(String[] args)
    {
        MessageLoop<String> loop = new MessageLoop<String>();
        
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
                        break;
                        
                    case 'n':
                        break;
                        
                    case 'x':
                        break;
                        
                    case 'c':
                        break;
                        
                    case 'l':
                        break;
                        
                    case 'p':
                        break;
                        
                    case 'a':
                        break;
                        
                    case 'r':
                        break;
                        
                    case 'd':
                        break;
                        
                    case 'j':
                        break;
                        
                    case 'i':
                        break;
                        
                    case 'q':
                        stop = true;
                        System.out.println("Quitting...");
                        break;
                    
                    default:
                        System.out.println("Invalid command!");
                        break;
                }
            }
        }
        
        scanner.close();
    }
}
