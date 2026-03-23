package com.bank;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 8080;

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector(); // triggers connector creation

        // Use current dir as base; JSPs and static files served from webroot
        String webappDir = new File("").getAbsolutePath();
        Context ctx = tomcat.addWebapp("/bank", webappDir);

        // Point Tomcat at compiled classes
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(
            resources,
            "/WEB-INF/classes",
            new File(webappDir, "WEB-INF/classes").getAbsolutePath(),
            "/"
        ));
        ctx.setResources(resources);

        tomcat.start();
        System.out.println("Server running at http://localhost:" + port + "/bank");
        tomcat.getServer().await();
    }
}
