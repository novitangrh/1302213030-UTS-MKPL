package lib;

public class TaxFunction {
	public static int calculateTax(Employee employee) {
		int monthlySalary = employee.getMonthlySalary();
		int otherMonthlyIncome = employee.getOtherMonthlyIncome();
		int monthsWorkingInYear = employee.calculateMonthsWorkingInYear();
		int deductible = employee.getAnnualDeductible();
		boolean isMarried = employee.getSpouseIdNumber().equals("");
		int numberOfChildren = employee.getChildIdNumbers().size();

		int tax = 0;

		if (monthsWorkingInYear > 12) {
			System.err.println("More than 12 month working per year");
		}

		numberOfChildren = Math.min(numberOfChildren, 3);

		if (isMarried) {
			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * monthsWorkingInYear) - deductible
					- (54000000 + 4500000 + (numberOfChildren * 1500000))));
		} else {
			tax = (int) Math.round(
					0.05 * (((monthlySalary + otherMonthlyIncome) * monthsWorkingInYear) - deductible - 54000000));
		}

		if (tax < 0) {
			return 0;
		} else {
			return tax;
		}
	}
}
