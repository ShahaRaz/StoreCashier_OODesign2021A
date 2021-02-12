package main.model;

public class Cmnd_AddProduct implements Command{
    private Product product;
    private Store store;
    public Cmnd_AddProduct(Product p,Store s) {
        this.product = p;
        this.store = s;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
