package lab.dao.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class AbstractJpaDao {

	protected EntityManagerFactory emf;

	public AbstractJpaDao() {
		super();
	}

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

}