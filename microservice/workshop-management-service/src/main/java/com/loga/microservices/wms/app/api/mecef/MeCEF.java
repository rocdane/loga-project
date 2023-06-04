package com.loga.microservices.wms.app.api.mecef;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MeCEF extends HttpRequestHelper implements IMeCEF {
    /*
    * PRODUCTION https://sygmef.impots.bj/emcf/api/invoice
    * TEST https://developper.impots.bj/sygmef-emcf/api/invoice
    * */
    final String INVOICE_URL = "https://developper.impots.bj/sygmef-emcf/api/invoice";

    /*
    * PRODUCTION https://sygmef.impots.bj/emcf/api/info
    * TEST https://developper.impots.bj/sygmef-emcf/api/info
    * */
    final String INFO_URL = "https://developper.impots.bj/sygmef-emcf/api/info";
    final String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjEyMDE3MDA1Nzg3MDd8VFMwMTAwMzg4NCIsInJvbGUiOiJUYXhwYXllciIsIm5iZiI6MTY2MjQxOTI4NSwiZXhwIjoxNjc4MDU3Njg1LCJpYXQiOjE2NjI0MTkyODUsImlzcyI6ImltcG90cy5iaiIsImF1ZCI6ImltcG90cy5iaiJ9.IH2Ia_IcIqvE_0dd7zbHiFvsgN3NdP6PdmACa7wpNos";

    @Override
    public JSONArray status() throws IOException {
        url = new URL(INVOICE_URL+"/");

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("content-type", "application/json");
        httpURLConnection.setRequestProperty("accept", "application/json");
        httpURLConnection.setRequestProperty("token", token);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        return request(httpURLConnection);
    }

    @Override
    public JSONArray invoicing() throws IOException {
        url = new URL(INVOICE_URL+"/");

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("content-type", "application/json");
        httpURLConnection.setRequestProperty("accept", "application/json");
        httpURLConnection.setRequestProperty("token", token);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        return request(httpURLConnection);
    }

    @Override
    public JSONArray confirmation(String uid, String action) throws IOException {
        url = new URL(INVOICE_URL+"/"+uid+"/"+action);
        /*
        * action = {confirm | cancel}
        * */
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("content-type", "application/json");
        httpURLConnection.setRequestProperty("accept", "application/json");
        httpURLConnection.setRequestProperty("token", token);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        return request(httpURLConnection);
    }

    @Override
    public JSONArray details(String uid) throws IOException {
        url = new URL(INVOICE_URL+"/"+uid);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("content-type", "application/json");
        httpURLConnection.setRequestProperty("accept", "application/json");
        httpURLConnection.setRequestProperty("token", token);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        return request(httpURLConnection);
    }

    @Override
    public JSONArray info() throws IOException {
        url = new URL(INFO_URL+"/status");

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("content-type", "application/json");
        httpURLConnection.setRequestProperty("accept", "application/json");
        httpURLConnection.setRequestProperty("token", token);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        return request(httpURLConnection);
    }

    @Override
    public JSONArray taxes() throws IOException {
        url = new URL(INFO_URL+"/taxGoups");

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("content-type", "application/json");
        httpURLConnection.setRequestProperty("accept", "application/json");
        httpURLConnection.setRequestProperty("token", token);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        return request(httpURLConnection);
    }

    @Override
    public JSONArray invoices() throws IOException {
        url = new URL(INFO_URL+"/invoiceTypes");

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("content-type", "application/json");
        httpURLConnection.setRequestProperty("accept", "application/json");
        httpURLConnection.setRequestProperty("token", token);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        return request(httpURLConnection);
    }

    @Override
    public JSONArray payments() throws IOException {
        url = new URL(INFO_URL+"/paymentTypes");

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("content-type", "application/json");
        httpURLConnection.setRequestProperty("accept", "application/json");
        httpURLConnection.setRequestProperty("token", token);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        return request(httpURLConnection);
    }
}
