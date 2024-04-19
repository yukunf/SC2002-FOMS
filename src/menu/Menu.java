package src.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu.
 *
 * @author fengyukun  Created at 21/3/24 17:40 Email : @author fengyukufyk@sina.com Package : src
 * @version 1.00.00
 */
public class Menu {
    List<Food> foodList;

    /**
     * Instantiates a new Menu.
     */
    public Menu() {
        this.foodList = new ArrayList<>();
    }

    /**
     * Instantiates a new Menu.
     *
     * @param foodList the food list
     */
    public Menu(List<Food> foodList) {
        this.foodList = foodList;
    }

    /**
     * Get food list list.
     *
     * @return the list
     */
    public List<Food> getFoodList(){
        return foodList;
    }

    /**
     * Get food list by branch list.
     *
     * @param branch the branch
     * @return the list
     */
    public List<Food> getFoodListByBranch(String branch){
    	List<Food> branchMenu = new ArrayList<Food>();
    	for(Food f : foodList) {
    		if(f.getBranch().equals(branch) && f.isAvailability()) {  //make sure is available
    			branchMenu.add(f);
    		}
    	}
    	return branchMenu;
    }

    /**
     * Set food list by branch.
     *
     * @param foodList the food list
     */
    public void setFoodListByBranch(List<Food> foodList){
    	this.foodList = foodList;
    }


    /**
     * Get food list by category list.
     *
     * @param branchMenu the branch menu
     * @param category   the category
     * @return the list
     */
    public List<Food> getFoodListByCategory(List<Food> branchMenu, FoodCategory category){
        List<Food> cate = new ArrayList<>();
        for(Food f: branchMenu){
            if(f.getCategory() == category) cate.add(f);
        }
        return cate;
    }
    //#TODO more methods



}
