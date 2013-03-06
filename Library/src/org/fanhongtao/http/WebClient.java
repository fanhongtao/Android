/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class WebClient {
    private String mBaseUrl;

    public WebClient() {
        this(null);
    }

    public WebClient(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    public String getContent(String urlStr) throws Exception {
        URL url;
        if (urlStr.startsWith("http:") || urlStr.startsWith("https:")) {
            url = new URL(urlStr);
        } else {
            url = new URL(mBaseUrl + urlStr);
        }
        return getContent(url);
    }

    public String getContent(URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        int length;
        byte[] buffer = new byte[1024];
        while ((length = is.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }

        return out.toString();
    }
}
