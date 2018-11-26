package solutions.thinkbiz.cableplus;

/**
 * Created by User on 26-Nov-18.
 */

public class OrderPageModel {

    private String name;
    private int image;
    private int count;

    public OrderPageModel(String name, int image, int count) {
        this.name = name;

        this.image = image;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
