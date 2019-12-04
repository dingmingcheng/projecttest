package com.dmc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 11:01 AM 2019/4/19
 * @Modified By:
 */
public class gfs {

    public static void main(String[] args) throws Exception{
//        String str = "{\"latestShipDate\":\"1538399700000\",\"orderTotalAmountCurrency\":\"USD\",\"orderType\":\"StandardOrder\",\"purchaseDate\":\"1538344039946\",\"paymentExecutionDetail\":\"[]\",\"orderId\":\"113-8847792-5633869\",\"amazonOrderId\":\"113-8847792-5633869\",\"buyerEmail\":\"3gx1fqsp06kx6qs@marketplace.amazon.com\",\"lastUpdateDate\":\"1538366645258\",\"numberOfItemsShipped\":\"1\",\"shipServiceLevel\":\"Expedited\",\"orderStatus\":\"Shipped\",\"shopName\":\"A3DX6IPPS9017J\",\"salesChannel\":\"Amazon.com\",\"orderItems\":\"[{\\\"orderItemId\\\":\\\"47066964939458\\\",\\\"promotionIds\\\":\\\"[]\\\",\\\"quantityOrdered\\\":1,\\\"title\\\":\\\"EIGOAL Duvet Cover Set Forest Printed Comforter Cover with Zipper Closure Kids Bedding Set Soft Lightweight Microfiber Twin/Queen/King Size\\\",\\\"shippingTax\\\":\\\"{\\\\\\\"amount\\\\\\\":\\\\\\\"0.00\\\\\\\",\\\\\\\"currencyCode\\\\\\\":\\\\\\\"USD\\\\\\\",\\\\\\\"setAmount\\\\\\\":true,\\\\\\\"setCurrencyCode\\\\\\\":true}\\\",\\\"promotionDiscount\\\":\\\"{\\\\\\\"amount\\\\\\\":\\\\\\\"0.00\\\\\\\",\\\\\\\"currencyCode\\\\\\\":\\\\\\\"USD\\\\\\\",\\\\\\\"setAmount\\\\\\\":true,\\\\\\\"setCurrencyCode\\\\\\\":true}\\\",\\\"quantityShipped\\\":1,\\\"shippingPrice\\\":\\\"{\\\\\\\"amount\\\\\\\":\\\\\\\"2.00\\\\\\\",\\\\\\\"currencyCode\\\\\\\":\\\\\\\"USD\\\\\\\",\\\\\\\"setAmount\\\\\\\":true,\\\\\\\"setCurrencyCode\\\\\\\":true}\\\",\\\"itemPriceAmountCurrency\\\":\\\"USD\\\",\\\"itemPriceAmount\\\":25.99,\\\"asin\\\":\\\"B07F71VK25\\\",\\\"itemPrice\\\":\\\"{\\\\\\\"amount\\\\\\\":\\\\\\\"25.99\\\\\\\",\\\\\\\"currencyCode\\\\\\\":\\\\\\\"USD\\\\\\\",\\\\\\\"setAmount\\\\\\\":true,\\\\\\\"setCurrencyCode\\\\\\\":true}\\\",\\\"itemTax\\\":\\\"{\\\\\\\"amount\\\\\\\":\\\\\\\"2.62\\\\\\\",\\\\\\\"currencyCode\\\\\\\":\\\\\\\"USD\\\\\\\",\\\\\\\"setAmount\\\\\\\":true,\\\\\\\"setCurrencyCode\\\\\\\":true}\\\",\\\"sellerSKU\\\":\\\"NS-7841-Q-A\\\",\\\"shippingDiscount\\\":\\\"{\\\\\\\"amount\\\\\\\":\\\\\\\"2.00\\\\\\\",\\\\\\\"currencyCode\\\\\\\":\\\\\\\"USD\\\\\\\",\\\\\\\"setAmount\\\\\\\":true,\\\\\\\"setCurrencyCode\\\\\\\":true}\\\"}]\",\"platform\":\"AMAZON_NA\",\"shippedByAmazonTFM\":\"false\",\"isBusinessOrder\":\"false\",\"sellerId\":\"A3DX6IPPS9017J\",\"numberOfItemsUnshipped\":\"0\",\"orderTotalAmount\":\"28.61\",\"shippingAddressCountryCode\":\"US\",\"prime\":\"false\",\"shippingAddressCity\":\"SEATTLE\",\"shippingAddressStateOrRegion\":\"WA\",\"orderItemNum\":\"1\",\"buyerName\":\"Sylvie\",\"orderTotal\":\"{\\\"amount\\\":\\\"28.61\\\",\\\"currencyCode\\\":\\\"USD\\\",\\\"setAmount\\\":true,\\\"setCurrencyCode\\\":true}\",\"earliestShipDate\":\"1538399700000\",\"marketplaceId\":\"ATVPDKIKX0DER\",\"fulfillmentChannel\":\"AFN\",\"paymentMethod\":\"Other\",\"shippingAddress\":\"{\\\"addressLine1\\\":\\\"108 NW 58TH ST\\\",\\\"city\\\":\\\"SEATTLE\\\",\\\"countryCode\\\":\\\"US\\\",\\\"name\\\":\\\"Sylvie Ghesquiere\\\",\\\"postalCode\\\":\\\"98107-2027\\\",\\\"setAddressLine1\\\":true,\\\"setAddressLine2\\\":false,\\\"setAddressLine3\\\":false,\\\"setCity\\\":true,\\\"setCountryCode\\\":true,\\\"setCounty\\\":false,\\\"setDistrict\\\":false,\\\"setName\\\":true,\\\"setPhone\\\":false,\\\"setPostalCode\\\":true,\\\"setStateOrRegion\\\":true,\\\"stateOrRegion\\\":\\\"WA\\\"}\",\"sellerOrderId\":\"113-8847792-5633869\",\"shipmentServiceLevelCategory\":\"Expedited\"}";
//        final JSONObject object = JSONObject.parseObject(str);
////        object.keySet().stream().forEach(System.out::print);
//        object.keySet().forEach(key -> {
//            System.out.println(object.getString(key));
//        });
//        processFile();
//        processDate();
//        test();
        test2();
    }
    //latestShipDate orderTotalAmountCurrency

