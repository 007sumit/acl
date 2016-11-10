package models;

public class User {
	private final String id;
    private final String userName;

    // Constructor
    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
