import java.util.Scanner;

public class DisplayEditor
{
    public static void main(String[] args)
    {
        //TODO Create empty loop of characters
        
        
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