    public static void test2() {
        String str = "{\"code\":0,\"data\":[{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"2016fashionlife2016\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1540552052000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9826,\"lastOrderFetchTime\":1556324301000,\"modified\":1556356706000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"2016fashionlife2016\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"2018bestbuy123\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1534476215000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":6600,\"lastOrderFetchTime\":1552567112000,\"modified\":1552599526000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"2018bestbuy123\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"5_star_choice\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065299000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9327,\"lastOrderFetchTime\":1550739148000,\"modified\":1550771557000,\"modifier\":\"\",\"remark\":\"{\\\"timeoutCount\\\":\\\"1\\\",\\\"updateModified\\\":true}\",\"sellerId\":\"5_star_choice\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"acumste\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1536755036000,\"creator\":\"\",\"firstSyncDate\":1527241480000,\"flag\":1,\"id\":8413,\"lastOrderFetchTime\":1550739135000,\"modified\":1550771550000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"acumste\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"ansi-aj\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1544752791000,\"creator\":\"\",\"firstSyncDate\":1532698501000,\"flag\":1,\"id\":11922,\"lastOrderFetchTime\":1550656150000,\"modified\":1550688631000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"ansi-aj\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"ansi-luy\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1543837444000,\"creator\":\"\",\"firstSyncDate\":1531745004000,\"flag\":1,\"id\":11514,\"lastOrderFetchTime\":1550658414000,\"modified\":1550690860000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"ansi-luy\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"auto-accessories\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065299000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9331,\"lastOrderFetchTime\":1551456411000,\"modified\":1551488875000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"auto-accessories\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"auto-part-1\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065311000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9332,\"lastOrderFetchTime\":1556378327000,\"modified\":1556410785000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"auto-part-1\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"autolife-6666\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542279953000,\"creator\":\"\",\"firstSyncDate\":1530184560000,\"flag\":1,\"id\":10332,\"lastOrderFetchTime\":1556379622000,\"modified\":1556412025000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"autolife-6666\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"autopartsmarket-en\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065311000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9335,\"lastOrderFetchTime\":1550613152000,\"modified\":1550645585000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"autopartsmarket-en\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"berttty\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1543837285000,\"creator\":\"\",\"firstSyncDate\":1531741386000,\"flag\":1,\"id\":11301,\"lastOrderFetchTime\":1556315176000,\"modified\":1556347578000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"berttty\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"bestbuybuy2018\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1534476215000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":6599,\"lastOrderFetchTime\":1552566743000,\"modified\":1552599147000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"bestbuybuy2018\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"car-parts-pro\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1534476216000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":6695,\"lastOrderFetchTime\":1556363297000,\"modified\":1556395698000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"car-parts-pro\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"carry-wu\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065290000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9302,\"lastOrderFetchTime\":1556377223000,\"modified\":1556409627000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"carry-wu\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"cellingwet\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542028997000,\"creator\":\"\",\"firstSyncDate\":1529933243000,\"flag\":1,\"id\":10246,\"lastOrderFetchTime\":1556316870000,\"modified\":1556349275000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"cellingwet\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"charming202018\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065290000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9305,\"lastOrderFetchTime\":1556377479000,\"modified\":1556409884000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"charming202018\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"cruwells_3\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1534476215000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":6611,\"lastOrderFetchTime\":1556377253000,\"modified\":1556409679000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"cruwells_3\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"cuvious\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1543837285000,\"creator\":\"\",\"firstSyncDate\":1531741386000,\"flag\":1,\"id\":11302,\"lastOrderFetchTime\":1556315175000,\"modified\":1556347577000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"cuvious\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"doraemon-auto\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542195206000,\"creator\":\"\",\"firstSyncDate\":1530100020000,\"flag\":1,\"id\":10307,\"lastOrderFetchTime\":1556316858000,\"modified\":1556349261000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"doraemon-auto\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"dreamtrees\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065295000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9321,\"lastOrderFetchTime\":1556363535000,\"modified\":1556395936000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"dreamtrees\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"eplus2017\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1536714434000,\"creator\":\"\",\"firstSyncDate\":1527242074000,\"flag\":1,\"id\":8287,\"lastOrderFetchTime\":1550620466000,\"modified\":1550707485000,\"modifier\":\"\",\"remark\":\"{\\\"timeoutCount\\\":\\\"3\\\",\\\"updateModified\\\":true}\",\"sellerId\":\"eplus2017\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"gileske\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065299000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9328,\"lastOrderFetchTime\":1552298904000,\"modified\":1552331308000,\"modifier\":\"\",\"remark\":\"{\\\"timeoutCount\\\":\\\"1\\\",\\\"updateModified\\\":true}\",\"sellerId\":\"gileske\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"goodbuyal\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065284000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9301,\"lastOrderFetchTime\":1552848015000,\"modified\":1552880417000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"goodbuyal\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"goodtripforyou\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1540810738000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9852,\"lastOrderFetchTime\":1556360428000,\"modified\":1556392832000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"goodtripforyou\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"hebby-wu\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1534476215000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":6613,\"lastOrderFetchTime\":1556377478000,\"modified\":1556409890000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"hebby-wu\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"himan-zhang-2012\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065299000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9330,\"lastOrderFetchTime\":1550598408000,\"modified\":1550630815000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"himan-zhang-2012\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"hyfuly\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1540810746000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9859,\"lastOrderFetchTime\":1552489345000,\"modified\":1552521748000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"hyfuly\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"jackous\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1540810746000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9858,\"lastOrderFetchTime\":1556320109000,\"modified\":1556352518000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"jackous\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"jigaid\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542635376000,\"creator\":\"\",\"firstSyncDate\":1530539522000,\"flag\":1,\"id\":10583,\"lastOrderFetchTime\":1552911031000,\"modified\":1552943444000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"jigaid\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"kang-xiaoming\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1541164450000,\"creator\":\"\",\"firstSyncDate\":1529380203000,\"flag\":1,\"id\":10009,\"lastOrderFetchTime\":1552296071000,\"modified\":1552458896000,\"modifier\":\"\",\"remark\":\"{\\\"timeoutCount\\\":\\\"1166\\\",\\\"updateModified\\\":true}\",\"sellerId\":\"kang-xiaoming\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"layinga\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542635376000,\"creator\":\"\",\"firstSyncDate\":1530539522000,\"flag\":1,\"id\":10585,\"lastOrderFetchTime\":1552910058000,\"modified\":1552942465000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"layinga\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"linglingsea\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065290000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9303,\"lastOrderFetchTime\":1556377717000,\"modified\":1556410134000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"linglingsea\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"lipower03\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1534476215000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":6614,\"lastOrderFetchTime\":1556364161000,\"modified\":1556396565000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"lipower03\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"liraino\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542279953000,\"creator\":\"\",\"firstSyncDate\":1530184445000,\"flag\":1,\"id\":10331,\"lastOrderFetchTime\":1552707859000,\"modified\":1552740260000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"liraino\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"lussue\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1541502672000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":10072,\"lastOrderFetchTime\":1552490298000,\"modified\":1552522699000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"lussue\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"nerlous\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542279963000,\"creator\":\"\",\"firstSyncDate\":1530184502000,\"flag\":1,\"id\":10348,\"lastOrderFetchTime\":1556379737000,\"modified\":1556412140000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"nerlous\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"new-decor-led\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1543837285000,\"creator\":\"\",\"firstSyncDate\":1531741386000,\"flag\":1,\"id\":11303,\"lastOrderFetchTime\":1556315899000,\"modified\":1556348300000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"new-decor-led\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"nullitle\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1541502696000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":10116,\"lastOrderFetchTime\":1556319991000,\"modified\":1556352400000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"nullitle\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"oepkoy\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1540810746000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9857,\"lastOrderFetchTime\":1552489455000,\"modified\":1552521857000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"oepkoy\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"outiousy\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542279948000,\"creator\":\"\",\"firstSyncDate\":1530184441000,\"flag\":1,\"id\":10320,\"lastOrderFetchTime\":1556380234000,\"modified\":1556412639000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"outiousy\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"partstore-us\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542029006000,\"creator\":\"\",\"firstSyncDate\":1529933102000,\"flag\":1,\"id\":10265,\"lastOrderFetchTime\":1556316975000,\"modified\":1556349390000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"partstore-us\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"pencil-88\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065299000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9329,\"lastOrderFetchTime\":1551019734000,\"modified\":1551052151000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"pencil-88\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"pueri-pu\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065311000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9334,\"lastOrderFetchTime\":1552580897000,\"modified\":1552613311000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"pueri-pu\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"pulicom-us\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1540810738000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9856,\"lastOrderFetchTime\":1556319667000,\"modified\":1556352128000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"pulicom-us\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"pulipart\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1540810738000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9855,\"lastOrderFetchTime\":1556358627000,\"modified\":1556391048000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"pulipart\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"ronda-carpart\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539065311000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":9333,\"lastOrderFetchTime\":1550752842000,\"modified\":1550785287000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"ronda-carpart\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"rooleyery\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542373560000,\"creator\":\"\",\"firstSyncDate\":1530277823000,\"flag\":1,\"id\":10424,\"lastOrderFetchTime\":1556317848000,\"modified\":1556350270000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"rooleyery\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"sawseety\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1542635371000,\"creator\":\"\",\"firstSyncDate\":1530539520000,\"flag\":1,\"id\":10574,\"lastOrderFetchTime\":1556267581000,\"modified\":1556397480000,\"modifier\":\"\",\"remark\":\"{\\\"timeoutCount\\\":\\\"6\\\",\\\"updateModified\\\":true}\",\"sellerId\":\"sawseety\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"shoptopstar\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1540552056000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9833,\"lastOrderFetchTime\":1556381177000,\"modified\":1556413601000,\"modifier\":\"\",\"remark\":\"{\\\"timeoutCount\\\":\\\"2\\\",\\\"updateModified\\\":true}\",\"sellerId\":\"shoptopstar\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"sportsclubs\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1540810738000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9851,\"lastOrderFetchTime\":1556324186000,\"modified\":1556356595000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"sportsclubs\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"superidol2018\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1534476215000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":6612,\"lastOrderFetchTime\":1550611233000,\"modified\":1550643634000,\"modifier\":\"\",\"remark\":\"{\\\"timeoutCount\\\":\\\"1\\\",\\\"updateModified\\\":true}\",\"sellerId\":\"superidol2018\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"superying2018\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1536714403000,\"creator\":\"\",\"firstSyncDate\":1527242989000,\"flag\":1,\"id\":8231,\"lastOrderFetchTime\":1550611941000,\"modified\":1550644374000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"superying2018\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"terrific-wang\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1534476216000,\"creator\":\"\",\"firstSyncDate\":1522512000000,\"flag\":1,\"id\":6694,\"lastOrderFetchTime\":1539842385000,\"modified\":1541494104000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"terrific-wang\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"themestown\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1540552056000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9831,\"lastOrderFetchTime\":1556319058000,\"modified\":1556351464000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"themestown\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"vuryew\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1543837291000,\"creator\":\"\",\"firstSyncDate\":1531741386000,\"flag\":1,\"id\":11305,\"lastOrderFetchTime\":1550657667000,\"modified\":1550690075000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"vuryew\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"wonderfullife2016\",\"channelStatus\":\"NEW_CHANGEORDER\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1539953770000,\"creator\":\"\",\"firstSyncDate\":1528560000000,\"flag\":1,\"id\":9713,\"lastOrderFetchTime\":1556358980000,\"modified\":1556391380000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"wonderfullife2016\",\"weight\":0.00},{\"allOrderRetrieved\":false,\"authorizationArguments\":\"\",\"bizType\":\"DEFAULT\",\"channelName\":\"yurgle\",\"channelStatus\":\"INVALID_GRANT\",\"channelType\":\"EBAY\",\"clientId\":\"161019154658183\",\"created\":1545048429000,\"creator\":\"\",\"firstSyncDate\":1532953201000,\"flag\":1,\"id\":12094,\"lastOrderFetchTime\":1552283904000,\"modified\":1552316306000,\"modifier\":\"\",\"remark\":\"{\\\"updateModified\\\":true}\",\"sellerId\":\"yurgle\",\"weight\":0.00}]}";
        JSONObject object = JSONObject.parseObject(str);
        JSONArray array = object.getJSONArray("data");
        for (int i = 0;i < array.size(); i++) {
            JSONObject object1 = array.getJSONObject(i);
            String sellerId = object1.getString("sellerId");
            String channelStatus = object1.getString("channelStatus");
            if (channelStatus.equalsIgnoreCase("INVALID_GRANT")) {
                System.out.println(sellerId);
            }
        }
    }
    public static void processFile() {
        String str = "latestShipDate\torderTotalAmountCurrency\torderType\tpurchaseDate\tpaymentExecutionDetail\torderId\tamazonOrderId\tbuyerEmail\tlastUpdateDate\tnumberOfItemsShipped\tshipServiceLevel\torderStatus\tshopName\tsalesChannel\torderItems\tplatform\tshippedByAmazonTFM\tisBusinessOrder\tsellerId\tnumberOfItemsUnshipped\torderTotalAmount\tshippingAddressCountryCode\tprime\tshippingAddressCity\tshippingAddressStateOrRegion\torderItemNum\tbuyerName\torderTotal\tearliestShipDate\tmarketplaceId\tfulfillmentChannel\tpaymentMethod\tsellerOrderId\tshipmentServiceLevelCategory";
        File file = new File("/Users/dingmc/Desktop/ordertest");
        List<String> header = Arrays.asList(str.split("\t"));
        List<Map<String, String>> content = Lists.newArrayList();
        try (FileReader reader = new FileReader(file);
            BufferedReader reader1 = new BufferedReader(reader)) {
            String line;
            while ((line = reader1.readLine()) != null) {
                Map<String, String> map = Maps.newHashMap();
                JSONObject obj = JSONObject.parseObject(line);
                header.forEach(header1 -> {
                    if (StringUtils.isNotBlank(obj.getString(header1))) {
                        map.put(header1, obj.getString(header1));
                    }
                });
                content.add(map);
            }
            ExcelUtil.generateXML(header, content, "/Users/dingmc/Desktop/amazonorders.xlsx");
        } catch (Exception e) {

        } finally {

        }
    }

