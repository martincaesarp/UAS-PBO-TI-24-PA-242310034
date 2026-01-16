package Logic;

public class ROP {
	public static int rop(int demand, int Lead_time, int Safety_stock) {
		
		int rp = (demand * Lead_time) + Safety_stock;
		
		return rp;
	}
	
}
