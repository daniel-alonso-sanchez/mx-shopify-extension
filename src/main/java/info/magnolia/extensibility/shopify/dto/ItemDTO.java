package info.magnolia.extensibility.shopify.dto;

import java.math.BigDecimal;
import java.util.List;

public record ItemDTO(String id, String title, List<String> images, String publishedAt, String vendor, BigDecimal price, Long quantity) {
}
