package service;

import java.util.Map;

import dao.ResourceDao;
import dao.UserDao;
import exception.InvalidResourceException;
import exception.InvalidUserException;
import models.Resource;
import models.User;
import util.ActionType;

public class AccessControlManager {

	// Singleton instances of various required DAOs
	private final UserDao userDaoInstance;
	private final ResourceDao resourceDaoInstance;
	private final RoleManager roleManager;
	
	private User user;
	private Resource requestedResource;
	private ActionType requestedAction;
	
	
    // Builder for building AccessControlManager
    public static class AccessControlBuilder {
    	private String m_userId;
    	private String m_resourceId;
    	private Integer m_actionTypeValue;
        
        public AccessControlBuilder() {
            
        }
        
        public AccessControlBuilder withUserId(String userId){
        	this.m_userId = userId;
        	return this;
        }
        
        public AccessControlBuilder withResourceId(String resourceId){
        	this.m_resourceId = resourceId;
        	return this;
        }
        
        public AccessControlBuilder withActionType(String actionTypeValue){
        	this.m_actionTypeValue = Integer.valueOf(actionTypeValue);
        	return this;
        }

        public AccessControlManager build() throws InvalidUserException, InvalidResourceException {
            return new AccessControlManager(this);
        }
    }
    
    /**
     * Constructor for AccessControl Manager
     */
    protected AccessControlManager(AccessControlBuilder builder) throws InvalidUserException, InvalidResourceException{
    	userDaoInstance = UserDao.getInstance();
    	resourceDaoInstance = ResourceDao.getInstance();
    	roleManager = RoleManager.getInstance();
    	
    	initializeAttributes(builder);
		 
	}
    
    /**
     * Initialize Attributes (User, Resource and ActionType)
     * IN practical scenario, it will end up calling the db in service chain
     * @param builder		Builder corresponding to AccessControlManager
     * @throws InvalidUserException		if User not found by given user_id
     * @throws InvalidResourceException	if Resource not found by given resource_id
     */
    private void initializeAttributes(AccessControlBuilder builder) throws InvalidUserException, InvalidResourceException{
    	user = userDaoInstance.getUser(builder.m_userId);
    	requestedResource = resourceDaoInstance.getResource(builder.m_resourceId);
    	requestedAction = ActionType.fromValue(builder.m_actionTypeValue);
    }
    
    /**
     * Function to get whether provided User has access to requested Resource
     * with the requested actionType (Read, Write, etc.)
     * 
     * TO-DO: Handle priority of Access Types
     *
     * @return	true if access allowed, else false
     */
    public boolean hasAccess(){
    	// Fetch complete Resource map corresponding to User roles
    	Map<String, ActionType> resourceMap = roleManager.fetchResourceMap(user);
    	
    	// check for exact access type of resource
    	// TO-DO: Can extend it to take priority into consideration as well
    	// Example: If Resource with 'W' access is present, then return 'true' for
    	// all accessTypes having priority less than 'W' (i.e. Read access)
    	if(resourceMap.containsKey(requestedResource.getId()) &&
    			resourceMap.get(requestedResource.getId()) == requestedAction ){
    		return true;
    	}
    	return false;
    }
    
}
