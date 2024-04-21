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

	private static final int GRADE_1_SALARY = 3000000;
	private static final int GRADE_2_SALARY = 5000000;
	private static final int GRADE_3_SALARY = 7000000;

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
				monthlySalary = calculateGradeSalary(GRADE_1_SALARY);
				break;
			case 2:
				monthlySalary = calculateGradeSalary(GRADE_2_SALARY);
				break;
			case 3:
				monthlySalary = calculateGradeSalary(GRADE_3_SALARY);
				break;
		}
	}

	private int calculateGradeSalary(int baseSalary) {
		return (nationality == Nationality.FOREIGN) ? (int) (baseSalary * 1.5) : baseSalary;
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
		LocalDate joinedDate = this.dateJoined;

		int monthsWorkedInYear = 12;
		if (currentDate.getYear() == joinedDate.getYear()) {
			monthsWorkedInYear = Math.max(currentDate.getMonthValue() - joinedDate.getMonthValue(), 0);
		}

		return monthsWorkedInYear;
	}

}

enum Nationality {
	LOCAL,
	FOREIGN
}