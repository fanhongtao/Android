/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.stock.test.http;

import org.fanhongtao.http.WebClient;
import org.fanhongtao.stock.test.http.server.JettyServer;

import android.annotation.SuppressLint;
import android.test.AndroidTestCase;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
@SuppressLint("SdCardPath")
public class WebClientTest extends AndroidTestCase {
    private static JettyServer server;

    private static final String BASE_URL = "http://localhost:8080";
    static {
        server = new JettyServer(8080, "/sdcard/fht/jetty");
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testGetContent() throws Exception {
        WebClient client = new WebClient(BASE_URL);
        String result = client.getContent("/hello");
        assertEquals("Hello, world", result);
    }
}
