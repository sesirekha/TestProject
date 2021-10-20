package com.test.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.spring.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<User> getAllUsers() {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(User.class);//passing Class class argument  
		List list=c.list(); 
		return list;
	}

	@Override
	public User createUser(User user) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.ilike("email", user.getEmail()));
		List user1 =  cr.list();
		if(user1.isEmpty()) {
			session.save(user);
			tx.commit();
			session.close();
			return user;
		}
		else {
			tx.commit();
			session.close();
			return null;
		}

	}

	@Override
	public User loginUser(User user) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "from User u where u.email = ? and u.password = ?";
		List result = session.createQuery(hql)
				.setString(0, user.getEmail())
				.setParameter(1, user.getPassword())
				.list(); 
		if(!(result.isEmpty())) {
			return user;
		}
		else {
			return null;
		}
	}


}
