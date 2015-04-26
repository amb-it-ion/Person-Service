package com.ambition.prodyna.interfaces.api;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;

@WebServlet(name = "SwaggerJaxrsConfiguration", loadOnStartup = 1)
public class SwaggerJaxrsConfiguration extends HttpServlet {

    @Override
    public void init(final ServletConfig servletConfig) {
	try {
	    super.init(servletConfig);
	    final SwaggerConfig swaggerConfig = new SwaggerConfig();
	    ConfigFactory.setConfig(swaggerConfig);
	    swaggerConfig.setBasePath("http://localhost:8080/person-service/resources");
	    swaggerConfig.setApiVersion("1.0.0");
	    ScannerFactory.setScanner(new DefaultJaxrsScanner());
	    ClassReaders.setReader(new DefaultJaxrsApiReader());
	} catch (final ServletException e) {
	    System.out.println(e.getMessage());
	}
    }
}
