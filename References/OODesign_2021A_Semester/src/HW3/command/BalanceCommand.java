/**
 * @author Shahar Raz
 *
 **/

package HW3.command;

public class BalanceCommand implements Command{
    private double currentBalance;

    public BalanceCommand(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public double getBalance() {
        return this.currentBalance;
    }

    @Override
    public void setBalance(double value) {
        this.currentBalance=value;
    }


    public double getBalanceCommand() {
        return getBalance(); // FIXME: 25/01/2021 why am i needed
    }

    public void setBalanceCommand(double simBalance) {
        this.setBalance(simBalance); // me too here for no reason :P
    }
}
