/*
 * This file Copyright (c) 2023 Magnolia International
 * Ltd.  (http://www.magnolia-cms.com). All rights reserved.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Magnolia Network Agreement
 * which accompanies this distribution, and is available at
 * http://www.magnolia-cms.com/mna.html
 *
 * Any modifications to this file must keep this entire header
 * intact.
 *
 */

package info.magnolia.extensibility.shopify.mapper;

import info.magnolia.extensibility.shopify.model.Item;
import info.magnolia.extensibility.shopify.model.Product;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA, uses = ImageMapper.class )
public interface ProductMapper {

    List<Item> toDTO (List<Product> product);
    @Mappings(value = {
            @Mapping(expression = "java((product.getVariants()!=null && ! product.getVariants().isEmpty())?product.getVariants().get(0).getPrice().floatValue():null)", target = "price"),
            @Mapping(expression = "java((product.getVariants()!=null && ! product.getVariants().isEmpty())?product.getVariants().get(0).getInventoryQuantity():null)", target="quantity")
    })
    Item toDTO (Product product);
    default OffsetDateTime map(String value){
        if (value != null && value.length() > 0) {
            return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
        else{
            return null;
        }
    }
}
