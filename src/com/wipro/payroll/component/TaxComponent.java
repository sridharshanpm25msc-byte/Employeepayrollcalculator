package com.wipro.payroll.component;

public class TaxComponent implements SalaryComponent {

    @Override
    public double calculate(double grossSalary) {
        return grossSalary * 0.10;
    }

}