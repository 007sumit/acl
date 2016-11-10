package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import exception.InvalidResourceException;
import exception.InvalidUserException;
import models.*;

public class DummyData {
	
	// Creating only MAPs which can be used to solve the given query (has_access or not)
	// To reduce computation (eq. to db joins), creating the merge (de-normalized) MAP (userRoles)
	// IN case of real normalized DB, query should fetch user->roles->permissions using multiple JOINs
	private static final Map<String, User> userMap = new HashMap<String, User>();
	private static final Map<String, ArrayList<Role>> userRolesMap = new HashMap<String, ArrayList<Role>>();
	private static final Map<String, Resource> resourceMap = new HashMap<String, Resource>();
	
	// Load dummy data
	static {
		loadData();
	}
	
	/**
	 * Function to populate/load dummy data into static MAPs
	 */
	private static void loadData(){
		userMap.put("1", new User("1", "Joe"));
		userMap.put("2", new User("2", "Tom"));
		
		resourceMap.put("1", new Resource("1", "Email"));
		resourceMap.put("2", new Resource("2", "Address"));
		resourceMap.put("3", new Resource("3", "Account Details"));
		
		Permission p1 = new Permission("1", "Email.Read", "1", ActionType.READ);
		Permission p2 = new Permission("2", "Email.Write", "1", ActionType.WRITE);
		Permission p3 = new Permission("3", "Address.Read", "2", ActionType.READ);
		Permission p4 = new Permission("4", "Address.Write", "2", ActionType.WRITE);
		Permission p5 = new Permission("5", "AccountDetails.Read", "3", ActionType.READ);
		Permission p6 = new Permission("6", "AccountDetails.Write", "3", ActionType.WRITE);
		
		Role r1 = new Role("1", "Profile Read Role", new ArrayList<Permission>(){{add(p1); add(p3); }} );
		Role r2 = new Role("2", "Account Details Read Role", new ArrayList<Permission>(){{add(p5);}});
		Role r3 = new Role("3", "Profile Edit Role", new ArrayList<Permission>(){{add(p2); add(p4); }});
		Role r4 = new Role("4", "Admin Role", new ArrayList<Permission>(){{add(p2); add(p4); add(p6); }});
		
		userRolesMap.put("1", new ArrayList<Role>(){{add(r1); add(r2);}});
		userRolesMap.put("2", new ArrayList<Role>(){{add(r3);}});
	}
	
	/**
	 * Fetch User object from given id
	 * 
	 * @param userId	id of the user
	 * @return			User object (if present)
	 * @throws InvalidUserException		in case no User present corresponding to passed id
	 */
	public static User getUser(String userId) throws InvalidUserException{
		if(! userMap.containsKey(userId)){
			throw new InvalidUserException("Invalid User Id");
		}
		return userMap.get(userId);
	}
	
	/**
	 * Fetch Resource object from given id
	 * 
	 * @param userId	id of the user
	 * @return			Resource object (if present)
	 * @throws InvalidResourceException		in case no Resource present corresponding to passed id
	 */
	public static Resource getResource(String userId) throws InvalidResourceException{
		if(! resourceMap.containsKey(userId)){
			throw new InvalidResourceException("Invalid Resource Id");
		}
		return resourceMap.get(userId);
	}
	
	/**
	 * Fetch all Roles corresponding to given UserId
	 * 
	 * @param userId	id of the user
	 * @return			ArrayList of all roles
	 */
	public static ArrayList<Role> getRoles(String userId){
		if(! userRolesMap.containsKey(userId)){
			return new ArrayList<Role>(); // Empty list ==> No Roles
		}
		return userRolesMap.get(userId);
	}

}
