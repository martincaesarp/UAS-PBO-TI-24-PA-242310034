package Dao;

public class items {
	private int item_id;
	private String item_name;
	private String date_in;
	private int quantity;
	private int safety_stock;
	private String classification;
	private int ROP;
	private int demand;
	private int leadTime;
	
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	
	public String getItem_name() {
		return item_name;
	}
	
	public void setItem_name(String item_name ) {
		this.item_name = item_name;
	}
	
	public String getDate_in() {
		return date_in;
	}
	
	public void setDate_in(String date_in) {
		this.date_in = date_in;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getSafety() {
		return safety_stock;
	}
	
	public void setSafety(int safety_stock) {
		this.safety_stock = safety_stock;
	}
	
	public String getClassification() {
		return classification;
	}
	
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	public int getRop() {
		return ROP;
	}
	
	public void setRop(int ROP) {
		this.ROP = ROP;
	}
	
	public int getDemand() {
		return demand;
	}
	
	public void setDemand(int demand) {
		this.demand = demand;
	}
	
	public int getLeadTime() {
		return leadTime;
	}
	
	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}
	
	@Override
	public String toString() {
		return item_name;
	}
}
