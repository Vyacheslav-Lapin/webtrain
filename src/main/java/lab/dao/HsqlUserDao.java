/**
 * 
 */
package lab.dao;

import lab.model.SimpleUser;
import lab.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author it.vaclav.kiev.ua
 * 
 */

public class HsqlUserDao extends JdbcDaoSupport implements UserDao {

	private static Log log = LogFactory.getLog(HsqlUserDao.class); 

	@Override
	public void insert(User user) {

		if (user != null) {
			log.debug( "Processing user: " + user);
			this.getJdbcTemplate().update(
					"insert into user (firstname, lastname) values (?, ?)",
					user.getFirstName(), user.getLastName());

		} else {
			log.debug("Domain user is null!");
		}
	}

	@Override
	public User select(int id) {

		User user = null;

		if (id > 0) {
			user = this.getJdbcTemplate().queryForObject(
					"select id, firstname, lastname from user where id = ?",
					new Object[] { id }, new UserMapper());
		}
		log.debug("Receidved user: " + user);
		
		return user;
	}

	@Override
	public List<User> selectAll() {
		return this.getJdbcTemplate().query(
				"select id, firstname, lastname from user"
				, new UserMapper());
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new SimpleUser();
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			return user;
		}
	}
}
