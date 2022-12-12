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

        do {
            currentUsername = textUI.getInputOnLine("Enter username: ");

            //Keeps prompting the user for a valid username.
        } while (currentUsername != null && currentUsername.trim().equalsIgnoreCase(""));
        //Trimming the given username for unnecessary spaces.
        currentUsername = currentUsername.trim();

        //If the username already existing. Prompt for a password to log in to existing account
        if (checkExistingUsername(currentUsername)) {
            String input;
            currentPassword = textUI.getInputOnLine("Enter password: ");
            if (checkCorrectPassword(currentPassword)) {
                //If the password matches the existing username, then you will be logged in.
                textUI.displayMessage("You have successfully logged in.");
                return new User(currentUsername, currentPassword);
            } else {
                while (!checkCorrectPassword(currentPassword)) {
                    do {
                        textUI.displayMessage("|-- Incorrect Password --|");
                        input = textUI.getInput("Try again(T) or go back(B)");

                        //If the user chooses to go back.
                        if (input.trim().equalsIgnoreCase("B")) {
                            return logIn(application);

                            //if the user chooses to try again.
                        } else if (input.trim().equalsIgnoreCase("T")) {
                            currentPassword = textUI.getInputOnLine("Enter password: ");

                            //If password is correct, return the user.
                            if (checkCorrectPassword(currentPassword)) {
                                return new User(currentUsername, currentPassword);
                            }
                        }


                    } while (!input.trim().equalsIgnoreCase("T") && !input.trim().equalsIgnoreCase("B"));
                }

            }
        } else if (!checkExistingUsername(currentUsername)) {
            String input;
            textUI.displayMessage("We don't have any existing users by the name of " + currentUsername + ".");
            textUI.displayMessage("What would you like to do?");
            do {
                /*
                Giving the options to create a new user, or try to log in with another username.
                The following do-while-loop will only accept a 'T' or 'N' as response.
                 */
                input = textUI.getInput("Create new user(N) or Try again(T)");
                if (input.trim().equalsIgnoreCase("T")) {
                    logIn(application);
                } else if (input.trim().equalsIgnoreCase("N")) {
                    textUI.displayMessage("Great, let's create a new user!");
                    currentPassword = textUI.getInputOnLine("Choose a password: ");
                    textUI.displayMessage("You have successfully been logged in.");
                    User user = new User(currentUsername, currentPassword);

                    //ToDO add this new user to Userdata.csv

                    return user;
                }
            } while (!input.trim().equalsIgnoreCase("N") && !input.trim().equalsIgnoreCase("T"));

        }

            //This should never happen.
            return new User("-1", "-1");
    }

    boolean checkExistingUsername(String username){
        //check csv file for existing username
        return false;
    }
    boolean checkCorrectPassword(String password){
        //check csv file for existing password
        return false;
    }


}
