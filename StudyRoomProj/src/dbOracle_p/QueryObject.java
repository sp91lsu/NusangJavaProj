package dbOracle_p;

public class QueryObject {

	public String calum = "";
	public String query = "";

	public void createQuery(String calum, Object... valueArr) {
		this.calum = calum;

		for (int i = 0; i < valueArr.length; i++) {
			System.out.println(valueArr[i].toString());

			if (valueArr[i].getClass() == String.class) {
				query += "'" + valueArr[i].toString() + "'";
			} else {
				query += valueArr[i].toString();
			}
			if (i < valueArr.length - 1) {
				query += ",";
			}
		}
	}

	public String setFindQuery(ETable table, String... data) {
		query = "select " + data[0] + " from " + table.name();
		if (data.length == 2) {
			query += " where " + data[1];
		}
		return query;
	}
}
