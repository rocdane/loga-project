package com.loga.microservices.wms.app.api.mecef;

import org.json.simple.JSONArray;

import java.io.IOException;

public interface IMeCEF {

    JSONArray status() throws IOException;

    JSONArray invoicing() throws IOException;

    JSONArray confirmation(String uid, String action) throws IOException;

    JSONArray details(String uid) throws IOException;

    JSONArray info() throws IOException;

    JSONArray taxes() throws IOException;

    JSONArray invoices() throws IOException;

    JSONArray payments() throws  IOException;
}
