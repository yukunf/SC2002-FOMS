package src.branch

public class Branch {
  private String name;
  private String location;
  private int staffQuota;
  private Staff[] staffList = new Staff[staffQuota];

  public Branch(String name, String location, int staffQuota) {
    this.name=name;
    this.location=location;
    this.staffQuota=staffQuota;
    for (int i = 1; i < staffQuota; i++) {
        staffList[i] = new staff('NA', 'NA', 'NA', 'NA', 0, name);
    }
  }
  
}
