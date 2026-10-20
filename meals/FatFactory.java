package meals;

public class FatFactory extends MacronutrientFactory {
    private static final FatFactory INSTANCE = new FatFactory();

    private FatFactory() {
    }

    public static FatFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public Category getCategory() {
        return Category.FATS;
    }

    @Override
    public Fat create(DietPlan dietPlan) {
        return new Fat(pickRandomFood(dietPlan));
    }
}
