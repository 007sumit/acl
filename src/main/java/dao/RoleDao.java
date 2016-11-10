package dao;

import models.User;
import util.DummyData;

import java.util.ArrayList;

import models.Role;

public class RoleDao {

	private static RoleDao s_instance;

	// Singleton instance
	public static RoleDao getInstance() {
        if (s_instance == null) {
            synchronized (UserDao.class) {
                if (s_instance == null) {
                    s_instance = new RoleDao();
                }
            }
        }       
        return s_instance;
	}
   
	// protected constructor
	protected RoleDao() {
       // Do Nothing
	}
	
	public ArrayList<Role> fetchRoles(User user) {
		return DummyData.getRoles(user.getId());
	}

}
