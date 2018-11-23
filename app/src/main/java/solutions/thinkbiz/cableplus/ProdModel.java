package solutions.thinkbiz.cableplus;

/**
 * Created by User on 20-Nov-18.
 */

public class ProdModel {

    private String name;
    private String stock;
    private int image;
    private int count;

    public ProdModel(String name,String stock, int image,int count) {
        this.name = name;
        this.stock = stock;
        this.image = image;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
