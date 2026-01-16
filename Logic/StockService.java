package Logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Dao.items;
import Dao.itemsDAO;
import Database.ConnectionDB;

public class StockService {
	items item = new items();
	itemsDAO dao = new itemsDAO();
	
	public void insertItems(String name, String date, int quantity, int safety, String classification, int demand, int leadTime) throws Exception {
		int ropValue = ROP.rop(demand, leadTime, safety);
		
		item.setItem_name(name);
		item.setDate_in(date);
		item.setQuantity(quantity);
		item.setSafety(safety);
		item.setClassification(classification);
		item.setRop(ropValue);
		item.setDemand(demand);
		item.setLeadTime(leadTime);
		
		dao.insertData(item);
	}
	
	public void AddStock(int item_id, String date, int quantity, int safety, int demand, int leadTime, int qtybefore, int ssbefore) throws Exception {
		int newQuantity = qtybefore + quantity;
		int newSafetyStock = ssbefore + safety;
		int RopValue = ROP.rop(demand, leadTime, newSafetyStock);
		
		item.setDate_in(date);
		item.setQuantity(newQuantity);
		item.setSafety(newSafetyStock);
		item.setRop(RopValue);
		item.setItem_id(item_id);
	
		dao.AddupdateData(item);
	}
	
	public void editStock(int itemId, String itemName, String date, int quantity, int safetyStock, String classification, int demand, int leadTime, String namebefore, String datebefore, int qtybefore, int ssbefore, String clssbefore) throws Exception {
		namebefore = itemName;
		datebefore = date;
		qtybefore = quantity;
		ssbefore = safetyStock;
		clssbefore = classification;
		int RopValue = ROP.rop(demand, leadTime, ssbefore);
		
		
		item.setItem_name(namebefore);
		item.setDate_in(datebefore);
		item.setQuantity(qtybefore);
		item.setSafety(ssbefore);
		item.setClassification(clssbefore);
		item.setRop(RopValue);
		item.setItem_id(itemId);
		
		dao.EditData(item);	
	}
	
	public void editRop(int item_id, int demand, int leadTime, int safetyStock) throws Exception {
		int RopValue = ROP.rop(demand, leadTime, safetyStock);
		
		item.setDemand(demand);
		item.setLeadTime(leadTime);
		item.setRop(RopValue);
		item.setItem_id(item_id);
		
		dao.EditROP(item);
	}
	
	public void deleteStock(String name) throws Exception{
		item.setItem_name(name);
		
		dao.deletedata(item);
	}
}
