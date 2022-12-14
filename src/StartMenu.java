import java.util.ArrayList;

public class StartMenu implements IMenu{


    @Override
    public void enter(Application application)
    {
        application.setCurrentUser(logIn(application));
        exit(application);
    }

    @Override
    public void exit(Application application)
    {
        application.menuStack.pop();
    }

    public User logIn(Application application) {
        String currentUsername;
        String currentPassword;

        //shortcut
        TextUI textUI = application.ui.asTextUI();
        FileIO fileIO = application.dataIO.asFileIO();

        do {
            currentUsername = textUI.getInputOnLine("Enter username: ");

            //Keeps prompting the user for a valid username.
        } while (currentUsername != null && currentUsername.trim().equalsIgnoreCase(""));
        //Trimming the given username for unnecessary spaces.
        currentUsername = currentUsername.trim();

        //If the username already existing. Prompt for a password to log in to existing account
        if (checkExistingUsername(currentUsername, fileIO)) {
            String input;
            currentPassword = textUI.getInputOnLine("Enter password: ");
            if (checkCorrectPassword(currentUsername, currentPassword, fileIO)) {
                //If the password matches the existing username, then you will be logged in.
                textUI.displayMessage("You have successfully logged in.");
                return new User(currentUsername, currentPassword);
            } else {
                while (!checkCorrectPassword(currentUsername, currentPassword, fileIO)) {
                    do {
                        textUI.displayMessage("|--"+ Colors.ANSI_RED +"Incorrect Password" + Colors.ANSI_RESET + "--|");
                        input = textUI.getInput("Try again(" + Colors.ANSI_CYAN + "T" + Colors.ANSI_RESET + ") or go back("+ Colors.ANSI_CYAN + "B" + Colors.ANSI_RESET +")");

                        //If the user chooses to go back.
                        if (input.trim().equalsIgnoreCase("B")) {
                            return logIn(application);

                            //if the user chooses to try again.
                        } else if (input.trim().equalsIgnoreCase("T")) {
                            currentPassword = textUI.getInputOnLine("Enter password: ");

                            //If password is correct, return the user.
                            if (checkCorrectPassword(currentUsername, currentPassword, fileIO)) {
                                return new User(currentUsername, currentPassword);
                            }
                        }


                    } while (!input.trim().equalsIgnoreCase("T") && !input.trim().equalsIgnoreCase("B"));
                }

            }
        } else if (!checkExistingUsername(currentUsername, fileIO)) {
            String input;
            textUI.displayMessage("We don't have any existing users by the name of " + currentUsername + ".");
            textUI.displayMessage("What would you like to do?");
            do {
                /*
                Giving the options to create a new user, or try to log in with another username.
                The following do-while-loop will only accept a 'T' or 'N' as response.
                 */
                input = textUI.getInput("Create new user(" + Colors.ANSI_CYAN + "N" + Colors.ANSI_RESET + ") or Try again(" + Colors.ANSI_CYAN + "T" + Colors.ANSI_RESET + ")");
                if (input.trim().equalsIgnoreCase("T")) {
                    logIn(application);
                } else if (input.trim().equalsIgnoreCase("N")) {
                    textUI.displayMessage("Great, let's create a new user!");
                    currentPassword = textUI.getInputOnLine("Choose a password: ");
                    textUI.displayMessage("You have successfully been logged in.");
                    User user = new User(currentUsername, currentPassword);

                    fileIO.writeNewUserData("src/Data/Userdata.csv",user);

                    return user;
                }
            } while (!input.trim().equalsIgnoreCase("N") && !input.trim().equalsIgnoreCase("T"));

        }

            //This should never happen.
            return new User("-1", "-1");
    }

    boolean checkExistingUsername(String username, FileIO fileIO){
        ArrayList<String> data = fileIO.readData("src/Data/Userdata.csv");

        //Loops through the whole list of userdata
        for (int i = 0; i < data.size(); i++){
            //Returns true if any of the usernames matches the given username.
            if(data.get(i).split(";")[0].equalsIgnoreCase(username)){
             return true;
            }
        }
        //If the method didn't find a match, it will return false.
        return false;
    }
    boolean checkCorrectPassword(String username, String password, FileIO fileIO){
       ArrayList<String> data = fileIO.readData("src/Data/Userdata.csv");

        //Loops through the whole list of userdata
        for (int i = 0; i < data.size(); i++){
            //Returns true if any of the passwords matches the given password.
            if (data.get(i).split(";")[0].equalsIgnoreCase(username)) {
                if (data.get(i).split(";")[1].equals(password)) {
                    return true;
                }
                return false;
            }
        }
        //If the method didn't find a match, it will return false.
        return false;
    }


}
