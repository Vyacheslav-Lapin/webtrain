package lab.dao;

import lab.model.User;

import java.util.List;


public interface UserDao {
	
	void insert(User user);
	
	User select(int id);

	List<User> selectAll();
	
}
