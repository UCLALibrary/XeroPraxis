package edu.ucla.xero.main;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.client.WebTarget;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.lang.StringBuffer;

import java.util.Base64;
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
      props.load(OauthCaller.class.getClassLoader().getResourceAsStream(propFile));
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
    WebTarget target = client.target(props.getProperty("authorization_url"));

    Form form = new Form();
    form.param("grant_type", "refresh_token");
    form.param("refresh_token", "Zec0VuOFzLyojdcYF7KqlUNgfZyvzw67tDw2mpuGplA");

    String start = "Basic ";
    String clientID = props.getProperty("client_id");
    String clientSecret = props.getProperty("client_secret");
    StringBuffer buffer = new StringBuffer(clientID).append(":").append(clientSecret);
    String encoded = Base64.getEncoder().encodeToString(buffer.toString().getBytes());
    String authString = start.concat(encoded);
    String jsonResp = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization",authString).
	    post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
    System.out.println(jsonResp);
  }
}
