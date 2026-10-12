package com.wipro.payroll.util;

public class InvalidSalaryException extends Exception {

    @Override
    public String toString() {
        return "InvalidSalaryException : Basic Salary should be greater than zero.";
    }

}