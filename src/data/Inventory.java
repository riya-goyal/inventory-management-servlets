package data;

import java.util.LinkedHashMap;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Inventory{
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh.mm.ss");

	private int id;
	private String prod_name;
	private int prod_quan;
	private float price;
	long created_at;
	long updated_at;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public long getCreated_at() {
		return created_at;
	}
	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}
	public long getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(long updated_at) {
		this.updated_at = updated_at;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getProd_quan() {
		return prod_quan;
	}
	public void setProd_quan(int prod_quan) {
		this.prod_quan = prod_quan;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public LinkedHashMap<String,Object> toMap(){
		LinkedHashMap<String,Object> map = new LinkedHashMap<>();
		map.put("id",id);
		map.put("prod_name",prod_name);
		map.put("prod_quan",prod_quan);
		map.put("price",price);
		map.put("created_at",created_at);
		map.put("updated_at",updated_at);
		return map;
	}
	@Override
	
	public String toString() {
		return "Inventory [id=" + id + ", prod_name=" + prod_name + ", prod_quan=" + prod_quan + ", price=" + price
				+ ", created_at=" + sdf.format(created_at) + ", updated_at=" + sdf.format(updated_at) + "]";
	}
	
}
