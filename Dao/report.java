package Dao;

public class report {
    private int idReport;
    private int itemId;
    private String description;
    private String reportDate;
    private int employeesId;
    private String itemName;
    private String employeesName;

    public int getIdReport() {
        return idReport;
    }
    
    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public int getItemId() {
        return itemId;
    }
    
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportDate() {
        return reportDate;
    }
    
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    
    public int getEmployeesId() {
        return employeesId;
    }
    
    public void setEmployeesId(int employeesId) {
        this.employeesId = employeesId;
    }
    
    public String getItemName() {
    	return itemName;
    }
    
    public void setItemName(String itemName) {
    	this.itemName = itemName;
    }
    
    public String getEmployeesName() {
    	return employeesName;
    }
    
    public void setEmployeesName(String employeesName) {
    	this.employeesName = employeesName;
    }
}
