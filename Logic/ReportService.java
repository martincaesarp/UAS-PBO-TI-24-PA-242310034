package Logic;

import Dao.report;
import Dao.reportDAO;

public class ReportService {
	report rp = new report();
	reportDAO dao = new reportDAO();
	
	public void insertReport(int item_id, String description, String date, int employees_id) throws Exception {
		rp.setItemId(item_id);
		rp.setDescription(description);
		rp.setReportDate(date);
		rp.setEmployeesId(employees_id);
		
		dao.insertData(rp);
	}
}
