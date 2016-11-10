package models;

import util.ActionType;

public class Permission {
	private final String id;
    private final String label;
    private final String resourceId;
	private final ActionType access;

    public ActionType getAccess() {
		return access;
	}

	public Permission(String id, String label, String resourceId, ActionType access) {
        this.id = id;
        this.label = label;
        this.resourceId = resourceId;
        this.access = access;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
    
    public String getResourceId() {
		return resourceId;
	}
}
