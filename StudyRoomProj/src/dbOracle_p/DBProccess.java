package dbOracle_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBProccess {

	private static DBProccess instance;

	public static DBProccess getInstance() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (instance == null) {
			instance = new DBProccess();
		}
		return instance;
	}

	Connection con;
	Statement stmt;

	DBProccess() {
	}

	public ResultSet findData(String query) {

		try {
			reset();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("findTableData is null");
		return null;
	}

	public void insertData(ETable table, QueryObject qo) {
		try {
			reset();
			String data = "insert into " + table.name() + "(" + qo.calum + ") values " + "(" + qo.query + ")";
			System.out.println(data);
			ResultSet rs = stmt.executeQuery(data);
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void reset() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			stmt = con.createStatement();
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

//// 쿠리 실행 데이터
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
