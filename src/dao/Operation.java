package dao;

import java.util.ArrayList;
import java.util.Date;
//import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Manage.Util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//import Interface.Shop;
import data.Customer;
import data.Inventory;

public class Operation <T> {
	//private static final String  = null;
	Connection conn = null;
	PreparedStatement stmt = null;
	public Operation() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/detail","root","infoobjects");
			if(conn==null) {
				System.out.println("connection failed");
			}
		}catch (SQLException ex) {
			//errors for jdbc
		    ex.printStackTrace();
		}
		catch(Exception e){
		      //Handle errors for Class.forName
		    e.printStackTrace();
		}
	}
	
	public T getRow(String name,List<Object> list) throws ParseException {
		if(name.equals("Inventory")) {
			Inventory object = new Inventory();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh.mm.ss");
			
			long created = Long.parseLong(list.get(4).toString());
			long updated = Long.parseLong(list.get(5).toString());
//			Timestamp created_at = Util.convertStringToTimestamp(sdf.format(created));
//			Timestamp updated_at = Util.convertStringToTimestamp(sdf.format(updated));
//			Date date = new Date();
			
			object.setId((Integer) list.get(0));
			object.setProd_name((String) list.get(1));
			object.setProd_quan((Integer) list.get(2));
			object.setPrice((Integer) list.get(3));
			object.setCreated_at(created);
			object.setUpdated_at(updated);
			
			return (T) object;
		}else if(name.equals("Customer")){
			Customer object = new Customer();
			object.setId((Integer) list.get(0));
			object.setProd_name((String) list.get(1));
			object.setPurchase_quan((Integer) list.get(2));
			object.setCost((Integer) list.get(3));
			return (T) object;
		}
		else {
			//System.out.println("wrong");
			return null;
		}
	}
	
	Scanner scan = new Scanner(System.in);
	private HashMap<Integer, T > inventoriesMap = new HashMap<>();
	
	public void create(int id,T inventObj) {
		
		inventoriesMap.put(id,inventObj);
		LinkedHashMap<String,Object> map = (LinkedHashMap<String, Object>) ((Inventory) inventObj).toMap();
		String name = inventObj.getClass().getSimpleName();
		StringBuilder sql = new StringBuilder("insert into "+ name + " values(?");
		int i;
		for(i=0;i<map.size()-1;i++) {
			sql.append(",?");
		}
		sql.append(");");
		i=1;
		try {
			stmt = conn.prepareStatement(sql.toString());
			if(stmt==null) {
				System.out.println("execution failed");
			}
			i=1;
			for (Entry<String, Object> entry : map.entrySet()) {
				stmt.setString(i++, entry.getValue().toString());
			}
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public T read(int id,T inventObj) throws ParseException {
		int i=1;
		Operation<T> operation = new Operation<>();
		List<Object> list = new ArrayList<Object>();
		String name = inventObj.getClass().getSimpleName();
		String sql = "select * from " + name +" where id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				for(i=1;i<=columnCount;i++) {
					list.add(rs.getObject(i));
				}
			}
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return operation.getRow(name,list);
	}
	
	 public void update(int id,int index,String s,T inventObj) {
		 String name = inventObj.getClass().getSimpleName();
		 String sql = "select * from " +name+" where id = ?";
		 try {
			 stmt = conn.prepareStatement(sql);
			 stmt.setInt(1, id);
			 ResultSet rs = stmt.executeQuery();
			 ResultSetMetaData rsmd = rs.getMetaData();
			 String sql1 = "update " + name + " set "+rsmd.getColumnName(index+1)+" = '"+s+"' where id = "+id+";";
			 stmt.executeUpdate(sql1);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void listUpdate(int id,Map<String,Object> map,T inventObj) {
		 Operation<T> operation = new Operation<>();
		 int index = 0;
		 for (Entry<String, Object> entry : map.entrySet())
		 {
		     String s = entry.getValue().toString();
		     operation.update(id,index,s,inventObj);
		     index++;
		 }
	 }
	 
	 public void delete(int id,T inventObj) {
		 String name = inventObj.getClass().getSimpleName();
		 String sql = "delete from "+ name + " where id = ?";
		 try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 inventoriesMap.remove(id);
	 }
	 public List<T> readAll(T inventObj) throws ParseException {
		 int i;
		 Operation operation = new Operation();
		 List<T> completeList = new ArrayList<T >();
		 String name = inventObj.getClass().getSimpleName();
		 String sql = "select * from " + name;
		 try {
				stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				while(rs.next()) {
					ArrayList<Object> list = new ArrayList<Object>();
					for(i=1;i<=columnCount;i++) {
						list.add(rs.getObject(i));
					}
					completeList.add((T) operation.getRow(name, list));
				}
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return completeList;
	 }
	 
	 public boolean checkValidId(int id,T inventObj) {
		 boolean bool = false;
		 String name = inventObj.getClass().getSimpleName();
		 String sql = "select 1 from " +name+ " where id = ?";
		 try {
			 stmt = conn.prepareStatement(sql);
			 stmt.setInt(1,id);
			 ResultSet rs = stmt.executeQuery();
			 bool = rs.next();
			 stmt.close();
			 System.out.println(bool);
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		}
		 return bool;
	 }
	 public void closeConn() {
		 try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
	 public void exit() {
		 try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 }
}
