package edu.ucla.xero.beans;

public class TenantJson
{
  private String id;
  private String authEventId;
  private String tenantId;
  private String tenantType;
  private String tenantName;
  private String createdDateUtc;
  private String updatedDateUtc;

  public TenantJson()
  {
    super();
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getId()
  {
    return id;
  }

  public void setAuthEventId(String authEventId)
  {
    this.authEventId = authEventId;
  }

  public String getAuthEventId()
  {
    return authEventId;
  }

  public void setTenantId(String tenantId)
  {
    this.tenantId = tenantId;
  }

  public String getTenantId()
  {
    return tenantId;
  }

  public void setTenantType(String tenantType)
  {
    this.tenantType = tenantType;
  }

  public String getTenantType()
  {
    return tenantType;
  }

  public void setTenantName(String tenantName)
  {
    this.tenantName = tenantName;
  }

  public String getTenantName()
  {
    return tenantName;
  }

  public void setCreatedDateUtc(String createdDateUtc)
  {
    this.createdDateUtc = createdDateUtc;
  }

  public String getCreatedDateUtc()
  {
    return createdDateUtc;
  }

  public void setUpdatedDateUtc(String updatedDateUtc)
  {
    this.updatedDateUtc = updatedDateUtc;
  }

  public String getUpdatedDateUtc()
  {
    return updatedDateUtc;
  }
}

