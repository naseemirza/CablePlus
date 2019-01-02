package solutions.thinkbiz.cableplus.History;

/**
 * Created by User on 28-Dec-18.
 */

public class HistoryModel {

    private String image;
    private String name;
    private String quantity;

    public HistoryModel(String image, String name, String quantity) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
