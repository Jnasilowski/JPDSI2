package com.jsf.dao;
import java.util.List;
import java.util.Map;
import com.jsf.entities.Car;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
@Stateless
public class CarDAO {
	private final static String UNIT_NAME = "jsfcourse-carentPU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Car car) {
		em.persist(car);
	}

	public Car merge(Car car) {
		return em.merge(car);
	}

	public void remove(Car car) {
		em.remove(em.merge(car));
	}

	public Car find(Object id) {
		return em.find(Car.class, id);
	}
	public int getCarTotalCount() {
		List<Car> list = null;
		
	
		Query query = em.createQuery("select c from Car c");
		
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list.size();
		
	}
	public List<Car> getFullList() {
		
		List<Car> list = null;
		
		
		Query query = em.createQuery("select c from Car c");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
public List<Car> getCars(int a,int b,Map<String, Object> searchParams) {
		
		List<Car> list = null;
		
		String select = "select c ";
		String from = "from Car c ";
		String where = "where c.marka like :marka ";
		String orderby = "order by c.marka asc, c.model";
		String surname = (String) searchParams.get("marka");

		// search for surname
		
		//Query query = em.createQuery("select c from Car c");
		Query query = em.createQuery(select + from + where + orderby);
		query.setParameter("marka", surname+"%");
		query.setFirstResult(a);
		query.setMaxResults(b);
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
public List<Car> getCars(int a,int b) {
	
	List<Car> list = null;
	
	
	Query query = em.createQuery("select c from Car c");
	
	query.setFirstResult(a);
	query.setMaxResults(b);
	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return list;
}
	public List<Car> getList(Map<String, Object> searchParams) {
		List<Car> list = null;

		// 1. Build query string with parameters
		String select = "select c ";
		String from = "from Car c ";
		String where = "";
		String orderby = "order by c.marka asc, c.model";

		// search for surname
		String surname = (String) searchParams.get("marka");
		if (surname != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "c.marka like :marka ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);
		
		// 3. Set configured parameters
		if (surname != null) {
			query.setParameter("marka", surname+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return list;
	}
}
