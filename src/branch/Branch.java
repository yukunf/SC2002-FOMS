package src.branch;

import src.menu.Menu;

public class Branch {
  private int BranchID;
  private String location;
  private int staffQuota;
  private Staff[] staffList;

  private Menu branchMenu;

  public int getBranchID() {
    return BranchID;
  }

  public void setBranchID(int branchID) {
    BranchID = branchID;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getStaffQuota() {
    return staffQuota;
  }

  public void setStaffQuota(int staffQuota) {
    this.staffQuota = staffQuota;
  }

  public Staff[] getStaffList() {
    return staffList;
  }

  public void setStaffList(Staff[] staffList) {
    this.staffList = staffList;
  }
}
