package dbOracle_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBProcess {

	private static DBProcess instance;

	public static DBProcess getInstance() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (instance == null) {
			instance = new DBProcess();
		}
		return instance;
	}

	Connection con;
	Statement stmt;

	DBProcess() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		updateData();
	}

	public static void main(String[] args) {
		new DBProcess().updateData();
	}

	public ResultSet findData(QueryObject qo) throws SQLException {

		reset();
		ResultSet rs = stmt.executeQuery(qo.query);
		return rs;
	}

	public void insertData(ETable table, QueryObject qo) throws SQLException {
		reset();
		String data = "insert into " + table.name() + "(" + qo.calum + ") values " + "(" + qo.query + ")";
		System.out.println(data);
		ResultSet rs = stmt.executeQuery(data);
		close();
	}

	public void updateData() {
		String query = "insert into stud (id,name,kor,eng,mat,birth,reg) values "
				+ "('iii','¿Ã¡§πŒ',67,76,89,'1989-06-02',sysdate)";

		try {
			int cnt = stmt.executeUpdate(query);
			System.out.println("Ω««‡ ∞πºˆ" + cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

//// ƒÌ∏Æ Ω««‡ µ•¿Ã≈Õ
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
