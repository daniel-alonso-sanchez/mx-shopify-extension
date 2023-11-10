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

package info.magnolia.extensibility.shopify.client;


import info.magnolia.extensibility.shopify.mapper.ExceptionMapper;
import info.magnolia.extensibility.shopify.model.ProductResponse;
import info.magnolia.extensibility.shopify.model.ProductsResponse;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;


@RegisterRestClient(configKey = "shopify")
@ClientHeaderParam(name = "X-Shopify-Access-Token", value = "{accessToken}")
public interface ShopifyClient {

    @GET
    @Path("/products.json")
    ProductsResponse getItems();
    @GET
    @Path("/products/{item_id}.json")
    ProductResponse getItem(@PathParam("item_id") String itemId);

    String EXTENSION_SHOPIFY_ACCESS_TOKEN_CONFIG_KEY = "extension.shopify.access-token";
    default String accessToken() {
        return ConfigProvider.getConfig().getValue(EXTENSION_SHOPIFY_ACCESS_TOKEN_CONFIG_KEY,String.class);
    }
    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        return ExceptionMapper.toException (response);
    }
}
