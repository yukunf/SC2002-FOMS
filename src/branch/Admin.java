package src.branch;
import java.util.List;
import java.util.Scanner;

public class Admin {
	List<Branch> branchlist;
	Scanner sc = new Scanner(System.in);


//constructor
	public Admin(){

	}
	
	
	
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

		String branchname=branch.getBranchName();
		Staff newsStaff=new Staff(name,ID,gender,age,branchname);
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
			a.changeStaffbranch(a, branchnm);
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

public void RemoveStaff(Staff a,Branch branch) {
	int index=branch.getStaffList().indexOf(a);
	List<Staff> stflist=branch.getStaffList();
	stflist.remove(index);
	branch.setStaffList(stflist);
	return;
	
}
public void DisplayStaff(Branch branch) {
	for(Staff st:branch.getStaffList())
		System.out.println(st.getStaffName());
	}
	
}
public void AssignManager(Branch branch,Manager manager) {
	List<Manager> mnglist;
	mnglist=branch.getmanagerlist();
	mnglist.add(manager);
	branch.setmanagerlist(mnglist);
}

public void TransferStaffManager(Branch newBranch, Manager manager) {
	newBranch.setmanagerlist(null);
	
}
public void addpaymentmethod() {
	
}
public void removepaymentmethod() {
	
}
public void open() {
	
}
public void close() {
	
}










}

