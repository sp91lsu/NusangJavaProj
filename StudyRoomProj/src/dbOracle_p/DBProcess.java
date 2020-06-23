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

//	ResultSet findData() {
//
//		reset();
//		// rs = stmt.executeQuery(qo.query);
//		return rs;
//	}

	void insertQuery(ETable table, String calum, String value) {
		query = "insert into " + table.name() + "(" + calum + ") values " + "(" + value + ")";
		System.out.println(query);
	}

	void findQuery(ETable table, String... data) {

		query = "select " + data[0] + " from " + table.name();
		if (data.length == 2) {
			query += " where " + data[1];
		}
		System.out.println(query);
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

	public String getCalum(String... calumArr) {
		String cQuery = "";

		for (int i = 0; i < calumArr.length; i++) {

			cQuery += calumArr[i];
			if (i < calumArr.length - 1) {
				cQuery += ",";
			}
		}
		return cQuery;
	}

	public String getCalumNum(int num) {
		String cQuery = "";

		for (int i = 0; i < num; i++) {

			cQuery += "?";
			if (i < num - 1) {
				cQuery += ",";
			}
		}
		return cQuery;
	}

	void reset() {
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

//	public void createQuery(String calum, Object... valueArr) {
//
//		this.calum = calum;
//
//		for (int i = 0; i < valueArr.length; i++) {
//			System.out.println(valueArr[i].toString());
//
//			if (valueArr[i].getClass() == String.class) {
//				query += "'" + valueArr[i].toString() + "'";
//			} else {
//				query += valueArr[i].toString();
//			}
//			if (i < valueArr.length - 1) {
//				query += ",";
//			}
//		}
//	}
//

}
//// Äí¸® ½ÇÇà µ¥ÀÌÅÍ
//while (rs.next()) {
//	System.out.print(rs.getString("id"));
//	System.out.print(rs.getString("name"));
//	System.out.print(rs.getString("kor"));
//	System.out.print(rs.getString("eng"));
//	System.out.print(rs.getString("mat"));
//	System.out.println();
//	Timestamp time = rs.getTimestamp("reg");
//	Stud st = new Stud(rs.getString("name"), rs.getString("id"), rs.getInt("kor"));
//}
