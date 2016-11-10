package dao;

import exception.InvalidResourceException;
import models.Resource;
import util.DummyData;

public class ResourceDao {
	private static ResourceDao s_instance;

	// Singleton instance
	public static ResourceDao getInstance() {
        if (s_instance == null) {
            synchronized (ResourceDao.class) {
                if (s_instance == null) {
                    s_instance = new ResourceDao();
                }
            }
        }       
        return s_instance;
	}

	// protected constructor
	protected ResourceDao() {
		// DO nothing
	}
	
	public Resource getResource(String resourceId) throws InvalidResourceException {
		Resource resource = DummyData.getResource(resourceId);
		return resource;
	}
}
