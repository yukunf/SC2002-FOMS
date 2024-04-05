package src.branch;
import java.util.List;
import java.util.Scanner;
import src.App;

public class Admin extends Staff {

	private char role='A';
	
	private List<Branch> branchlist;
	private List<String> paymentMethodsList;	 
	Scanner sc = new Scanner(System.in);


// default constructor
	public Admin(String name, String loginID, char gender, int age, Branch branch) {
		super(name, loginID, gender, age, branch);
		}
//Constructor

	
	
public void AddStaff(Branch branch) {

	int quota=branch.getStaffQuota();
	List<Staff> staffList=branch.getStaffList();
	if (staffList.size()<quota){
		System.out.println("Enter the name of the new staff");
		String name=sc.nextLine();
		System.out.println("Enter the loginID of the new staff");
		String ID=sc.nextLine();
		System.out.println("Enter the gender of the new staff");
		char gender=sc.nextLine().charAt(0);
		System.out.println("Enter the age of the new staff");
		int age=sc.nextInt();

		Staff newsStaff=new Staff(name,ID,gender,age,branch);
		staffList.add(newsStaff);
		branch.setStaffList(staffList);
	}

		
}

public void EditStaff(Staff a,Branch branch) {

	List<Staff> stlist;
	int ind =branch.getStaffList().indexOf(a);


	System.out.println("What do you want to edit");
	System.out.println("1.name");
	System.out.println("2.ID");
	System.out.println("3.gender");
	System.out.println("4.age");
	System.out.println("5.branch");
	int choice=sc.nextInt();

	switch (choice) {
		case 1:
			System.out.println("key in the new name");
			String name=sc.nextLine();
			a.changeStaffName(a,name);
			break;
		case 2:
			System.out.println("key in the new ID");
			String id=sc.nextLine();
			a.changeStaffID(a,id);
			break;
		case 3:
			System.out.println("key in the new gender");
			char gender =sc.nextLine().charAt(0);
			a.changeStaffgender(a, gender);
			break;
		case 4:
			System.out.println("key in the new age");
			int age=sc.nextInt();
			a.changeStaffage(a, age);
			break;
		case 5:
			System.out.println("key in the new branch");
			String branchnm=sc.nextLine();
			a.changeStaffbranch(a, branch);
			stlist=branch.getStaffList();
			stlist.remove(ind);
			branch.setStaffList(stlist);

			int branchindex = -1;

			for (int i=0;i<this.branchlist.size();++i){
				if (this.branchlist.get(0).getBranchName().equals(branchnm)){
					branchindex=i;
					break;
				}
	
			}
			if (branchindex==-1) {
				System.out.println("Branch does not exist.");
				return;
			}
			Branch bh=this.branchlist.get(branchindex);
			List<Staff> stflist=bh.getStaffList();
			stflist.add(a);
			bh.setStaffList(stflist);
			return;		
	}
	stlist=branch.getStaffList();
	stlist.remove(ind);
	stlist.set(ind, a);
	branch.setStaffList(stlist);
}

public void RemoveStaff(Staff a,Branch branch) {
	int index=branch.getStaffList().indexOf(a);
	List<Staff> stflist=branch.getStaffList();
	stflist.remove(index);
	branch.setStaffList(stflist);
	return;
	
}
public void DisplayStaff(Branch branch) {
	System.out.println("Manahers:");
	for(Manager m:branch.getmanagerlist()){
		System.out.println(m.getStaffName());
	}
	System.out.println("staffs:");
	for(Staff st:branch.getStaffList())
		System.out.println(st.getStaffName());
	}
	

private Manager addManager(Branch branch){
	System.out.println("Enter the name of the new staff");
	String name=sc.nextLine();
	System.out.println("Enter the loginID of the new staff");
	String ID=sc.nextLine();
	System.out.println("Enter the gender of the new staff");
	char gender=sc.nextLine().charAt(0);
	System.out.println("Enter the age of the new staff");
	int age=sc.nextInt();
	Manager m=new Manager(name,ID,gender,age,branch);
	return m;

}
public void AssignManager(Branch branch) {
	List<Manager> mnglist;
	Manager m=addManager(branch);
	mnglist=branch.getmanagerlist();
	mnglist.add(m);
	branch.setmanagerlist(mnglist);
}
public void AssignManager(Branch branch,Manager m) {
	List<Manager> mnglist;
	mnglist=branch.getmanagerlist();
	mnglist.add(m);
	branch.setmanagerlist(mnglist);
}

public void TransferManager(Branch newBranch, Branch originalBranch, Manager manager) {
	List<Manager> managerlist;
	managerlist=originalBranch.getmanagerlist();
	managerlist.remove(manager);
	originalBranch.setmanagerlist(managerlist);
	managerlist=newBranch.getmanagerlist();
	List<Manager> mnglist;
	mnglist=originalBranch.getmanagerlist();
	mnglist.add(manager);
	newBranch.setmanagerlist(mnglist);
}

public void TransferStaff(Branch newBranch, Branch originalBranch, Staff staff) {
	List<Staff> stafflist;
	stafflist=newBranch.getStaffList();
	stafflist.add(staff);
	newBranch.setStaffList(stafflist);

	stafflist=originalBranch.getStaffList();
	stafflist.remove(staff);
	originalBranch.setStaffList(stafflist);
}

public List<String> getPaymentMethod(){
	return paymentMethodsList;
}

public List<String> addpaymentmethod(String newpaymentmethod) {
	paymentMethodsList.add(newpaymentmethod);
	return paymentMethodsList;
}
public List<String> removepaymentmethod(String removingpaymentmethod) {
	paymentMethodsList.remove(removingpaymentmethod);
	return paymentMethodsList;
	
}
public void open(Branch branch) {
	App.branchList.add(branch);	
}
public void close(Branch branch) {
	App.branchList.remove(branch);
}


}


