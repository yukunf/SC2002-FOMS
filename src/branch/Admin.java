package src.branch;
import java.util.List;
import java.util.Scanner;

public class Admin {

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

	for (Staff stf: branch.getStaffList()){
		if (stf.getStaffName().equals(a.getStaffName())){
			int ind =branch.getStaffList().indexOf(a);
			break;
		}
	}

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
		stlist=branch.getStaffList().remove(ind);
		branch.setStaffList(stlist.set(ind, a));
		return;			
		break;

		
		case 2:
		System.out.println("key in the new ID");
		String id=sc.nextLine();
		a.changeStaffID(a,id);
		case 3:
		System.out.println("key in the new gender");
		case 4:
		System.out.println("key in the new age");
		case 5:
		System.out.println("key in the new branch");

	}
}

public void RemoveStaff(Staff a) {
	
}
public void DisplayStaff() {
	
}
public void AssignManager() {
	
}
public void TransferStaffManager() {
	
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

