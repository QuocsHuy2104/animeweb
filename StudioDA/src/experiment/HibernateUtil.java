package experiment;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private final static SessionFactory FACTORY;

	static {
		Configuration conf = new Configuration();
		Properties pros = new Properties();
		pros.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2012Dialect");
		pros.put(Environment.DRIVER, "com.microsoft.sqlserver");
		pros.put(Environment.URL, "jdbc:sqlserver://localhost:1433/hibernate");
		pros.put(Environment.USER, "sa");
		pros.put(Environment.PASS, "0398948675");
		conf.setProperties(pros);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		
		FACTORY = conf.buildSessionFactory(registry);
	}

	public static SessionFactory getSessionFactory() {
		return FACTORY;
	}
}
