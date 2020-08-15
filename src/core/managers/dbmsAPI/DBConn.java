package core.managers.dbmsAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConn {
   Connection conn = null;
   Statement stmt = null;
   public DBConn(String url, String name, String usr, String pass) {
	   try {
	   conn = DriverManager.getConnection(url + name, usr, pass);
	   stmt = conn.createStatement();
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
	   }
   } 
   public boolean CreateTable(String name,String ...variables) {
   	String subq = "";
   	for(String vars : variables) {
   		subq += vars + ",";
   	}
   	subq = "(" + subq.substring(0, subq.length() - 1) + ");";
   	String q = "create table " + name + subq;
   	boolean done =  false;
   	try {
		done = stmt.execute(q);
		done = true;
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
   	return done;
   }
   public boolean DeleteTable(String name) {
   	boolean done = false;
   	try {
		stmt.execute("DROP TABLE " + name);
		done = true;
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
   	return done;
   }
   public boolean addVals(String table,String...vals) {
	   String q1 = "insert into " + table + "(";
	   String q2 = "values(";
	   for(String val : vals) {
		   String[] tmp = val.split("=");
		   q1 += tmp[0] + ",";
		   q2 += tmp[1] + ",";
	   }
	   String q = q1.substring(0, q1.length() - 1) + ") " + q2.substring(0, q2.length() - 1) + ");";
	   boolean done = false;
	   try {stmt.execute(q);done = true;}
	   catch(SQLException e) {System.out.println(e.getMessage());}
	   return done;
   }
   public boolean addColumn(String table, String variable) {
	   String q = "ALTER TABLE " + table + " ADD COLUMN " + variable + ";";
	   boolean done = false;
	   try {stmt.execute(q);done = true;}
	   catch(SQLException e) {System.out.println(e.getMessage());}
	   return done;
   }
   public boolean remColumn(String table, String name) {
	   String q = "ALTER TABLE " + table + " DROP COLUMN " + name + ";";
	   boolean done = false;
	   try {stmt.execute(q);done = true;}
	   catch(SQLException e) {
		   System.out.println(e.getMessage());
	   }
	   return done;
   }
   public boolean updCol(String table, String WhereCon, String...var_equalto_val) {
	   String q = "UPDATE " + table + " SET ";
	   for(String tmp : var_equalto_val) {
		   q += tmp + ",";
	   }
	   if(WhereCon.length() == 0)
		   q = q.substring(0, q.length() - 1)   + ";";
	   else
		   q = q.substring(0, q.length() - 1) + " WHERE " + WhereCon  + ";";
	   boolean done = false;
	   try {stmt.execute(q);done = true;}
	   catch(SQLException e) {System.out.println(e.getMessage());}
	   return done;
   }
   public Object[] getCol(String table,String colname) {
	   List<String> obj = new ArrayList<String>();
	   try {
		ResultSet rs = stmt.executeQuery("select " + colname + " from " + table);
		while(rs.next())
			obj.add(rs.getString(colname));
		
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	   
	  return obj.toArray();
   }
   public String getCol(String table, String colname, int index) {
	   String val = "";
	   try {
		ResultSet rs = stmt.executeQuery("select " + colname + " from " + table);
		while(index-- != 0)
			rs.next();
		 val = rs.getString(colname);
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	   return val;
   }
   public String getValue(String table, String colname, String where) {
	   String val = "";
	try {
		ResultSet rs = stmt.executeQuery("select " + colname + " from " + table + " where " + where);
		rs.first();
		val = rs.getString(colname);
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	   return val;
	   
   }
   public Object[] getValues(String table, String colname, String where) {
	   List<String> vals = new ArrayList<String>();
	try {
		ResultSet rs = stmt.executeQuery("select " + colname + " from " + table + " where " + where);
		while(rs.next())
			vals.add(rs.getString(colname));
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	   return vals.toArray();
	   
   }
   public ResultSet runQuery(String q) {
	   ResultSet rs = null;
	   try {
		rs = stmt.executeQuery(q);
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	   return rs;
   }
   public boolean modColumn(String table, String variable) {
	   String q = "ALTER TABLE " + table + " MODIFY COLUMN " + variable + ';';
	   boolean done = false;
	   try {stmt.execute(q);done = true;}
	   catch(SQLException e) {System.out.println(e.getMessage());}
	   return done;
   }
   public void close() {
		try {
			if(conn!=null)
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
   }
   @Override
   protected void finalize() {
	   try {
		if(conn != null)
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
