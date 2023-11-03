package info.magnolia.extensibility.shopify.mapper;

import info.magnolia.extensibility.shopify.model.Item;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import com.shopify.model.ShopifyProduct;
@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA, uses = ImageMapper.class )
public interface ProductMapper {
    @Mappings(value = {
            @Mapping(expression = "java((product.getVariants()!=null && ! product.getVariants().isEmpty())?product.getVariants().get(0).getPrice().floatValue():null)", target = "price"),
            @Mapping(expression = "java((product.getVariants()!=null && ! product.getVariants().isEmpty())?product.getVariants().get(0).getInventoryQuantity():null)", target="quantity")
    })
    Item toDTO (ShopifyProduct product);
    List<Item> toDTO (List<ShopifyProduct> product);
    default OffsetDateTime map(String value){
        if (StringUtils.isNotEmpty(value)) {
            return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
        else{
            return null;
        }
    }
}
