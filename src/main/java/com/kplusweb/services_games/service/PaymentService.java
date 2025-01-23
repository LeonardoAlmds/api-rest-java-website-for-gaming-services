package com.kplusweb.services_games.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kplusweb.services_games.config.PagSeguroConfig;
import com.kplusweb.services_games.dtos.OrderDTO;

import java.util.List;

@Service
public class PaymentService {
    private final PagSeguroConfig pagSeguroConfig;

    public PaymentService(PagSeguroConfig pagSeguroConfig) {
        this.pagSeguroConfig = pagSeguroConfig;
    }

    public String createPayment(OrderDTO order) throws Exception {
        String url = pagSeguroConfig.getBaseUrl() + "/v2/checkout";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);

            List<BasicNameValuePair> params = List.of(
                    new BasicNameValuePair("email", pagSeguroConfig.getEmail()),
                    new BasicNameValuePair("token", pagSeguroConfig.getToken()),
                    new BasicNameValuePair("currency", "BRL"),
                    new BasicNameValuePair("itemId1", order.productId().toString()),
                    new BasicNameValuePair("itemDescription1", "Produto: " + order.productId()),
                    new BasicNameValuePair("itemAmount1", "100.00"), // Exemplo de valor
                    new BasicNameValuePair("itemQuantity1", "1")
            );

            httpPost.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse response = httpClient.execute(httpPost);

            if (response.getCode() != 200) {
                throw new Exception("Erro ao criar pagamento: " + EntityUtils.toString(response.getEntity()));
            }

            return EntityUtils.toString(response.getEntity());
        }
    }
}
