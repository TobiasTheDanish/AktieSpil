import java.io.File;
import java.io.FileNotFoundException;
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
        File file = new File(path);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Ignores the header in the file.
        scanner.nextLine();
        while (scanner.hasNextLine())
        {
            data.add(scanner.nextLine());
        }

        scanner.close();

        return data;
    }

    @Override
    //When a new user is created, call this function.
    //This function adds user data to the end of the file at given path.
    public void writeNewUserData(String path, User user)
    {
        //Reads all the existing data and puts it into an ArrayList<>.
        ArrayList<String> data = readData(path);

        //Creates a file object at the given path
        File file = new File(path);
        try
        {
            //Accesses the file with the FileWriter class to write data to that file
            writer = new FileWriter(file);
            //Rewrites the header in the file
            writer.write("username;password\n");
                data.add(user.getUsername() + ";" + user.getPassword());
                for (String s : data){
                    writer.write(s + "\n");
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
