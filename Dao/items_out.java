package Dao;

public class items_out {
	private int item_out_id;
	private String date_out;
	private int quantity_out;
	private int item_id;
	private String itemName;
	private String classification;
	
	public int getItemOut() {
		return item_out_id;
	}
	
	public void setItemOut(int item_out_id) {
		this.item_out_id = item_out_id;
	}
	
	public String getDateOut() {
		return date_out;
	}
	
	public void setDateOut(String date_out) {
		this.date_out = date_out;
	}
	
	public int getQuantityOut() {
		return quantity_out;
	}
	
	public void setQuantityOut(int quantity_out) {
		this.quantity_out = quantity_out;
	}
	
	public int getItemId() {
		return item_id;
	}
	
	public void setItemId(int item_id) {
		this.item_id = item_id;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getClassification() {
		return classification;
	}
	
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	@Override
	public String toString() {
		return String.valueOf(item_out_id);
	}
}
