package src.branch;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import src.App;


public class Admin extends Staff {

	private char role = 'A';
	
	private List<Branch> branchlist;
	private List<String> paymentMethodsList;	 

	Scanner sc = new Scanner(System.in);


// default constructor
	public Admin(String name, String loginID, char gender, int age, Branch branch) {
		super(name, loginID, gender, age, branch);
		}
//Constructor

public static List<Staff> filterStaff(Branch branch){
	List<Staff> staffList = new ArrayList<>();
	for(Staff staff: App.staffList) {
		if(staff.getBranch().getBranchName().equals(branch.getBranchName())) {
			staffList.add(staff);
		}		
	}
	return staffList;
}
public static List<Manager> filterManager(Branch branch){
	List<Manager> managerList = new ArrayList<>();
	for(Manager manager: App.ManagerList) {
		if(manager.getBranch().getBranchName().equals(branch.getBranchName())) {
			managerList.add(manager);
		}		
	}
	return managerList;
}
	
public void AddStaff(Branch branch) {

	int quota=branch.getStaffQuota();
	List<Staff> staffList = filterStaff(branch);
	if (staffList.size()<quota){
		System.out.println("Enter the name of the new staff:");
		String name=sc.nextLine();
		System.out.println("Enter the loginID of the new staff");
		String ID=sc.nextLine();
		System.out.println("Enter the gender of the new staff");
		char gender=sc.nextLine().charAt(0);
		System.out.println("Enter the age of the new staff");
		int age=sc.nextInt();

		Staff newsStaff=new Staff(name,ID,gender,age,branch);
		staffList.add(newsStaff);
		App.allEmployeesList.add(newsStaff);
		App.staffList.add(newsStaff);
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
			a.changeStaffgender(gender);
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

			for (int i=0;i<branchlist.size();++i){
				if (branchlist.get(0).getBranchName().equals(branchnm)){
					branchindex=i;
					break;
				}
	
			}
			if (branchindex==-1) {
				System.out.println("Branch does not exist.");
				return;
			}
			Branch bh=branchlist.get(branchindex);
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

public void RemoveStaff(Branch branch) {
	int i = 1;
	List<Staff> staffList = filterStaff(branch);
	System.out.println("Enter the staff to remove:");
	for(Staff staff: staffList) {
		System.out.println(i + ". " + staff.getStaffName());
		i++;
	}
	int opt = sc.nextInt();
	for(Staff s : App.allEmployeesList) {
		if(staffList.get(opt-1).getStaffName().equals(s.getStaffName())) {
			App.allEmployeesList.remove(s);
			break;
		}
	}
	for(Staff s : App.staffList) {
		if(staffList.get(opt-1).getStaffName().equals(s.getStaffName())) {
			App.staffList.remove(s);
			break;
		}
	}
	staffList.remove(opt-1);
	branch.setStaffList(staffList);
	System.out.println("Removed successfully!");
	System.out.println();
	return;
	
}

public void DisplayStaff(Branch branch) {
	List<Staff> staffList = filterStaff(branch);
	List<Manager> managerList = filterManager(branch);
	System.out.println("Managers:");
	for(Manager m : managerList){
		System.out.println(m.getStaffName());
	}
	System.out.println();
	System.out.println("Staffs:");
	for(Staff st : staffList)
		System.out.println(st.getStaffName());
	System.out.println();
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

	//AssignManager(newBranch, manager);  Why assignManager method is called?
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


