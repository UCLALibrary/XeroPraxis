package edu.ucla.xero.beans;

public class LineItemJson
{
  private String LineAmount;
  private String AccountID;

  public LineItemJson()
  {
    super();
  }

  public void setLineAmount(String LineAmount)
  {
    this.LineAmount = LineAmount;
  }

  public String getLineAmount()
  {
    return LineAmount;
  }

  public void setAccountID(String AccountID)
  {
    this.AccountID = AccountID;
  }

  public String getAccountID()
  {
    return AccountID;
  }
}
