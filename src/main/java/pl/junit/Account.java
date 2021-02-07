package pl.junit;

public class Account {

    private boolean active;
    private Address defaultDeliveryAddress;

    public Account(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        if(defaultDeliveryAddress != null){
            this.active = true;
        }else{
            this.active = false;
        }
    }

    public Account() {
        this.active = false;
    }

    public void activate(){
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }
}
