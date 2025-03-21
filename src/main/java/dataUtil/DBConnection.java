//package utils;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import org.testng.Assert;
//import coreUtil.PropertyUtil;
//import testbase.TestBase;
//
//public class DBConnection extends TestBase {
//
//	static String result;
//
//	public static List<Object> getDBDataMap() {
//
//		List<Object> list = new ArrayList<Object>();
//
//		try {
//
//			Connection cn = DriverManager.getConnection(PropertyUtil.getValue("dbUrlWOName") + "map",
//					PropertyUtil.getValue("serverUserName"), PropertyUtil.getValue("serverPassword"));
//
//			Statement st = cn.createStatement();
//
//			ResultSet rs = st.executeQuery(PropertyUtil.getValue("queryMap"));
//
//			while (rs.next()) {
//
//				String status = rs.getString("Status");
//				String placeID = rs.getString("PlaceID");
//
//				list.add(status);
//				list.add(placeID);
//
//			}
//
//		}
//
//		catch (Exception e) {
//
//			Assert.assertTrue(false,
//					"User is not able to perform the below steps : <br>"
//							+ "Validation : Expected Value from API and Actual Value from DB"
//
//							+ "<br>Technical Error Message is as below : <br/>" + e.getMessage());
//
//		}
//
//		return list;
//	}
//
//	public static List<Object> getDBDataLibrary() {
//
//		List<Object> list = new ArrayList<Object>();
//
//		try {
//
//			Connection cn = DriverManager.getConnection(PropertyUtil.getValue("dbUrlWOName") + "library",
//					PropertyUtil.getValue("serverUserName"), PropertyUtil.getValue("serverPassword"));
//
//			Statement st = cn.createStatement();
//
//			ResultSet rs = st.executeQuery(PropertyUtil.getValue("queryLibrary"));
//
//			while (rs.next()) {
//
//				String message = rs.getString("Msg");
//				String id = rs.getString("ID");
//
//				list.add(message);
//				list.add(id);
//
//			}
//
//		}
//
//		catch (Exception e) {
//
//			Assert.assertTrue(false,
//					"User is not able to perform the below steps : <br>"
//							+ "Validation : Expected Value from API and Actual Value from DB"
//
//							+ "<br>Technical Error Message is as below : <br/>" + e.getMessage());
//
//		}
//
//		return list;
//	}
//}
