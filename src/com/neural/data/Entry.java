package com.neural.data;

/**
 * Created by Mateusz on 29.06.2016.
 */
public class Entry {

    private Double income;
    private Double age;
    private Double loan;
    private Double lti;

    public Entry(Double income, Double age, Double loan, Double lti) {
        this.income = income;
        this.age = age;
        this.loan = loan;
        this.lti = lti;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public Double getLti() {
        return lti;
    }

    public void setLti(Double lti) {
        this.lti = lti;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "income=" + income +
                ", age=" + age +
                ", loan=" + loan +
                ", lti=" + lti +
                '}';
    }
}