    public static void processDate() throws Exception{
        String str = "1537916399000\n"
            + "1550534399000\n"
            + "1549151999000\n"
            + "1543017599000\n"
            + "1546473599000\n"
            + "1534373999000\n"
            + "1552431599000\n"
            + "1546041599000\n"
            + "1543449599000\n"
            + "1540076399000\n"
            + "1545004799000\n"
            + "1537138799000\n"
            + "1545350399000\n"
            + "1541462399000\n"
            + "1546905599000\n"
            + "1534114800000\n"
            + "1538434799000\n"
            + "1555369199000\n"
            + "1541548799000\n"
            + "1542067199000\n"
            + "1543535999000\n"
            + "1544313599000\n"
            + "1547510399000\n"
            + "1541721599000\n"
            + "1532300399000\n"
            + "1535669999000\n"
            + "1551484799000\n"
            + "1543103999000\n"
            + "1551225599000\n"
            + "1539471599000\n"
            + "1554159599000\n"
            + "1537052399000\n"
            + "1549324799000\n"
            + "1530831599000\n"
            + "1543190399000\n"
            + "1537225199000\n"
            + "1536069600000\n"
            + "1531954799000\n"
            + "1538002799000\n"
            + "1542499199000\n"
            + "1543276799000\n"
            + "1540422000000\n"
            + "1551571199000\n"
            + "1552949999000\n"
            + "1551743999000\n"
            + "1544399999000\n"
            + "1544140799000\n"
            + "1541462399000\n"
            + "1535151599000\n"
            + "1543708799000\n"
            + "1542931199000\n"
            + "1541807999000\n"
            + "1546819199000\n"
            + "1549324799000\n"
            + "1540853999000\n"
            + "1547942399000\n"
            + "1535669999000\n"
            + "1540940399000\n"
            + "1536274799000\n"
            + "1536101999000\n"
            + "1545955199000\n"
            + "1545909300000\n"
            + "1538348399000\n"
            + "1552003199000\n"
            + "1549324799000\n"
            + "1545695999000\n"
            + "1537916399000\n"
            + "1537225199000\n"
            + "1547164799000\n"
            + "1554073199000\n"
            + "1554245999000\n"
            + "1541285999000\n"
            + "1538866799000\n"
            + "1549324799000\n"
            + "1535410799000\n"
            + "1551398399000\n"
            + "1545782399000\n"
            + "1536101999000\n"
            + "1544918399000\n"
            + "1539385199000\n"
            + "1537484399000\n"
            + "1535929199000\n"
            + "1537052399000\n"
            + "1543363199000\n"
            + "1536447599000\n"
            + "1533250799000\n"
            + "1536534000000\n"
            + "1537138799000\n"
            + "1536706799000\n"
            + "1533682800000\n"
            + "1533596399000\n"
            + "1539298799000\n"
            + "1532559600000\n"
            + "1538002800000\n"
            + "1535756399000\n"
            + "1537311599000\n"
            + "1545436799000\n"
            + "1535929199000\n"
            + "1543622399000\n"
            + "1539385199000\n"
            + "1548460799000";
        String[] strings = str.split("\n");
        for (String s : strings) {
            Date date = new Date(Long.valueOf(s));
            DateTime time = new DateTime(date);
            System.out.println(time.toString("yyyy-MM-dd HH:mm:ss"));
        }
    }

    public static void test() throws Exception{
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(5);
//        queue.peek();

//        queue.take();//阻塞
//        queue.poll();//非阻塞,如果没有则返回null

//        queue.put();//阻塞
//        queue.offer();//非阻塞
        for (int i = 0; i < 5; i ++) {
            System.out.println(queue.offer(i));
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(queue.take());
        }
        System.out.println(queue.size());

    }
}
