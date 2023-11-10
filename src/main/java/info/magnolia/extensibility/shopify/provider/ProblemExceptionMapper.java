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
package info.magnolia.extensibility.shopify.provider;

import info.magnolia.extensibility.shopify.exception.ShopifyExtensionException;

import java.util.Optional;

import com.tietoevry.quarkus.resteasy.problem.ExceptionMapperBase;
import com.tietoevry.quarkus.resteasy.problem.HttpProblem;

import io.opentelemetry.api.trace.Span;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProblemExceptionMapper extends ExceptionMapperBase<ShopifyExtensionException> {

    private Span span;

    @Inject
    public ProblemExceptionMapper(Span span) {
        this.span = span;
    }

    public ProblemExceptionMapper() {
    }

    @Override
    protected HttpProblem toProblem(ShopifyExtensionException exception) {
        Response.StatusType status = Optional.ofNullable(exception.getStatus())
                .map(problemStatus -> Response.Status.fromStatusCode(problemStatus.getStatusCode()))
                .orElse(Response.Status.INTERNAL_SERVER_ERROR);

        HttpProblem.Builder builder = HttpProblem.builder()
                .with("trace_id", span.getSpanContext().getTraceId())
                .with("span_id", span.getSpanContext().getSpanId())
                .withTitle(exception.getTitle())
                .withStatus(status)
                .withDetail(exception.getDetail());
        return builder.build();
    }
}
