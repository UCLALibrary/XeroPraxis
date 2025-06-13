package edu.ucla.xero.beans;

public class BasicAccountJson
{
  private String AccountID;
  private String Code;
  private String Name;

  public BasicAccountJson()
  {
    super();
  }

  public void setAccountID(String AccountID)
  {
    this.AccountID = AccountID;
  }

  public String getAccountID()
  {
    return AccountID;
  }

  public void setCode(String Code)
  {
    this.Code = Code;
  }

  public String getCode()
  {
    return Code;
  }

  public void setName(String Name)
  {
    this.Name = Name;
  }

  public String getName()
  {
    return Name;
  }
}

