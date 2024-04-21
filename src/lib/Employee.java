package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	private LocalDate dateJoined;
	private Nationality nationality;
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	private String spouseName;
	private String spouseIdNumber;
	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
			LocalDate dateJoined, Nationality nationality) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.dateJoined = dateJoined;
		this.nationality = nationality;
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}

	public void setMonthlySalary(int grade) {
		switch (grade) {
			case 1:
				monthlySalary = calculateGradeSalary(3000000);
				break;
			case 2:
				monthlySalary = calculateGradeSalary(5000000);
				break;
			case 3:
				monthlySalary = calculateGradeSalary(7000000);
				break;
		}
	}

	private int calculateGradeSalary(int baseSalary) {
		if (nationality == Nationality.FOREIGN) {
			return (int) (baseSalary * 1.5);
		} else {
			return baseSalary;
		}
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public int getOtherMonthlyIncome() {
		return otherMonthlyIncome;
	}

	public int getAnnualDeductible() {
		return annualDeductible;
	}

	public String getSpouseIdNumber() {
		return spouseIdNumber;
	}

	public List<String> getChildIdNumbers() {
		return childIdNumbers;
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {
		return TaxFunction.calculateTax(this);
	}

	public int calculateMonthsWorkingInYear() {
		LocalDate currentDate = LocalDate.now();
		if (currentDate.getYear() == this.dateJoined.getYear()) {
			return currentDate.getMonthValue() - this.dateJoined.getMonthValue();
		} else {
			return 12;
		}
	}
}

enum Nationality {
	LOCAL,
	FOREIGN
}