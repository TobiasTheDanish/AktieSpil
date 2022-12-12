import java.util.ArrayList;

public interface IDataIO
{
    ArrayList<String> readData(String path);
    ArrayList<IEquity> readEquityData(String path);
    void writeNewUserData(String path, User user);
    void updateUserData(String path, User user);
    void writeGameData(String path, ArrayList<String> data);
}
