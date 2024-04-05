package src.branch;

import src.menu.Food;
import java.util.List;

public class Branch {
  private int BranchID;
  private String branchName;
  private int staffQuota=0;
  private List<Staff> staffList;
  private List<Food> branchMenu;
  private List<Manager> managerList;
  private Boolean state;
  public Branch(String branchName){
	  this.branchName = branchName;
  }
  

  public Branch(String branchName, int staffQuota){
    this.branchName = branchName;
    this.staffQuota = staffQuota;
    this.branchMenu = branchMenu;
    this.state=true;
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

  public void setBranchMenu(List<Food> branchMenu) {
    this.branchMenu = branchMenu;
  }

  public List<Food> getBranchMenu() {
    return branchMenu;
  }

  public List<Manager> getmanagerlist(){
    return this.managerList;

  }

  public void setmanagerlist(List<Manager> managerList){

    this.managerList=managerList;    
  }

  public Boolean getState(){
    return this.state;
  }

  public void setstate(Boolean open){
    this.state=open;
  }
}