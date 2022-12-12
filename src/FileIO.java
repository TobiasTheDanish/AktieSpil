import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements IDataIO
{
    private Scanner scanner;
    private FileWriter writer;
    @Override
    public ArrayList<String> readData(String path)
    {
        ArrayList<String> data = new ArrayList<>();
        scanner = new Scanner(path);

        while (scanner.hasNextLine())
        {
            data.add(scanner.nextLine());
        }

        scanner.close();

        return data;
    }

    @Override
    //When a new user is created, call this function.
    //This function adds user data to the end of the file a given path.
    public void writeNewUserData(String path, ArrayList<User> data)
    {
        //Creates a file object at the given path
        File file = new File(path);
        try
        {
            //Accesses the file with the FileWriter class to write data to that file
            writer = new FileWriter(file);
            for (User user : data)
            {
                String s = user.getUsername() + user.getPassword();
                writer.write(s);
            }
            //Closes FileWriter, so it's ready to be used somewhere else
            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserData(String path, User user)
    {
        ArrayList<String> data = readData(path);
        for (String s : data)
        {
            if (s.split(";")[0].equalsIgnoreCase(user.getUsername()))
            {
                data.set(data.indexOf(s), user.getUsername() + user.getPassword());
            }
        }
        //Creates a file object at the given path
        File file = new File(path);
        try
        {
            //Accesses the file with the FileWriter class to write data to that file
            writer = new FileWriter(file);
            for (String s : data)
            {
                writer.write(s);
            }
            //Closes FileWriter, so it's ready to be used somewhere else
            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeGameData(String path, ArrayList<String> data)
    {

    }
}
