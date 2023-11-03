package info.magnolia.extensibility.shopify.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "extension.shopify")
public interface ShopifyConfig {
    String accessToken();
    String domain();
}
