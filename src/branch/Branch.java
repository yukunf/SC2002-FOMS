package src.branch;

import src.menu.Food;
import java.util.List;
import java.util.ArrayList;
import src.App;

/**
 * The  Branch Class.
 */
public class Branch {
  private int BranchID;
  private boolean state;
  private String branchName;
  private int quota;
  private List<Staff> staffList;
  private List<Food> branchMenu;
  private List<Staff> managerList;


  /**
   * Instantiates a new Branch.
   *
   * @param branchName the branch name
   */
  public Branch(String branchName){
	  this.branchName = branchName;
  }

  /**
   * Gets branch id.
   *
   * @return the branch id
   */
  public int getBranchID() {
    return BranchID;
  }

  /**
   * Sets branch id.
   *
   * @param branchID the branch id
   */
  public void setBranchID(int branchID) {
    BranchID = branchID;
  }

  /**
   * Gets branch name.
   *
   * @return the branch name
   */
  public String getBranchName() {
    return branchName;
  }

  /**
   * Sets branch name.
   *
   * @param branchName the branch name
   */
  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  /**
   * Gets staff quota of the branch.<br>
   * A branch with 1-4 staffs (excluding managers) has 1 manager.<br>
   * •A branch with 5-8 staffs (excluding managers) has 2 managers.<br>
   * •A branch with 9-15 staffs (excluding managers) has 3 managers.<br>
   *
   * @return the staff quota
   */
  public int getStaffQuota() {
    return quota;
  }

  /**
   * Sets staff quota.
   *
   * @param staffQuota the staff quota
   */
  public void setStaffQuota(int staffQuota) {
    this.quota = staffQuota;
  }

  /**
   * Gets all staff list in this branch
   *
   * @return the staff list
   */
  public List<Staff> getStaffList() {
    return staffList;
  }

  /**
   * Sets staff list in this branch.
   *
   * @param staffList the staff list
   */
  public void setStaffList(List<Staff> staffList) {
    this.staffList = staffList;
  }

  /**
   * Sets branch menu.
   *
   * @param branchMenu the branch menu
   */
  public void setBranchMenu(List<Food> branchMenu) {
    this.branchMenu = branchMenu;
  }

  /**
   * Gets branch menu.
   *
   * @return the branch menu
   */
  public List<Food> getBranchMenu() {
    return branchMenu;
  }

  /**
   * Get all mangers of this branch
   *
   * @return the list
   */
  public List<Staff> getmanagerlist(){
    return this.managerList;

  }

  /**
   * Set the manager list.
   *
   * @param managerList the manager list
   */
  public void setmanagerlist(List<Staff> managerList){

    this.managerList=managerList;    
  }

  /**
   * Returns if this branch is open.
   *
   * @return the boolean True for open branch
   */
  public Boolean getState(){
    return this.state;
  }

  /**
   * Set if this branch is open
   *
   * @param open the open
   */
  public void setstate(Boolean open){
    this.state=open;
  }
}