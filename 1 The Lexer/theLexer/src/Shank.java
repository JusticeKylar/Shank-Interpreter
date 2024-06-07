import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class Shank {
    public static void main(String[] args) {
        //Get input from the commandline
        //handle if number of inputs on the command line is more than 1
        if(args.length != 1) {
            System.out.println("Incorrect number of inputs. Please put in 1 file name.");
            System.exit(1);
        }

        //get filename from commandline
        Path myPath = Paths.get(args[0]);
        //System.out.println("myPath = " + myPath + "\n");                             //debugging tool
        List<String> lines = Collections.singletonList("holder");               //start lines with a value
        try {
            lines = Files.readAllLines(myPath, StandardCharsets.UTF_8);
        } catch(IOException ex){ //if it doesn't open print out the error
            ex.printStackTrace();
            System.out.format("Error in reading the file %s%nExiting with code: 2", ex);
            System.exit(2);
        }

        Lexer lexerObject = new Lexer();
        //lexerObject.displayMessage();

        //call the lex function equal to a number of times that the file has lines
        for (String value : lines){
            //System.out.println(value);  //debug method to check to see that value is the lines
            lexerObject.lex(value);     //call the lexer
        }

        //System.out.println(args[0]);
        //System.out.println("Hey, we got it to work from the command line!");
        System.exit(0);
    }
}