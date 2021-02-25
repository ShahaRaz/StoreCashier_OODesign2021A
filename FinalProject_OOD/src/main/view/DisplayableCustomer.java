package main.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import main.model.Customer;

public class DisplayableCustomer {
    private SimpleStringProperty name;
    private SimpleStringProperty mobileNumber;
    private SimpleBooleanProperty isAcceptingPromotions;
    private SimpleStringProperty responseToPromotion;

    public DisplayableCustomer(Customer customer) {
        this.name = new SimpleStringProperty(customer.getName());
        this.mobileNumber = new SimpleStringProperty(customer.getMobileNumber());;
        this.isAcceptingPromotions = new SimpleBooleanProperty(customer.getIsAcceptingPromotions());
        this.responseToPromotion = new SimpleStringProperty(customer.announceGotPromotion());
    }

    public String getResponseToPromotion() {
        return responseToPromotion.get();
    }

    public SimpleStringProperty responseToPromotionProperty() {
        return responseToPromotion;
    }

    public void setResponseToPromotion(String responseToPromotion) {
        this.responseToPromotion.set(responseToPromotion);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getMobileNumber() {
        return mobileNumber.get();
    }

    public SimpleStringProperty mobileNumberProperty() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber.set(mobileNumber);
    }

    public boolean isIsAcceptingPromotions() {
        return isAcceptingPromotions.get();
    }

    public SimpleBooleanProperty isAcceptingPromotionsProperty() {
        return isAcceptingPromotions;
    }

    public void setIsAcceptingPromotions(boolean isAcceptingPromotions) {
        this.isAcceptingPromotions.set(isAcceptingPromotions);
    }
}
