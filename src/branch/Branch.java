package src.branch;

import src.menu.Menu;

public class Branch {
  private int BranchID;
  private String branchName;
  private int staffQuota;
  private Staff[] staffList;

  private Menu branchMenu;

  public int getBranchID() {
    return BranchID;
  }

  public void setBranchID(int branchID) {
    BranchID = branchID;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
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
