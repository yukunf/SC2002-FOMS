package src.branch;

import src.menu.Food;
import java.util.List;
import java.util.ArrayList;
import src.App;

public class Branch {
  private int BranchID;
  private boolean state;
  private String branchName;
  private int quota;
  private List<Staff> staffList;
  private List<Food> branchMenu;
  private List<Staff> managerList;

  
  public Branch(String branchName){
	  this.branchName = branchName;
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
    return quota;
  }

  public void setStaffQuota(int staffQuota) {
    this.quota = staffQuota;
  }

  public List<Staff> getStaffList() {
    return staffList;
  }

  public void setStaffList(List<Staff> staffList) {
    this.staffList = staffList;
  }

  public void setBranchMenu(List<Food> branchMenu) {
    this.branchMenu = branchMenu;
  }

  public List<Food> getBranchMenu() {
    return branchMenu;
  }

  public List<Staff> getmanagerlist(){
    return this.managerList;

  }

  public void setmanagerlist(List<Staff> managerList){

    this.managerList=managerList;    
  }
  
  public Boolean getState(){
    return this.state;
  }

  public void setstate(Boolean open){
    this.state=open;
  }
}