package info.magnolia.extensibility.shopify.endpoints;

import info.magnolia.extensibility.shopify.client.ShopifyClient;
import info.magnolia.extensibility.shopify.model.Item;
import info.magnolia.extensibility.shopify.model.ItemsResponse;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class ItemsApiImpl implements ItemsApi {

    private ShopifyClient shopifyClient;

    public ItemsApiImpl(ShopifyClient shopifyClient) {
        this.shopifyClient = shopifyClient;
    }

    @Override
    public Item getItem(String idItem) {
        return shopifyClient.getItem(idItem);
    }

    @Override
    public ItemsResponse getItems() {
        return shopifyClient.getItems();
    }
}
