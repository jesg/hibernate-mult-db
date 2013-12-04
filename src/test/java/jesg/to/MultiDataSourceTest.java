package jesg.to;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class MultiDataSourceTest {

	public static void main(String[] args) {
		SessionFactory postgreSessionFactory = new Configuration().configure("postgres.cfg.xml").buildSessionFactory();
		Session postgreSession = postgreSessionFactory.openSession();
		SessionFactory mysqlSessionFactory = new Configuration().configure("mysql.cfg.xml").buildSessionFactory();
		Session mysqlSession = mysqlSessionFactory.openSession();
		mysqlSession.beginTransaction();
		postgreSession.beginTransaction();
		
		Food apple = new Food();
		apple.setName("Apple");
		postgreSession.save(apple);
		
		Person person = new Person();
		person.setFirstName("first");
		person.setLastName("last");
		mysqlSession.save(person);
		
		mysqlSession.getTransaction().commit();
		postgreSession.getTransaction().commit();
		postgreSessionFactory.close();
		mysqlSessionFactory.close();
		

	}

}
