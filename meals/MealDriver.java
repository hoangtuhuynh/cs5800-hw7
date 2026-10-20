package meals;

import java.util.List;

public class MealDriver {
    public static void main(String[] args) {
        MacronutrientAbstractFactory abstractFactory = MacronutrientAbstractFactory.getInstance();

        System.out.println("===== Singleton check =====");
        System.out.println("One abstract factory: "
                + (abstractFactory == MacronutrientAbstractFactory.getInstance()));
        for (Category category : Category.values()) {
            System.out.println("One " + category + " factory: "
                    + (abstractFactory.getFactory(category) == abstractFactory.getFactory(category)));
        }
        System.out.println();

        System.out.println("===== Allowed foods per diet plan =====");
        for (DietPlan plan : DietPlan.values()) {
            System.out.println(plan + ":");
            for (Category category : Category.values()) {
                System.out.println("  " + category + ": "
                        + abstractFactory.getFactory(category).availableFoods(plan));
            }
        }
        System.out.println();

        List<Customer> customers = List.of(
                new Customer("Alice", DietPlan.NO_RESTRICTION),
                new Customer("Bob", DietPlan.PALEO),
                new Customer("Carol", DietPlan.VEGAN),
                new Customer("David", DietPlan.NUT_ALLERGY),
                new Customer("Emma", DietPlan.PALEO),
                new Customer("Frank", DietPlan.VEGAN));

        System.out.println("===== Meals =====\n");
        for (Customer customer : customers) {
            abstractFactory.createMeal(customer).print();
        }
    }
}
