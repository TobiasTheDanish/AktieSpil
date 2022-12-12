public interface IUI {
    String getInput(String msg);
    String getInputOnLine(String msg);
    void displayMessage(String msg);
    void displayMessageOnLine(String msg);
   default TextUI asTextUI(){
       return (TextUI) this;
   }
}
