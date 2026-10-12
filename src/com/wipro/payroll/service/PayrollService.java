package com.wipro.payroll.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.wipro.payroll.bean.EmployeeSalaryBean;
import com.wipro.payroll.bean.Payslip;
import com.wipro.payroll.component.DaComponent;
import com.wipro.payroll.component.HraComponent;
import com.wipro.payroll.component.SalaryComponent;
import com.wipro.payroll.component.TaxComponent;
import com.wipro.payroll.util.InvalidSalaryException;

public class PayrollService {

    private SalaryComponent hra = new HraComponent();
    private SalaryComponent da = new DaComponent();
    private SalaryComponent tax = new TaxComponent();

    public Payslip calculatePayslip(EmployeeSalaryBean emp)
            throws InvalidSalaryException {

        if (emp.getBasicSalary() <= 0) {
            throw new InvalidSalaryException();
        }

        double basic = emp.getBasicSalary();

        double hraAmount = hra.calculate(basic);
        double daAmount = da.calculate(basic);

        double gross = basic + hraAmount + daAmount;

        double taxAmount = tax.calculate(gross);

        double net = gross - taxAmount;

        return new Payslip(emp.getEmpId(), gross, taxAmount, net);
    }

    public List<Payslip> processPayroll(List<EmployeeSalaryBean> employees) {

        return employees.stream()
                .map(emp -> {
                    try {
                        return calculatePayslip(emp);
                    } catch (InvalidSalaryException e) {
                        System.out.println(e);
                        return null;
                    }
                })
                .filter(p -> p != null)
                .sorted(Comparator.comparing(Payslip::getNet).reversed())
                .collect(Collectors.toList());
    }

    public double getTotalPayrollCost(List<Payslip> payslips) {

        return payslips.stream()
                .mapToDouble(Payslip::getNet)
                .sum();
    }

    public static void main(String[] args) {

        PayrollService service = new PayrollService();

        List<EmployeeSalaryBean> employees = new ArrayList<>();

        employees.add(new EmployeeSalaryBean("E101", "Rahul", 30000));
        employees.add(new EmployeeSalaryBean("E102", "Priya", 45000));
        employees.add(new EmployeeSalaryBean("E103", "Arun", 25000));
        employees.add(new EmployeeSalaryBean("E104", "Deepa", 50000));
        employees.add(new EmployeeSalaryBean("E105", "Kiran", 20000));
        employees.add(new EmployeeSalaryBean("E106", "Suresh", 15000));
        employees.add(new EmployeeSalaryBean("E107", "Meena", 38000));
        employees.add(new EmployeeSalaryBean("E108", "John", 60000));
        employees.add(new EmployeeSalaryBean("E109", "Anu", 27000));
        employees.add(new EmployeeSalaryBean("E110", "Kumar", 0));

        List<Payslip> payslips = service.processPayroll(employees);

        System.out.println("========== PAYSLIP REPORT ==========");

        payslips.forEach(System.out::println);

        System.out.println("------------------------------------");

        System.out.println("Total Payroll Cost : " +
                service.getTotalPayrollCost(payslips));
    }
}