package meals;

public class Meal {
    private final Customer customer;
    private final Macronutrient carb;
    private final Macronutrient protein;
    private final Macronutrient fat;

    public Meal(Customer customer, Macronutrient carb, Macronutrient protein, Macronutrient fat) {
        this.customer = customer;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Macronutrient getCarb() {
        return carb;
    }

    public Macronutrient getProtein() {
        return protein;
    }

    public Macronutrient getFat() {
        return fat;
    }

    public void print() {
        System.out.println("Customer: " + customer.getName() + " (" + customer.getDietPlan() + ")");
        System.out.println("  Carbs:   " + carb);
        System.out.println("  Protein: " + protein);
        System.out.println("  Fats:    " + fat);
        System.out.println();
    }
}
