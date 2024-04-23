package org.example.course_homework.hw_4;

import java.util.Scanner;

/**
 * Auto dealerships frequently advertise tempting loan offers in order to make it easier for people to afford the "car of their dreams".
 * A typical sales tactic is to show you various cars, and then talk in terms of what your monthly payment would be,
 * to say nothing of how much you are actually paying for the car, how much interest you pay, or how long you have to make payments.
 * <p>
 * A typical auto loan is calculated using a fixed interest rate, and is set up so that you make
 * the same monthly payment for a set period of time in order to fully pay off the balance.
 * The balance of your loan starts out as the sticker price of the car.
 * Each month, the monthly interest is added to your balance, and the amount of your payment is subtracted from your balance.
 * (The payment is subtracted after the interest is added.) The monthly interest rate is 1/12 of the yearly interest rate.
 * Thus, if your annual percentage rate is 12%, then 1% of the remaining balance would be charged as interest each month.
 * <p>
 * You have been checking out some of the cars at your local dealership.
 * An excited salesman has just approached you, shouting about how you can have the car you are looking at
 * for a payment of only monthlyPayment for only loanTerm months! You are to find the
 * annual percentage rate of the loan, assuming that the initial balance of the loan is price.
 * <p>
 * Because of the way interest is compounded monthly,
 * the actual interest accrued over the course of a year is not
 * necessarily the same as (balance * yearly interest rate). In fact, it's usually more.
 * <p>
 * Input Format
 * <p>
 * A single line of input contains 2 real numbers price and monthlyPayment and a single integer number loanTerm.
 * <p>
 * Constraints
 * <p>
 * 1 ≤ price ≤ 106
 * 0 ≤ monthlyPayment ≤ price / 2
 * 1 ≤ loanTerm ≤ 600
 * The resulting interest rate will be between 0 and 100, inclusive.
 * Output Format
 * <p>
 * Print a single number — the resulting yearly interest rate.
 * Your answer must be within 10-6 absolute error of the actual result.
 *
 * Sample Input 0
 *
 * 6800.00 100.00 68
 * Sample Output 0
 *
 * 0.000000000000133
 * Explanation 0
 *
 * Noting that 68 payments of 100 equals the total price of 6800, so there is no interest.
 */
public class AutoLoan {

    private static double price = 100;
    private static double payment = 0;
    private static int month = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        price = scanner.nextDouble();
        payment = scanner.nextDouble();
        month = scanner.nextInt();

        double interest = bin_search();
        System.out.println(interest);

        for (int i = 0; i < 1_000_000; i++) {
            float param = i / 10_000f;
            double simple = buildEquation(param, price);
            double powEquation = buildEquationPow(param, price);
            System.out.println(powEquation - simple);
        }
    }

    private static double bin_search() {
        double bad = 0;
        double good = 100;

        while (good - bad > 0.000000001) {
            double medium = (good + bad) / 2;
            if (buildEquation(medium, price) > 0) {
                good = medium;
            } else {
                bad = medium;
            }
        }
        return good;
    }

    private static double buildEquationPow(double x, double local_balance) {
        x = 1 + x / 100;
        local_balance = (local_balance * Math.pow(x, month));

        for (int i = 0; i < month; i++) {
            local_balance -= Math.pow(x, i) * payment;
        }

        return local_balance;
    }

    private static double buildEquation(double x, double local_price) {
        x = 1 + x / 100;
        for (int i = 0; i < month; i++) {
            local_price = local_price * x - payment;
        }

        return local_price;
    }
}
