package edu.ucla.xero.beans;

public class BasicInvoiceJson
{
  private String InvoiceID;
  private String InvoiceNumber;
  private String Reference;
  private String AmountDue;
  private LineItemJson[] LineItems;

  public BasicInvoiceJson()
  {
    super();
  }

  public void setInvoiceID(String InvoiceID)
  {
    this.InvoiceID = InvoiceID;
  }

  public String getInvoiceID()
  {
    return InvoiceID;
  }

  public void setInvoiceNumber(String InvoiceNumber)
  {
    this.InvoiceNumber = InvoiceNumber;
  }

  public String getInvoiceNumber()
  {
    return InvoiceNumber;
  }

  public void setReference(String Reference)
  {
    this.Reference = Reference;
  }

  public String getReference()
  {
    return Reference;
  }

  public void setAmountDue(String AmountDue)
  {
    this.AmountDue = AmountDue;
  }

  public String getAmountDue()
  {
    return AmountDue;
  }

  public void setLineItems(LineItemJson[] LineItems)
  {
    this.LineItems = LineItems;
  }

  public LineItemJson[] getLineItems()
  {
    return LineItems;
  }
}

