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

import info.magnolia.extensibility.shopify.model.Image;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;



@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface ImageMapper {
    List<String> map(List<Image> value);
    default String map(Image value){
        return value.getSrc();
    };
}
