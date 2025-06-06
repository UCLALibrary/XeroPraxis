package edu.ucla.xero.main;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.client.WebTarget;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OauthCaller
{
  private static final Logger logger = LogManager.getLogger(OauthCaller.class);
  private static Properties props;

  public OauthCaller()
  {
    super();
  }

  public static void main(String[] args)
  {
    loadProperties(args[0]);
    getRefreshToken();
  }

  private static void loadProperties(String propFile)
  {
    props = new Properties();
    try
    {
      props.load(new FileInputStream(new File(propFile)));
    }
    catch (IOException ioe)
    {
      logger.fatal("problem with props file" + ioe.getMessage());
      System.exit(-1);
    }
  }

  private static void getRefreshToken()
  {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:9998").path("resource");

    Form form = new Form();
    form.param("x", "foo");
    form.param("y", "bar");
  }
}
