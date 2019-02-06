package data;

import java.util.LinkedHashMap;
import java.util.Map;

//import Interface.Shop;

public class Customer{
	private int id;
	private String prod_name;
	private int purchase_quan;
	private int cost;
	long created_at;
	long updated_at;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getPurchase_quan() {
		return purchase_quan;
	}
	public void setPurchase_quan(int purchase_quan) {
		this.purchase_quan = purchase_quan;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
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
	public Map<String,Object> toMap(){
		Map<String,Object> map = new LinkedHashMap<>();
		map.put("id",id);
		map.put("prod_name",prod_name);
		map.put("purchase_quan",purchase_quan);
		map.put("cost",cost);
		map.put("created_at",created_at);
		map.put("updated_at",updated_at);
		return map;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", prod_name=" + prod_name + ", purchase_quan=" + purchase_quan + ", cost=" + cost
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
}
