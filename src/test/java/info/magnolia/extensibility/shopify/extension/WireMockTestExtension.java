package info.magnolia.extensibility.shopify.extension;

import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockTestExtension implements QuarkusTestResourceLifecycleManager {
    private static final String GET_ITEM_RESPONSE_OK = """
            {
                  "id": 4494451802165,
                  "title": "Deuter Aircontact 45 + 10 Backpack",
                  "body_html": "<meta charset=\\"utf-8\\"><meta charset=\\"utf-8\\">\\n<p><span>Our Aircontact trekking backpack has won the title of “indestructible”. Therefore, we have opted for gentle changes: It is extremely robust, but now it is even neater, more modern and streamlined when trekking. And even more comfortable.</span></p>\\n<table>\\n<tbody>\\n<tr>\\n<th class=\\"text-nowrap\\">Colour:</th>\\n<td class=\\"pl-2 pl-md-4 w-100\\">midnight-navy</td>\\n</tr>\\n<tr>\\n<th class=\\"text-nowrap\\">GTIN:</th>\\n<td class=\\"pl-2 pl-md-4 w-100\\">4046051096261</td>\\n</tr>\\n<tr>\\n<th class=\\"text-nowrap\\">Weight:</th>\\n<td class=\\"pl-2 pl-md-4 w-100\\">2290 g</td>\\n</tr>\\n<tr>\\n<th class=\\"text-nowrap\\">Volume:</th>\\n<td class=\\"pl-2 pl-md-4 w-100\\">45 Liter</td>\\n</tr>\\n<tr>\\n<th class=\\"text-nowrap\\">Dimensions:</th>\\n<td class=\\"pl-2 pl-md-4 w-100\\">78 / 29 / 22 (L x W x D) cm</td>\\n</tr>\\n</tbody>\\n</table>",
                  "vendor": "MagBoats",
                  "product_type": "",
                  "created_at": "2020-03-23T15:09:05-04:00",
                  "handle": "aircontact-45-10-backpack",
                  "updated_at": "2023-10-23T18:28:21-04:00",
                  "published_at": "2020-03-23T12:41:47-04:00",
                  "template_suffix": "",
                  "published_scope": "web",
                  "tags": "",
                  "status": "active",
                  "admin_graphql_api_id": "gid://shopify/Product/4494451802165",
                  "variants": [
                    {
                      "id": 31775365726261,
                      "product_id": 4494451802165,
                      "title": "Default Title",
                      "price": "209.00",
                      "sku": "",
                      "position": 1,
                      "inventory_policy": "deny",
                      "compare_at_price": null,
                      "fulfillment_service": "manual",
                      "inventory_management": "shopify",
                      "option1": "Default Title",
                      "option2": null,
                      "option3": null,
                      "created_at": "2020-03-23T15:09:05-04:00",
                      "updated_at": "2023-10-23T18:28:21-04:00",
                      "taxable": true,
                      "barcode": "",
                      "grams": 0,
                      "image_id": null,
                      "weight": 0,
                      "weight_unit": "kg",
                      "inventory_item_id": 33453185105973,
                      "inventory_quantity": 22,
                      "old_inventory_quantity": 22,
                      "requires_shipping": true,
                      "admin_graphql_api_id": "gid://shopify/ProductVariant/31775365726261"
                    }
                  ],
                  "options": [
                    {
                      "id": 5867603329077,
                      "product_id": 4494451802165,
                      "name": "Title",
                      "position": 1,
                      "values": [
                        "Default Title"
                      ]
                    }
                  ],
                  "images": [
                    {
                      "id": 14506145513525,
                      "alt": null,
                      "position": 1,
                      "product_id": 4494451802165,
                      "created_at": "2020-03-23T15:09:07-04:00",
                      "updated_at": "2020-03-23T15:09:07-04:00",
                      "admin_graphql_api_id": "gid://shopify/ProductImage/14506145513525",
                      "width": 995,
                      "height": 995,
                      "src": "https://cdn.shopify.com/s/files/1/0264/3561/6821/products/deuter-aircontact-45-10.jpg?v=1584990547",
                      "variant_ids": []
                    }
                  ],
                  "image": {
                    "id": 14506145513525,
                    "alt": null,
                    "position": 1,
                    "product_id": 4494451802165,
                    "created_at": "2020-03-23T15:09:07-04:00",
                    "updated_at": "2020-03-23T15:09:07-04:00",
                    "admin_graphql_api_id": "gid://shopify/ProductImage/14506145513525",
                    "width": 995,
                    "height": 995,
                    "src": "https://cdn.shopify.com/s/files/1/0264/3561/6821/products/deuter-aircontact-45-10.jpg?v=1584990547",
                    "variant_ids": []
                  }
               }
            """;
    private static final String GET_ITEMS_RESPONSE_OK= String.format("""
            {
              "products": [
                %s                
              ]
            }
            """, GET_ITEM_RESPONSE_OK);
    private static final String GET_ITEM_ROOT_RESPONSE_OK= String.format("""
            {
              "product":
                %s
            }
            """, GET_ITEM_RESPONSE_OK);
    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(0);

        wireMockServer.start();

        wireMockServer.stubFor(
                get(urlEqualTo("/products.json"))

                        .withHeader("X-Shopify-Access-Token", equalTo("dummy-token"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                                GET_ITEMS_RESPONSE_OK
                        )));

        wireMockServer.stubFor(
                get(urlEqualTo("/products/4494526611509.json"))
                        .withHeader("X-Shopify-Access-Token", equalTo("dummy-token"))
                        .willReturn(aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBody(
                                        GET_ITEM_ROOT_RESPONSE_OK
                                )));
        wireMockServer.stubFor(

                get(urlEqualTo("/products/non_existing_id.json"))
                        .withHeader("X-Shopify-Access-Token", equalTo("dummy-token"))
                        .willReturn(aResponse()
                                .withStatus(404)));

        wireMockServer.stubFor(

                get(urlEqualTo("/products/4494526611509.json"))
                        .withHeader("X-Shopify-Access-Token", equalTo("non-valid-token"))
                        .willReturn(aResponse()
                                .withStatus(404)));

        return Map.of(
                "quarkus.rest-client.shopify.url", wireMockServer.baseUrl(),
                "extension.shopify.access-token","dummy-token"
                );
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}