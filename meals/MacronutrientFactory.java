package meals;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class MacronutrientFactory {
    private final Random random = new Random();

    public abstract Category getCategory();

    public abstract Macronutrient create(DietPlan dietPlan);

    protected Food pickRandomFood(DietPlan dietPlan) {
        List<Food> options = availableFoods(dietPlan);
        if (options.isEmpty()) {
            throw new IllegalStateException("No " + getCategory() + " options for diet plan " + dietPlan);
        }
        return options.get(random.nextInt(options.size()));
    }

    public List<Food> availableFoods(DietPlan dietPlan) {
        return Arrays.stream(Food.values())
                .filter(food -> food.getCategory() == getCategory())
                .filter(dietPlan::allows)
                .toList();
    }
}
