package thinksync.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionProvider {
	private static SessionFactory factory;

    static {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            factory = configuration.buildSessionFactory();
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        return factory.openSession();
    }
}
