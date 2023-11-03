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

package info.magnolia.extensibility.shopify.endpoints;

import info.magnolia.extensibility.shopify.model.Item;
import info.magnolia.extensibility.shopify.model.ItemsResponse;
import info.magnolia.extensibility.shopify.service.ShopifyService;

import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class ItemsApiImpl implements ItemsApi {

    private ShopifyService shopifyService;

    public ItemsApiImpl(ShopifyService shopifyService) {
        this.shopifyService = shopifyService;
    }

    @Override
    public Item getItem(String idItem) {
        return shopifyService.getItem(idItem);
    }

    @Override
    public ItemsResponse getItems() {
        return shopifyService.getItems();
    }
}
