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

		double baseTaxableIncome = (monthlySalary + otherMonthlyIncome) * monthsWorkingInYear - deductible;

		if (isMarried) {
			tax = calculateMarriedTax(baseTaxableIncome, numberOfChildren);
		} else {
			tax = calculateSingleTax(baseTaxableIncome);
		}

		return Math.max(tax, 0);
	}

	private static int calculateMarriedTax(double baseTaxableIncome, int numberOfChildren) {
		return (int) Math.round(0.05 * (baseTaxableIncome - (54000000 + 4500000 + (numberOfChildren * 1500000))));
	}

	private static int calculateSingleTax(double baseTaxableIncome) {
		return (int) Math.round(0.05 * (baseTaxableIncome - 54000000));
	}
}