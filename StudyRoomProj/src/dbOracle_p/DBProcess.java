package dbOracle_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBProcess {

	private static DBProcess instance;

	Connection con;
	public PreparedStatement stmt;
	ResultSet rs;
	String query = "";

	DBProcess() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
//			stmt = (PreparedStatement) con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// updateData();
	}

	void insertQuery(ETable table, String calum, String... value) {
		query = "insert into " + table.name() + "(" + calum + ") values " + "(" + value[0] + ")";
		where(value);
	}

	void updateQuery(ETable table, String calum, String... value) {
		query = "UPDATE " + table.name() + " SET " + calum + " = " + value[0];

		where(value);
	}

	void findQuery(ETable table, String... value) {

		query = "select " + value[0] + " from " + table.name();
		where(value);

	}

	void deleteQuery(ETable table, String... value) {
		query = "DELETE " + "from " + table.name() + " where " + value;
	}

	void where(String... data) {
		if (data.length == 2) {
			query += " where " + data[1];
		}
		try {
			stmt = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(query);
	}

	ResultSet getRS(ETable table, String... keys) throws SQLException {

		findQuery(table, keys);

		return stmt.executeQuery();
	}
//	public void setQuery() throws SQLException {
//		reset();
//		rs = stmt.executeQuery(query);
//		close();
//	}

//	public void updateData() {
//		String query = "insert into stud (id,name,kor,eng,mat,birth,reg) values "
//				+ "('iii','ÀÌÁ¤¹Î',67,76,89,'1989-06-02',sysdate)";
//
//		try {
//			int cnt = stmt.executeUpdate(query);
//			System.out.println("½ÇÇà °¹¼ö" + cnt);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public String getColum(String... calumArr) {
		String cQuery = "";

		for (int i = 0; i < calumArr.length; i++) {

			cQuery += calumArr[i];
			if (i < calumArr.length - 1) {
				cQuery += ",";
			}
		}
		return cQuery;
	}

	public String getColumNum(int num) {
		String cQuery = "";

		for (int i = 0; i < num; i++) {

			cQuery += "?";
			if (i < num - 1) {
				cQuery += ",";
			}
		}
		return cQuery;
	}

	public void reset() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {

		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
