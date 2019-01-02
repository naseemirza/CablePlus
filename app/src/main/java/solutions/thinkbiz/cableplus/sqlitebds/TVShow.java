package solutions.thinkbiz.cableplus.sqlitebds;

/**
 * Created by User on 13-Dec-18.
 */

public class TVShow  {

    private String Pid;
    private String name;
    private String imageUrl;
    private String price;

    public TVShow() {
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
