package geopixel.server.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @since 22/06/2012
 */

@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	/**
	 * Gasta-se recurso para se obter o 1o SessionFactory
	 */
	static {
		try {
			sessionFactory = getConfiguration().buildSessionFactory();
		} catch (Throwable t) {
			Exception exception = new Exception("Falha ao criar o SessionFactory.", t);
			throw new ExceptionInInitializerError(exception);
		}
	}

	/**
	 * Configuracao de acesso ao banco utilizado pelo hibernate.
	 * 
	 * remember - set configure with: "geopixel/server/helper/hibernate.cfg.xml"
	 *  
	 * @return
	 */
	public static Configuration getConfiguration() {
		return new Configuration().configure("geopixel/server/helper/hibernate.cfg.xml");
	}

	/**
	 * SessionFactory da Classe. Nao existe instancia dessa Classe.
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Encerra a sessao corrente.
	 */
	public static void closeSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	/**
	 * Efetua o rollback na transacao corrente.
	 */
	public static void rollbackTransaction(Transaction transaction) {
		if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
			transaction.rollback();
		}
	}

	/**
	 * Cria as tabelas informadas pelo objeto no banco de dados fornecido na
	 * configuracao
	 */
	public static void main(String[] args) {
		try {
			new SchemaExport(getConfiguration()).create(true, true);
			getSessionFactory().openSession().beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}