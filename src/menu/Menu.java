package src.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengyukun
 * Created at 21/3/24 17:40
 * Email : @author fengyukufyk@sina.com
 * Package : src
 * @version 1.00.00
 **/
public class Menu {
    List<Food> foodList;

    public Menu() {
        this.foodList = new ArrayList<>();
    }

    public Menu(List<Food> foodList) {
        this.foodList = foodList;
    }

    public List<Food> getFoodList(){
        return foodList;
    }


    public List<Food> getFoodListByCategory(FoodCategory category){
        //#TODO Filter by category
        return foodList;
    }
    //#TODO more methods



}
