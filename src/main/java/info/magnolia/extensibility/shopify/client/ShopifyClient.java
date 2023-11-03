package info.magnolia.extensibility.shopify.client;

import info.magnolia.extensibility.shopify.config.ShopifyConfig;

import info.magnolia.extensibility.shopify.mapper.ProductMapper;
import info.magnolia.extensibility.shopify.model.Item;
import info.magnolia.extensibility.shopify.model.ItemsResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shopify.ShopifySdk;
import com.shopify.model.ShopifyProduct;
import com.shopify.model.ShopifyProducts;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ShopifyClient {
    private final ShopifyConfig shopifyConfig;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ProductMapper productMapper;
    @Inject
    public ShopifyClient(ProductMapper productMapper, ShopifyConfig shopifyConfig) {
        this.productMapper = productMapper;
        this.shopifyConfig = shopifyConfig;
    }

    public ItemsResponse getItems() {
        final ShopifySdk shopifySdk = ShopifySdk.newBuilder()
                .withSubdomain(shopifyConfig.domain())
                .withAccessToken(shopifyConfig.accessToken()).build();

        final ShopifyProducts products = shopifySdk.getProducts();
        return new ItemsResponse().items(productMapper.toDTO(products.values()));
    }
    public Item getItem(String itemId) {
        final ShopifySdk shopifySdk = ShopifySdk.newBuilder()
                .withSubdomain(shopifyConfig.domain())
                .withAccessToken(shopifyConfig.accessToken()).build();

        final ShopifyProduct product = shopifySdk.getProduct(itemId);

        return productMapper.toDTO(product);
    }

}
