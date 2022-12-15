import java.util.ArrayList;

public class User {

    private final String username;
    private final String password;
    private final Portfolio portfolio;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.portfolio = new Portfolio(new ArrayList<>());
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
