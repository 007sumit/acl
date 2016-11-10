package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.RoleDao;
import models.Permission;
import util.ActionType;
import models.User;
import models.Role;

public class RoleManager {
	private static RoleManager s_instance;
	private final RoleDao roleDaoInstance;

	// Fetch Singleton Instance
	public static RoleManager getInstance() {
        if (s_instance == null) {
            synchronized (RoleManager.class) {
                if (s_instance == null) {
                    s_instance = new RoleManager();
                }
            }
        }       
        return s_instance;
   }
   
	// protected constructor
   protected RoleManager() {
	   roleDaoInstance = RoleDao.getInstance();
   }
   
   
   /**
    * Fetch Resource MAP containing all the resources and its actionTypes
    * for which user has access to.
	* @param user	User for which resources is to be fetched
	* @return		MAP containing allowed resource and actionType
	*/
   public Map<String, ActionType> fetchResourceMap(User user){
	   Map<String, ActionType> resourceMap = new HashMap<String, ActionType>();
	   
	   ArrayList<Role> roles = fetchRoles(user);
	   
	   populateResourceMap(roles, resourceMap);
	   
	   return resourceMap;
   }
   
   
   /**
    * Fetch all roles corresponding to User
	* @param user	User for which roles is to be fetched
	* @return		Array containing all the associated Roles
	*/
   private ArrayList<Role> fetchRoles(User user){
	   return roleDaoInstance.fetchRoles(user);
   }
   
   /**
    * Populate Resource MAP by iterating over the roles array
    * Rule 1: To avoid fetching all the resource, comparison is done only by resourceId
    * Rule 2: In case of similar Resource, give preference to high AccessType
    *  
	* @param roles			Array of all associated Roles
	* @param resourceMap	MAP of unique resources and its actionType
	*/
   private void populateResourceMap(ArrayList<Role> roles, Map<String, ActionType> resourceMap){
	   for(Role role : roles){
		   for(Permission permission : role.getPermissions()){
			   if(resourceMap.containsKey(permission.getResourceId() )){
				   ActionType action = resourceMap.get(permission.getResourceId());
				   // If already high access  is given to the resource by some another permission
				   // then no need to include low level access for that resource in map
				   if(permission.getAccess().getValue() <= action.getValue()){
					   continue;
				   }
			   }
			   resourceMap.put(permission.getResourceId(), permission.getAccess());
		   }
	   }
   }

}
