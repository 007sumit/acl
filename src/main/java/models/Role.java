package models;

import java.util.ArrayList;

public class Role {
	private final String id;
    private final String label;
    private ArrayList<Permission> permissions;


	public Role(String id, String label, ArrayList<Permission> permissions) {
        this.id = id;
        this.label = label;
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
    
    // Get permissions associated with the Role
    public ArrayList<Permission> getPermissions() {
		return permissions;
	}
}
