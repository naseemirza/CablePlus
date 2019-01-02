package solutions.thinkbiz.cableplus;

/**
 * Created by User on 26-Nov-18.
 */

public class OrderPageModel {

    private String name;
    private int image;
    private int quantity;

    public OrderPageModel(String name, int image, int quantity) {
        this.name = name;
        this.image = image;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
