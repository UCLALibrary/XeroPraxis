package edu.ucla.xero.main;

import com.google.gson.Gson;

import edu.ucla.xero.beans.AccountArrayJson;
import edu.ucla.xero.beans.BasicAccountJson;
import edu.ucla.xero.beans.BasicInvoiceJson;
import edu.ucla.xero.beans.InvoiceArrayJson;
import edu.ucla.xero.beans.LineItemJson;
import edu.ucla.xero.beans.RefreshJson;
import edu.ucla.xero.beans.TenantJson;

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
  private static RefreshJson refreshBean;
  private static String tenantID;
  private static TenantJson[] tenantBeans;

  public OauthCaller()
  {
    super();
  }

  public static void main(String[] args)
  {
    loadProperties(args[0]);
    getRefreshToken();
    getTenantID();
    //getInvoices();
    getContacts();
  }

  private static void loadProperties(String propFile)
  {
    props = new Properties();
    try
    {
      props.load(new FileInputStream(new File(propFile)));
      //props.load(OauthCaller.class.getClassLoader().getResourceAsStream(propFile));
    }
    catch (IOException ioe)
    {
      logger.fatal("problem with props file" + ioe.getMessage());
      System.exit(-1);
    }
  }

  private static void getRefreshToken()
  {
    Gson gson = new Gson();
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(props.getProperty("token_url"));

    Form form = new Form();
    form.param("grant_type", "refresh_token");
    form.param("refresh_token", "aZLOLb7KxyIweaWwqm03k2TZuhqou6pQ8XECK1n1ewA");

    String start = "Basic ";
    String clientID = props.getProperty("client_id");
    String clientSecret = props.getProperty("client_secret");
    StringBuffer buffer = new StringBuffer(clientID).append(":").append(clientSecret);
    String encoded = Base64.getEncoder().encodeToString(buffer.toString().getBytes());
    String authString = start.concat(encoded);
    String jsonResp = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization",authString).
	    post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
    refreshBean = gson.fromJson(jsonResp, RefreshJson.class);
    System.out.println("access " + refreshBean.getAccess_token());
    System.out.println("expires " + refreshBean.getExpires_in());
    System.out.println("refresh " + refreshBean.getRefresh_token());
    System.out.println("scope " + refreshBean.getScope());
  }

  private static void getTenantID() {
    Gson gson = new Gson();
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(props.getProperty("tenant_url"));
    String authString = "Bearer ".concat(refreshBean.getAccess_token());
    String jsonResp = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization",authString).
            get(String.class);
    tenantBeans = gson.fromJson(jsonResp, TenantJson[].class);
    System.out.println("tenant id " + tenantBeans[0].getTenantId());
    System.out.println("tenant name " + tenantBeans[0].getTenantName());
  }
  
  private static void getInvoices() {
    Gson gson = new Gson();
    Client client = ClientBuilder.newClient();
    //String url = props.getProperty("invoice_url").concat("/SR-66169-01?summaryOnly=true");
    String url = props.getProperty("invoice_url").concat("?summaryOnly=truei&d87ContactIDs=f34a7-38cd-4c1a-a229-d2fdc8fe8f6c");
    //WebTarget target = client.target(props.getProperty("invoice_url"));
    WebTarget target = client.target(url);
    String authString = "Bearer ".concat(refreshBean.getAccess_token());
    String jsonResp = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization",authString).
            header("xero-tenant-id", tenantBeans[0].getTenantId()).get(String.class);
    InvoiceArrayJson invoiceArray = gson.fromJson(jsonResp, InvoiceArrayJson.class);
    System.out.println("\n\n" + jsonResp);
    BasicInvoiceJson theInvoice = invoiceArray.getInvoices()[0];
    System.out.println("\n\n" + theInvoice.getInvoiceNumber() + "\t" + theInvoice.getReference() + "\t" + theInvoice.getAmountDue());
    LineItemJson[] theLines = theInvoice.getLineItems();
    for (LineItemJson line : theLines) {
      System.out.println("\t" + line.getAccountID() + "\t" + line.getLineAmount() + "" + getItemCode(line.getAccountID()));
    }
  }
  
  private static String getItemCode(String accountID) {
    Gson gson = new Gson();
    Client client = ClientBuilder.newClient();
    String url = props.getProperty("accounts_id").concat(accountID);
    WebTarget target = client.target(url);
    String authString = "Bearer ".concat(refreshBean.getAccess_token());
    String jsonResp = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization",authString).
                header("xero-tenant-id", tenantBeans[0].getTenantId()).get(String.class);
    AccountArrayJson theAccount = gson.fromJson(jsonResp, AccountArrayJson.class);
    BasicAccountJson detail = theAccount.getAccounts()[0];
    return detail.getName();
  }

  private static void getContacts() {
    Client client = ClientBuilder.newClient();
    String url = props.getProperty("contact_url");
    WebTarget target = client.target(url);
    String authString = "Bearer ".concat(refreshBean.getAccess_token());
    String jsonResp = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization",authString).
                header("xero-tenant-id", tenantBeans[0].getTenantId()).get(String.class);
    System.out.println("\n\n" + jsonResp);
  }
}
