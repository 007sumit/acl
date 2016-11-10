package dao;

import exception.InvalidUserException;
import models.User;
import util.DummyData;

public class UserDao {
   private static UserDao s_instance;

	// Singleton instance
   public static UserDao getInstance() {
        if (s_instance == null) {
            synchronized (UserDao.class) {
                if (s_instance == null) {
                    s_instance = new UserDao();
                }
            }
        }       
        return s_instance;
   }
   
   // protected constructor
   protected UserDao() {
       // DO nothing
   }

	public User getUser(String userId) throws InvalidUserException {
		User user = DummyData.getUser(userId);
		return user;
	}

}
