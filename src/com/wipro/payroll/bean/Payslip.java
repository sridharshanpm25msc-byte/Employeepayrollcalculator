package com.wipro.payroll.bean;

public class Payslip {

    private String empId;
    private double gross;
    private double tax;
    private double net;

    public Payslip() {
    }

    public Payslip(String empId, double gross, double tax, double net) {
        this.empId = empId;
        this.gross = gross;
        this.tax = tax;
        this.net = net;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public double getGross() {
        return gross;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getNet() {
        return net;
    }

    public void setNet(double net) {
        this.net = net;
    }

    @Override
    public String toString() {
        return "Employee ID : " + empId
                + "\nGross Salary : " + gross
                + "\nTax : " + tax
                + "\nNet Salary : " + net + "\n";
    }
}