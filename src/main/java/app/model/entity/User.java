package app.model.entity;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;

    public enum ROLE {
        USER, ADMIN
    }

    private ROLE role;
    private Iterable<Check> checks;
    private Iterable<Order> orders;

    public User(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public Iterable<Check> getChecks() {
        return checks;
    }

    public void setChecks(Iterable<Check> checks) {
        this.checks = checks;
    }

    public Iterable<Order> getOrders() {
        return orders;
    }

    public void setOrders(Iterable<Order> orders) {
        this.orders = orders;
    }
}
