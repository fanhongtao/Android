/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.stock.test.http.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class UnimplementedServlet extends HttpServlet {

    private static final long serialVersionUID = 6408042246393412913L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().print("The page [" + req.getPathInfo() + "] is unimplemented.");
    }
}
