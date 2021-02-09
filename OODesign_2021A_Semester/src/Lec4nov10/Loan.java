package Lec4nov10;

import java.io.Serializable;
import java.util.Comparator;
public class  Loan implements Serializable
{ private String loanDescription;
  private double annualInterestRate;
  private int numberOfYears;
  private double loanAmount;
  private java.util.Date loanDate;
  /** Default constructor */
  public Loan()
  { this(2.5, 1, 1000, "");
  }
  /** Construct a loan with specified annual interest rate,
      number of years and loan amount     */
  public Loan(double annualInterestRate, int numberOfYears, double loanAmount,
		  String loanDescription)
  { this.loanDescription = loanDescription;
	this.annualInterestRate = annualInterestRate;
    this.numberOfYears = numberOfYears;
    this.loanAmount = loanAmount;
    loanDate = new java.util.Date();
  }
  /** Return annualInterestRate */
  public double getAnnualInterestRate()
  { return annualInterestRate;
  }
  /** Set a new annualInterestRate */
  public void setAnnualInterestRate(double annualInterestRate)
  { this.annualInterestRate = annualInterestRate;
  }
  /** Return numberOfYears */
  public int getNumberOfYears()
  { return numberOfYears;
  }
  /** Set a new numberOfYears */
  public void setNumberOfYears(int numberOfYears)
  { this.numberOfYears = numberOfYears;
  }
  /** Return loanAmount */
  public double getLoanAmount()
  { return loanAmount; 
  }
  /** Set a newloanAmount */
  public void setLoanAmount(double loanAmount)
  { this.loanAmount = loanAmount;
  }
  /** Find monthly payment */
  public double getMonthlyPayment()
  { double monthlyInterestRate = annualInterestRate / 1200;
    double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
      (Math.pow(1 / (1 + monthlyInterestRate), numberOfYears * 12)));
    return monthlyPayment;
  }
  /** Find total payment */
  public double getTotalPayment()
  { double totalPayment = getMonthlyPayment() * numberOfYears * 12;
    return totalPayment;
  }
  /** Return loan date */
  public java.util.Date getLoanDate()
  { return loanDate; 
  }
  public String getLoanDescription()
  { return loanDescription;
  }
  public void setLoanDescription(String loanDescription)
  { this.loanDescription = loanDescription;
  }
}
class LoanObjectComparatorByTotalPayment 
  implements Comparator<Loan>, Serializable
  //java.io.Serializable is not essential here   
{ private static final long serialVersionUID = 1L;
  public int compare(Loan loan1, Loan loan2)
  { double totalPayment1 = loan1.getTotalPayment();
    double totalPayment2 = loan2.getTotalPayment();
    if (totalPayment1 > totalPayment2)        return -1;
       else if (totalPayment1 == totalPayment2)  return 0;
            else   return 1;
  }
}
class LoanObjectComparatorByNumberOfYears 
  implements Comparator<Loan>, Serializable
  //java.io.Serializable is not essential here   
{ private static final long serialVersionUID = 1L;
  public int compare(Loan loan1, Loan loan2)
  { int numberOfYears1 = loan1.getNumberOfYears();
    int numberOfYears2 = loan2.getNumberOfYears();
    if (numberOfYears1 > numberOfYears2)        return 1;
    else if (numberOfYears1 == numberOfYears2)  return 0;
         else   return -1;
  }
}
