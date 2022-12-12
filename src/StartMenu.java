public class StartMenu implements IMenu{
    private String currentUsername;
    private String currentPassword;

    public User logIn(){


        return new User(currentUsername, currentPassword);
    }



    @Override
    public void enter() {

    }

    @Override
    public void enter(Application application)
    {

    }

    @Override
    public void exit(Application application)
    {

    }
}
