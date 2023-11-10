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

import info.magnolia.extensibility.shopify.exception.NotAuthorizedException;
import info.magnolia.extensibility.shopify.exception.NotFoundException;
import info.magnolia.extensibility.shopify.exception.ShopifyExtensionException;

import java.util.Map;
import java.util.function.Function;

import jakarta.ws.rs.core.Response;

public interface ExceptionMapper {
    Map<Integer, Function<String, ShopifyExtensionException>> EXCEPTION_MAP= Map.of(
            Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), desc -> new ShopifyExtensionException("There was a problem calling shopify api", desc, Response.Status.INTERNAL_SERVER_ERROR),
            Response.Status.UNAUTHORIZED.getStatusCode(), desc -> new NotAuthorizedException("There was an authorization problem calling shopify api", desc),
            Response.Status.NOT_FOUND.getStatusCode(), desc -> new NotFoundException("Product not found", desc)
            );
    static ShopifyExtensionException toException(Response response) {
        return EXCEPTION_MAP.getOrDefault(response.getStatus(), (desc -> new ShopifyExtensionException("There was a problem calling shopify api", desc, Response.Status.INTERNAL_SERVER_ERROR, null))).apply(response.readEntity(String.class));
    }
}
