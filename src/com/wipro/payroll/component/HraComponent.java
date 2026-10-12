package com.wipro.payroll.component;

public class HraComponent implements SalaryComponent {

    @Override
    public double calculate(double basicSalary) {
        return basicSalary * 0.40;
    }

}