package dk.cs.dwebtek;

/**
 * Created by mortenkrogh-jespersen on 28/02/2017.
 */
public class Item {

    private int id;
    private String name;
    private int price;
    private String URL;
    private int stock;
    private String description;

    public Item(int id, String name, String URL, int price, int stock, String description) {
        this.id = id;
        this.name = name;
        this.URL = URL;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
