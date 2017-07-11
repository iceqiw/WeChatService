package org.qiwei;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.qiwei.config.AppConfig;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Created by admin on 2017/7/11.
 */
public class applicationServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setName("test1");
        connector.setHost("0.0.0.0");
        connector.setPort(8080);
        server.addConnector(connector);


        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setServer(server);
        webAppContext.setResourceBase("/Tpl");
        //访问地址：http://127.0.0.1:8080/test/
        webAppContext.setContextPath("/test");
        webAppContext.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

        webAppContext.addEventListener(new AppConfig());
        server.setHandler(webAppContext);
        server.start();
    }
}
