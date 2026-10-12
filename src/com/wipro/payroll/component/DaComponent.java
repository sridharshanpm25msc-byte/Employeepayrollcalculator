package com.wipro.payroll.component;

public class DaComponent implements SalaryComponent {

    @Override
    public double calculate(double basicSalary) {
        return basicSalary * 0.10;
    }

}