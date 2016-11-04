package mule.learning.beans;

public class Employee {
	private Manager manager;
	private Consultant consultant;
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public Consultant getConsultant() {
		return consultant;
	}
	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}
}
