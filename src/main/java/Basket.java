import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Basket {

    private Long id;
    private Client client;
    private List<Product> products;

    public Basket(Client client) {
        this.client = client;
        this.products = new LinkedList<>();
    }
}