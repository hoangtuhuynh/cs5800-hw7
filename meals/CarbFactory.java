package meals;

public class CarbFactory extends MacronutrientFactory {
    private static final CarbFactory INSTANCE = new CarbFactory();

    private CarbFactory() {
    }

    public static CarbFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public Category getCategory() {
        return Category.CARBS;
    }

    @Override
    public Carb create(DietPlan dietPlan) {
        return new Carb(pickRandomFood(dietPlan));
    }
}
