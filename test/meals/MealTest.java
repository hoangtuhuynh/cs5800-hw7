package meals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MealTest {

    private final MacronutrientAbstractFactory abstractFactory = MacronutrientAbstractFactory.getInstance();

    @Test
    void mealBelongsToTheCustomer() {
        Customer customer = new Customer("Alice", DietPlan.NO_RESTRICTION);

        Meal meal = abstractFactory.createMeal(customer);

        assertSame(customer, meal.getCustomer());
    }

    @Test
    void mealHasOneCarbOneProteinAndOneFat() {
        Meal meal = abstractFactory.createMeal(new Customer("Bob", DietPlan.NO_RESTRICTION));

        assertEquals(Category.CARBS, meal.getCarb().getCategory());
        assertEquals(Category.PROTEIN, meal.getProtein().getCategory());
        assertEquals(Category.FATS, meal.getFat().getCategory());
    }

    @Test
    void mealsFollowEveryDietPlan() {
        for (DietPlan plan : DietPlan.values()) {
            Customer customer = new Customer("Customer", plan);
            for (int i = 0; i < 200; i++) {
                Meal meal = abstractFactory.createMeal(customer);
                assertTrue(plan.allows(meal.getCarb().getFood()), plan + " carb " + meal.getCarb());
                assertTrue(plan.allows(meal.getProtein().getFood()), plan + " protein " + meal.getProtein());
                assertTrue(plan.allows(meal.getFat().getFood()), plan + " fat " + meal.getFat());
            }
        }
    }

    @Test
    void veganMealAlwaysUsesTofuForProtein() {
        Meal meal = abstractFactory.createMeal(new Customer("Carol", DietPlan.VEGAN));

        assertEquals(Food.TOFU, meal.getProtein().getFood());
    }

    @Test
    void paleoMealAlwaysUsesPistachioForCarbs() {
        Meal meal = abstractFactory.createMeal(new Customer("Emma", DietPlan.PALEO));

        assertEquals(Food.PISTACHIO, meal.getCarb().getFood());
    }
}
