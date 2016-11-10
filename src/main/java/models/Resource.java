package models;

public class Resource {
	private final String id;
    private final String label;

    public Resource(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return label;
    }
}
