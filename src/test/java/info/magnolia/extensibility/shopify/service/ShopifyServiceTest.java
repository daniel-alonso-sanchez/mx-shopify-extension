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

package info.magnolia.extensibility.shopify.service;

import static org.junit.jupiter.api.Assertions.*;

import info.magnolia.extensibility.shopify.service.ShopifyService;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class ShopifyServiceTest {
    @Inject
    ShopifyService shopifyService;
    @Inject
    ObjectMapper objectMapper;

    @Test
    void getItems() {
        var items = shopifyService.getItems();
        assertNotNull(items);
        assertFalse(items.getItems().isEmpty());
        try {
            System.out.println(objectMapper.writeValueAsString(items));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getItem() {
        var item = shopifyService.getItem("4494526611509");
        assertNotNull(item);
    }
}