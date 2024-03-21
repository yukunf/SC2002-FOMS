package src.menu;

/**
 * @author fengyukun
 * Created at 21/3/24 17:28
 * Email : @author fengyukufyk@sina.com
 * Package : src
 * @version 1.00.00
 **/
public class Food {

    private int ID;
    private String name;
    private FoodCategory category;



    private boolean availability;

    public Food(int ID, String name, FoodCategory category, boolean availability) {
        this.ID = ID;
        this.name = name;
        this.category = category;
        this.availability = availability;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
