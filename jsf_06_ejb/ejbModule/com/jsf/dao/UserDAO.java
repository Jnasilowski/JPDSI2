package com.jsf.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jsf.entities.User;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "jsfcourse-carentPU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	public EntityManager em;
	UserDAO a ;
	User d = new User();
	
	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}

	public List<User> getFullList() {
		
		List<User> list = null;
		
		
		Query query = em.createQuery("select u from User u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public int czyJestLogin(String ab) {
		int i =0;
		a =  new UserDAO();
		List<User> list = null;
		Query query = em.createQuery("select u from User u");
		User u = null;
		list = query.getResultList();
		
		for(User c : list){
			if(ab.equals(c.getLogin())) {
				
				i++;
				
			}
			
		}
		return i;
		
	}
	
	public User getUserFromDatabase(String login, String pass) {
		a =  new UserDAO();
		List<User> list = null;
		Query query = em.createQuery("select u from User u");
		User u = null;
		list = query.getResultList();
		
		for(User c : list){
			if(login.equals(c.getLogin())&& pass.equals(c.getHasło())) {
			u = new User();
			u.setHasło(c.getHasło());
			u.setID_user(c.getID_user());
			u.setImie(c.getImie());
			u.setNazwisko(c.getImie());
			u.setLogin(c.getLogin());
			u.setRola(c.getRola());
			
			}
		}
		
		
		return u;
		
	}
	public List<String> getUserRolesFromDatabase(User user) {
		
        ArrayList<String> roles = new ArrayList<>();
        if (user.getLogin().equals("admin")) {
            roles.add("admin");
        }
        else {
        	roles.add("user");
        }
        

        return roles;
    }

	public List<User> getList(Map<String, Object> searchParams) {
		List<User> list = null;

		// 1. Build query string with parameters
		String select = "select u ";
		String from = "from User u ";
		String where = "";
		String orderby = "order by u.nazwisko asc, u.imie";

		// search for surname
		String surname = (String) searchParams.get("nazwisko");
		if (surname != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.nazwisko like :nazwisko ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from  + where + orderby);

		// 3. Set configured parameters
		if (surname != null) {
			query.setParameter("nazwisko", surname+"%");
			
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