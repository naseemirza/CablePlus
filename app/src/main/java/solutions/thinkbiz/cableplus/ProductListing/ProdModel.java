package solutions.thinkbiz.cableplus.ProductListing;

/**
 * Created by User on 20-Nov-18.
 */

public class ProdModel {

    private String Pid;
    private String name;
    private String stock;
    private String spool;
    private String image;
    private String count;

    public ProdModel(String Pid, String name, String stock, String spool, String image, String count) {
        this.Pid = Pid;
        this.name = name;
        this.stock = stock;
        this.spool = spool;
        this.image = image;
        this.count = count;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String Pid) {
        this.Pid = Pid;
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

    public String getSpool() {
        return spool;
    }

    public void setSpool(String spool) {
        this.spool = spool;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
