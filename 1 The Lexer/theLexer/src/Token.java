public class Token {
    //a function that calls out the name of the class. A debugging tool
    public void displayMessage(){
        System.out.println("Called the Token");
    }

    //set up enum to define what types of inputs. Words, numbers, and blank space characters
    enum tokenType {WORD, NUMBER, ENDOFLINE}
    private tokenType previousInput = tokenType.ENDOFLINE;
    String value;

    public tokenType getState(){
        return previousInput;
    }
    public void setState(tokenType state){
        previousInput = state;
    }
    public void newToken(char input){
        //System.out.println("Called newToken");        //debug tool
        value = "" + input;
    }

    public void printToken(){
        System.out.print(previousInput + "(" + value + ") ");
    }

    public void addToToken(char input){
        //System.out.println("Called addToToken");        //debug tool
        value = value + input;
    }

}
