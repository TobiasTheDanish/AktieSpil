import java.util.ArrayList;

public interface IDataIO
{
    ArrayList<String> readData(String path);
    void writeNewUserData(String path, ArrayList<User> data);
    void updateUserData(String path, User user);
    void writeGameData(String path, ArrayList<String> data);
}
