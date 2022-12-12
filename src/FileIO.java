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

        try
        {
            scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {
                data.add(scanner.nextLine());
            }

            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        return data;
    }

    @Override
    public ArrayList<IEquity> readEquityData(String path)
    {
        ArrayList<IEquity> equities = new ArrayList<>();
        File file = new File(path);

        try
        {
            scanner = new Scanner(file);

            //Ignores header text
            scanner.nextLine();

            while (scanner.hasNextLine())
            {
                String data = scanner.nextLine();

                String[] dataSplit = data.split(";");

                String name = dataSplit[1].trim();
                float price = Float.parseFloat(dataSplit[2].trim());
                Range range = new Range(Float.parseFloat(dataSplit[3].split(",")[0].trim()), Float.parseFloat(dataSplit[3].split(",")[1].trim()));
                float riskOfBankruptcy = Float.parseFloat(dataSplit[4].trim());

                IEquity equity;
                switch (dataSplit[0].trim())
                {
                    case "PennyStock" ->
                    {
                        equity = new PennyStock(range, riskOfBankruptcy, name, price);
                        equities.add(equity);
                    }
                    case "SmallCapStock" ->
                    {
                        equity = new SmallCapStock(range, riskOfBankruptcy, name, price);
                        equities.add(equity);
                    }
                    case "MediumCapStock" ->
                    {
                        equity = new MediumCapStock(range, riskOfBankruptcy, name, price);
                        equities.add(equity);
                    }
                    case "LargeCapStock" ->
                    {
                        equity = new LargeCapStock(range, riskOfBankruptcy, name, price);
                        equities.add(equity);
                    }
                    default ->
                    {
                        System.err.println("This should never happen!");
                        throw new RuntimeException("Error when loading stock data! A stock type is not represented in switch statement.");
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        return equities;
    }

    //When a new user is created, call this function.
    //This function adds user data to the end of the file a given path.
    @Override
    public void writeNewUserData(String path, User user)
    {
        //Creates a file object at the given path
        File file = new File(path);
        try
        {
            //Accesses the file with the FileWriter class to write data to that file
            writer = new FileWriter(file);

            String s = user.getUsername() + user.getPassword();
            writer.write(s);

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
            writer.write("username;password");
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
