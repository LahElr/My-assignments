package priv.lahelr.onlinelib.server.dba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//���ڷ������ݿ���࣬�����������̬������������ʵ������
/**
 * DataBase Accessor<br>
 * The class used to access the database,<br>
 * synchronized,<br>
 * no instantiation is allowed.
 * 
 * @author lahelr
 *
 */
public class Dba {
	protected static String dbClassName = "com.mysql.cj.jdbc.Driver";
	protected static String dbUrl = "jdbc:mysql://localhost:3306/Library?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	protected static String dbUser = "abcd";
	protected static String dbPwd = "1234567";
	protected static String second = null;
	private static Connection conn = null;
	// connect to db
	static {
		try {
			if (conn == null) {
				Class.forName(dbClassName);
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	// no instantiation
	private Dba() {
	}

	/**
	 * Do specified query
	 * 
	 * @param sql
	 * @return one result set
	 */
	public static ResultSet findForResultSet(String sql) {
		if (conn == null)
			return null;
		long time = System.currentTimeMillis();
		ResultSet rs = null;
		synchronized (conn) {
			try {
				Statement stmt = null;
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(sql);
				second = ((System.currentTimeMillis() - time) / 1000d) + "";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rs;
	}

	/**
	 * do specified insertion to the database
	 * 
	 * @param sql
	 * @return if the updating successed
	 */
	public static boolean insert(String sql) {
		boolean result = false;
		synchronized (conn) {
			try {
				Statement stmt = conn.createStatement();
				result = (stmt.executeUpdate(sql) > 0);// �����������0�࣬��Ϊ�ɹ�
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * do specified db update
	 * 
	 * @param sql
	 * @return the count of lines involved in the updating
	 */
	public static int update(String sql) {
		int result = 0;
		synchronized (conn) {
			try {
				Statement stmt = conn.createStatement();
				result = stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}