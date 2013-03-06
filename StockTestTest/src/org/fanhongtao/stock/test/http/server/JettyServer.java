/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.stock.test.http.server;

import java.io.File;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class JettyServer {
    private Server server;

    /** HTTP服务器的端口号 */
    private int mPort;

    /** 服务器的资源对应的Home目录 */
    private String mResourceBase;

    public JettyServer(int port, String resourceBase) {
        this.mPort = port;
        this.mResourceBase = resourceBase;
    }

    public void start() throws Exception {
        // server = new Server(mPort);
        File dir = new File(mResourceBase);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        server = new Server();
        Connector connector = new SocketConnector();
        connector.setPort(mPort);
        server.addConnector(connector);

        HandlerList handlerList = new HandlerList();
       
        ResourceHandler resHandler = new ResourceHandler();
        resHandler.setWelcomeFiles(new String[] { "index.html" });
        resHandler.setResourceBase(mResourceBase);
        handlerList.addHandler(resHandler);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(HelloServlet.class, "/hello");
        servletHandler.addServletWithMapping(UnimplementedServlet.class, "/*");
        handlerList.addHandler(servletHandler);

        server.setHandler(handlerList);
        server.setStopAtShutdown(true);
        server.start();
        // Log.info("Server base: " + resHandler.getBaseResource());
    }

    public void stop() throws Exception {
        server.stop();
    }

    public static void main(String[] args) throws Exception {
        new JettyServer(8080, ".").start();
    }
}
