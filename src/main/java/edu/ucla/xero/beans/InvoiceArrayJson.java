package edu.ucla.xero.beans;

public class InvoiceArrayJson
{
  private String Id;
  private String Status;
  private String ProviderName;
  private String DateTimeUTC;
  private BasicInvoiceJson[] Invoices;

  public InvoiceArrayJson()
  {
    super();
  }

  public void setId(String Id)
  {
    this.Id = Id;
  }

  public String getId()
  {
    return Id;
  }

  public void setStatus(String Status)
  {
    this.Status = Status;
  }

  public String getStatus()
  {
    return Status;
  }

  public void setProviderName(String ProviderName)
  {
    this.ProviderName = ProviderName;
  }

  public String getProviderName()
  {
    return ProviderName;
  }

  public void setDateTimeUTC(String DateTimeUTC)
  {
    this.DateTimeUTC = DateTimeUTC;
  }

  public String getDateTimeUTC()
  {
    return DateTimeUTC;
  }

  public void setInvoices(BasicInvoiceJson[] Invoices)
  {
    this.Invoices = Invoices;
  }

  public BasicInvoiceJson[] getInvoices()
  {
    return Invoices;
  }
}

