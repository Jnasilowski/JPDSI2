package com.jsf.dao;
import java.util.List;
import java.util.Map;
import com.jsf.entities.Zamowienia;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Stateless
public class ZamowieniaDAO {
	private final static String UNIT_NAME = "jsfcourse-carentPU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	public void create(Zamowienia zamowienie) {
		em.persist(zamowienie);
	}

	public Zamowienia merge(Zamowienia zamowienie) {
		return em.merge(zamowienie);
	}

	public void remove(Zamowienia zamowienie) {
		em.remove(em.merge(zamowienie));
	}

	public Zamowienia find(Object id) {
		return em.find(Zamowienia.class, id);
	}

	public List<Zamowienia> getFullList() {
		
		List<Zamowienia> list = null;
		
		
		Query query = em.createQuery("select z from Zamowienia z");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
public List<Zamowienia> UsersList( String a) {
		
	List<Zamowienia> list = null;

	// 1. Build query string with parameters
	String select = "select z ";
	String from = "from Zamowienia z ";
	String where = "where z.user.ID_user = " + a;
	String orderby = "";

	// search for surname
	
	
	// ... other parameters ... 

	// 2. Create query object
	Query query = em.createQuery(select + from + where + orderby);

	// 3. Set configured parameters
	
	// ... other parameters ... 

	// 4. Execute query and retrieve list of Person objects
	try {
		list = query.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
		
	}
	
	return list;
	}
	
	public List<Zamowienia> getList(Map<String, Object> searchParams,String a) {
		List<Zamowienia> list = null;

		// 1. Build query string with parameters
		String select = "select z ";
		String from = "from Zamowienia z ";
		String where = "";
		String orderby = "order by z.ID_zamowienia asc";

		// search for surname
		String surname = (String) searchParams.get("ID_zamowienia");
		if (surname != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "z.ID_zamowienia = " + a;
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		
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
