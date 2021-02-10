package fr.control.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Base {
	static final Logger logger = LogManager.getLogger("Suivi");
	private static final SessionFactory sessionFactory;
	
	static {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		MetadataSources sources = new MetadataSources(registry);
		Metadata metadata = sources.buildMetadata();
		sessionFactory = metadata.buildSessionFactory();
	}//-
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}//FIN PRG
