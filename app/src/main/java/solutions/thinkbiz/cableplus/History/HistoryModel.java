package solutions.thinkbiz.cableplus.History;

/**
 * Created by User on 28-Dec-18.
 */

public class HistoryModel {

    private String image;
    private String name;
    private String quantity;
    private String date;

    public HistoryModel(String image, String name, String quantity, String date) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
