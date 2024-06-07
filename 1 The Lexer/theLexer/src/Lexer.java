public class Lexer {
    //a function that calls out the name of the class. A debugging tool
    public void displayMessage(){
        System.out.println("Called the Lexer");
    }
    //creates a new token and calls a generic function in Token.java

    //Given a string the lex function will
    // use a state machine to iterate over the string and create the appropriate tokens
    //returns nothing
    public void lex(String lineInput){
        //System.out.println("Called the Lex function in the Lexer class");     //debug tool
        //System.out.println("lex  called. lineInput = " + lineInput);          //debug tool
        Token tokenObject = new Token(); //create a new token to operate on
        for (int i = 0; i < lineInput.length(); i++){ //iterate through the string
            //System.out.print(lineInput.charAt(i) + " ");                      //debug tool
            //initialize some variables for legibility
            boolean isLetter = Character.isLetter(lineInput.charAt(i));
            boolean isNumber = Character.isDigit(lineInput.charAt(i));

            //check to see if i is a valid character
            //if not a valid character dump the current token, output the error message, and exit with status 3
            if ( !(    isNumber
                    || isLetter
                    || lineInput.charAt(i) == '.'
                    || lineInput.charAt(i) == ' ')){
                                tokenObject.printToken();
                                System.out.println("Invalid character in input file: " + lineInput.charAt(i));
                                System.out.println("Exiting with exit value: 3");
                                System.exit(3);
            }

            switch (tokenObject.getState()) {
                case WORD: //if we are currently in a word
                    //if the next value is a digit or a number, we append it to the token
                    if (isLetter || isNumber){
                        tokenObject.addToToken(lineInput.charAt(i));
                    } else {
                        //if anything else, make a new token
                        tokenObject.printToken();
                        tokenObject.newToken(lineInput.charAt(i));
                        tokenObject.setState(Token.tokenType.ENDOFLINE);
                    }
                    break;
                case NUMBER: //if we are currently in a number
                    //if the next value is a number or a period, we append it to the token
                    if (isNumber || lineInput.charAt(i) == '.'){
                        tokenObject.addToToken(lineInput.charAt(i));
                    } else {
                        //anything else, we make a new token
                        tokenObject.printToken();
                        tokenObject.newToken(lineInput.charAt(i));
                        tokenObject.setState(Token.tokenType.ENDOFLINE);
                    }
                    break;
                case ENDOFLINE: //if the last value was a space or if we are starting over with a new token
                    if (isLetter){
                        //if it is a word, make it a token
                        tokenObject.newToken(lineInput.charAt(i));
                        tokenObject.setState(Token.tokenType.WORD);
                    } else if (isNumber || lineInput.charAt(i) == '.'){
                        //if it is a number or a '.', make it a token
                        tokenObject.newToken(lineInput.charAt(i));
                        tokenObject.setState(Token.tokenType.NUMBER);
                    }
                    break;
            }
        }
        //System.out.println();                                                 //debug tool
        if (tokenObject.value != null) { //print the last line if the value exists
            tokenObject.printToken();
            System.out.println("ENDOFLINE");
        }
    }

    //some function to print the tokens
}
