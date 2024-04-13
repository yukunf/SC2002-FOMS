package src.branch;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import src.App;
import src.order.Payment;


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
		//Update allEmployeesList
		App.allEmployeesList.add(newsStaff);
		//Update App.staffList
		App.staffList.add(newsStaff);
		branch.setStaffList(staffList);
	}

		
}

public void EditStaff(Branch branch) {
	int i = 1;
	List<Staff> staffList = filterStaff(branch);
	System.out.println("Enter the staff to edit:");
	for(Staff staff: staffList) {
		System.out.println(i + ". " + staff.getStaffName());
		i++;
	}
	System.out.println();
	int opt = sc.nextInt();
	Staff staff = staffList.get(opt-1);
	System.out.println("What do you want to edit");
	System.out.println("1. Name");
	System.out.println("2. ID");
	System.out.println("3. Gender");
	System.out.println("4. Age");
	int choice=sc.nextInt();
	switch (choice) {
		case 1:
			System.out.println("Enter new name:");
			String name=sc.next();
			staff.changeStaffName(name);
			break;
		case 2:
			System.out.println("Enter new LoginID:");
			String id=sc.next();
			staff.changeStaffID(id);
			break;
		case 3:
			System.out.println("Enter new gender");
			char gender =sc.next().charAt(0);
			staff.changeStaffgender(gender);
			break;
		case 4:
			System.out.println("Enter new age");
			int age=sc.nextInt();
			staff.changeStaffage(age);
			break;
	}
    // Update App.allEmployeesList
    for (Staff s : App.allEmployeesList) {
        if (s.getLoginID().equals(staff.getLoginID()) || s.getStaffName().equals(staff.getStaffName())) {
            s.changeStaffName(staff.getStaffName());
            s.changeStaffgender(staff.getGender());
            s.changeStaffage(staff.getAge());
            break;
        }
    }

    // Update App.staffList
    for (Staff s : App.staffList) {
    	if (s.getLoginID().equals(staff.getLoginID()) || s.getStaffName().equals(staff.getStaffName())) {
        	s.changeStaffName(staff.getStaffName());
            s.changeStaffgender(staff.getGender());
            s.changeStaffage(staff.getAge());
            break;
        }
    }
	branch.setStaffList(staffList);
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

public void TransferManager(Branch b) {
	int i = 0;
	System.out.println("Select Manager:");
	for(Staff s: b.getmanagerlist()) {   
		System.out.println(i+1 + ". " + s.getStaffName());
		i++;
	}
	int opt = sc.nextInt();
	Staff m = b.getmanagerlist().get(opt-1);
	System.out.println("Select branch to transfer the Manager to:");
	i= 0;
	for(Branch branch : App.branchList) {
		System.out.println(i+1 + ". " + branch.getBranchName());
		i++;
	}
	opt = sc.nextInt();
	//update App.StaffList
	 for (Staff manager : App.ManagerList) {
		 if(m.getStaffName().equals(manager.getStaffName())){
			 manager.changeStaffbranch(App.branchList.get(opt-1));
			 break;
		 }
	 }
	 //update App.allEmployeesList
	 for (Staff manager : App.allEmployeesList) {
		 if(m.getStaffName().equals(manager.getStaffName())){
			 manager.changeStaffbranch(App.branchList.get(opt-1));
			 break;
		 }
	 }
	 App.branchList.get(opt-1).getmanagerlist().add(m);
	 b.getmanagerlist().remove(m);
}

public void TransferStaff(Branch b) {
	int i = 0;
	System.out.println("Select Staff:");
	for(Staff s: b.getStaffList()) {
		System.out.println(i+1 + ". " + s.getStaffName());
		i++;
	}
	int opt = sc.nextInt();
	Staff s = b.getStaffList().get(opt-1);
	System.out.println("Select branch to transfer the staff to:");
	i= 0;
	for(Branch branch : App.branchList) {
		System.out.println(i+1 + ". " + branch.getBranchName());
		i++;
	}
	opt = sc.nextInt();
	//update App.StaffList
	 for (Staff staff : App.staffList) {
		 if(s.getStaffName().equals(staff.getStaffName())){
			 staff.changeStaffbranch(App.branchList.get(opt-1));
			 break;
		 }
	 }
	 //update App.allEmployeesList
	 for (Staff staff : App.allEmployeesList) {
		 if(s.getStaffName().equals(staff.getStaffName())){
			 staff.changeStaffbranch(App.branchList.get(opt-1));
			 break;
		 }
	 }
	 App.branchList.get(opt-1).getStaffList().add(s);
	 b.getStaffList().remove(s);
	
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

public void loadHomePage() {
	int answer;
	do {
	System.out.println("Select action:");
	System.out.println("1. Add/Edit/Remove Staff");
	System.out.println("2. Display staff");
	System.out.println("3. Promote a staff to a Branch manager");
	System.out.println("4. Transfer a staff/manager");
	System.out.println("5. Add/Remove payment method");
	System.out.println("6. Open/Close Branch");
	System.out.println("7. Quit");
	answer = sc.nextInt();
	switch (answer) {
		case 1: 
			System.out.println("Enter option");
			System.out.println("1. Add Staff");
			System.out.println("2. Edit Staff");
			System.out.println("3. Remove Staff");
			int option=sc.nextInt();
			//Add
			if(option==1){
				System.out.println("Enter the branch the staff is adding to:");
				for(int i=0;i<App.branchList.size();i++){
					System.out.println((i+1)+". "+App.branchList.get(i).getBranchName());
				}
				int index;
				index=sc.nextInt();
				Branch branch=App.branchList.get(index-1);
				this.AddStaff(branch);
			}
			//Edit
			if(option==2){
				System.out.println("Enter the branch the staff is in:");
				for(int i=0;i<App.branchList.size();i++){
					System.out.println((i+1) + ". " + App.branchList.get(i).getBranchName());
				}
				int index;
				index=sc.nextInt();
				Branch branch=App.branchList.get(index-1);
				this.EditStaff(branch);	
			}
			if (option==3){
				System.out.println("Enter the branch the staff is in:");
				for(int i=0;i<App.branchList.size();i++){
					System.out.println((i+1) + ". " + App.branchList.get(i).getBranchName());
				}
				int index;
				index=sc.nextInt();
				Branch branch=App.branchList.get(index-1);
				this.RemoveStaff(branch);
			}
			break;
			
		case 2: 
			System.out.println("Enter the branch to display the stafflist");
			for(int i=0;i<App.branchList.size();i++){
				System.out.println((i+1)+ ". " + App.branchList.get(i).getBranchName());
			}
			int index;
			index=sc.nextInt();
			Branch branch = App.branchList.get(index-1);
			this.DisplayStaff(branch);
			break;
		case 3: 
			System.out.println("Enter the branch");
			for(int i=0;i<App.branchList.size();i++){
				System.out.println((i+1) + 	". " + App.branchList.get(i).getBranchName());
			}
			index=sc.nextInt();
			int x = 0;
			branch = App.branchList.get(index-1);
			System.out.println("Enter the staff to promote to Manager");
				for(Staff staff : branch.getStaffList()){
					System.out.println(x+1 + ". " + staff.getStaffName());
					x++;
					}
				int opt = sc.nextInt();
				Manager m=new Manager(branch.getStaffList().get(opt-1).getStaffName(),branch.getStaffList().get(opt-1).getLoginID(),branch.getStaffList().get(opt-1).getGender(),branch.getStaffList().get(opt-1).getAge(),branch.getStaffList().get(opt-1).getBranch());
				
				//updating App.allEmployeesList
				List<Staff> employeesToRemove = new ArrayList<>();
				for (Staff s : App.allEmployeesList) {
				    if (m.getStaffName().equals(s.getStaffName())) {
				        employeesToRemove.add(s);
				    }
				}
				App.allEmployeesList.removeAll(employeesToRemove);

				// Update App.staffList 
				employeesToRemove.clear();
				for (Staff s : App.staffList) {
				    if (m.getStaffName().equals(s.getStaffName())) {
				        employeesToRemove.add(s);
				    }
				}
				App.staffList.removeAll(employeesToRemove);
				branch.getStaffList().remove(branch.getStaffList().get(opt-1));
				branch.getmanagerlist().add(m);
				App.allEmployeesList.add(m);
				App.ManagerList.add(m);

			break;
		case 4: 
			System.out.println("Transfer:");
			System.out.println("1. Staff");
			System.out.println("2. Manager");
			opt = sc.nextInt();
			int k = 0;
			if(opt == 1) {
				k = 0;
				System.out.println("Select branch you want to transfer staff from:");
				for(Branch b : App.branchList) {
					System.out.println(k+1 + ". " + b.getBranchName());
					k++;
				}
				int choice = sc.nextInt();
				Branch b = App.branchList.get(choice-1);
				this.TransferStaff(b);
			}
			else if(opt == 2) {
				k = 0;
				System.out.println("Select branch you want to transfer manager from:");
				for(Branch b : App.branchList) {
					System.out.println(k+1 + ". " + b.getBranchName());
					k++;
				}
				int choice = sc.nextInt();
				Branch b = App.branchList.get(choice-1);
				this.TransferManager(b);
			}
		break;

		case 5:
		System.out.println("Do you want to");
		System.out.println("1. Add Payment");
		System.out.println("2. Remove Payment");
		index=sc.nextInt();
		if(index==1){
			System.out.println("Enter the new payment method:");
			
			String paymentMethod = sc.next();
			Payment.addPaymentMethod(paymentMethod);
		}
		if(index==2){
			int i =0;
			System.out.println("Enter payment method to remove:");
			for(String p : Payment.getPaymentMethods()) {
				System.out.println(i+1 + ". " + p);
				i++;
			}
			int method =sc.nextInt();
			Payment.removePaymentMethod(Payment.getPaymentMethods().get(method-1));
		}
		break;

		case 6:			
		System.out.println("Do you want to");
		System.out.println("1. Open Branch");
		System.out.println("2. Close Branch");
		index=sc.nextInt();
		if(index==1){
			System.out.println("Enter the name of a branch: ");
			sc.nextLine();
			String name = sc.next();
			Branch newBranch = new Branch(name);
			App.branchList.add(newBranch);
		}
		if(index==2){
			System.out.println("Enter the branch to close");
			for(int i=0;i<App.branchList.size();i++){
				System.out.println((i+1)+ ". " + App.branchList.get(i).getBranchName());
			}
			index=sc.nextInt();
			branch=App.branchList.get(index-1);
			this.close(branch);
		}

	}
}while(answer < 7);
}


}


