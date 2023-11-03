package info.magnolia.extensibility.shopify.client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class ShopifyClientTest {
    @Inject
    ShopifyClient shopifyClient;
    @Inject
    ObjectMapper objectMapper;

    @Test
    void getItems() {
        var items = shopifyClient.getItems();
        assertNotNull(items);
        assertTrue(!items.getItems().isEmpty());
        try {
            System.out.println(objectMapper.writeValueAsString(items));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getItem() {
        var item = shopifyClient.getItem("4494526611509");
        assertNotNull(item);
        try {
            System.out.println(objectMapper.writeValueAsString(item));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}