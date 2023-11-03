
package info.magnolia.extensibility.shopify.service;

import info.magnolia.extensibility.shopify.client.ShopifyClient;
import info.magnolia.extensibility.shopify.mapper.ProductMapper;
import info.magnolia.extensibility.shopify.model.Item;
import info.magnolia.extensibility.shopify.model.ItemsResponse;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ShopifyService {
    private final ShopifyClient shopifyClient;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ProductMapper productMapper;
    @Inject
    public ShopifyService(
                @RestClient ShopifyClient shopifyClient, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.shopifyClient =  shopifyClient;
    }

    public ItemsResponse getItems() {
        return new ItemsResponse().items(productMapper.toDTO(shopifyClient.getItems().getProducts()));
    }
    public Item getItem(String itemId) {
        logger.debug("Calling get product by id: {}", itemId);
        return productMapper.toDTO(shopifyClient.getItem(itemId));
    }

}
