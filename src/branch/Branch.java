package src.branch;

import src.menu.Menu;
import java.util.List;
import java.util.ArrayList;

public class Branch {
  private int BranchID;
  private String branchName;
  private int staffQuota;
  private List<Staff> staffList;
  private Menu branchMenu;
  
  public Branch(String branchName, int staffQuota){
	  this.branchName = branchName;
	  this.staffQuota = staffQuota;
  }

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

  public List<Staff> getStaffList() {
    return staffList;
  }

  public void setStaffList(List<Staff> staffList) {
    this.staffList = staffList;
  }
}
