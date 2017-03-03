package dk.cs.dwebtek;

import dk.cs.au.dwebtek.CloudService;
import dk.cs.au.dwebtek.OperationResult;
import org.jdom2.Document;
import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("shop")
public class ShopService {
    /**
     * Our Servlet session. We will need this for the shopping basket
     */
    HttpSession session;
    private static CloudService service = new CloudService();
    public ShopService(@Context HttpServletRequest servletRequest) {
        session = servletRequest.getSession();
    }

    /**
     * Make the price increase per request (for the sake of example)
     */
    private static int priceChange = 0;

    @GET
    @Path("items")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        OperationResult<Document> res = service.listItems();
        if(res.isSuccess()) {
            List<Element> itemList = res.getResult().getRootElement().getChildren();
            for(Element e : itemList) {
                items.add(getItemFromElement(e));
            }
        } else {
            System.out.println(res.getMessage());
        }

        //You should get the items from the cloud server.
        //In the template we just construct some simple data as an array of objects
        // Here we have the JSON automated automatically by having models.
        return items;
    }

    public Item getItemFromElement(Element e) {
        int id = Integer.parseInt(e.getChildText("itemID"));
        String name = e.getChildText("itemName");
        String URL = e.getChildText("itemURL");
        int price = Integer.parseInt(e.getChildText("itemPrice"));
        int stock = Integer.parseInt(e.getChildText("itemStock"));
        String description = e.getChildText("itemDescription");
        return new Item(id, name, URL, price, stock, description);
    }


    @GET
    @Path("items/manual")
    public String getItemsConstructedManually() {
        //You should get the items from the cloud server.
        //In the template we just construct some simple data as an array of objects
        //Here we output construct the JSON manually
        JSONArray array = new JSONArray();

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("id", 1);
        jsonObject1.put("name", "Stetson hat");
        jsonObject1.put("price", 200 + priceChange);
        array.put(jsonObject1);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("id", 2);
        jsonObject2.put("name", "Rifle");
        jsonObject2.put("price", 500 + priceChange);
        array.put(jsonObject2);

        priceChange++;

        //You can create a MessageBodyWriter so you don't have to call toString() every time
        return array.toString();
    }


}
