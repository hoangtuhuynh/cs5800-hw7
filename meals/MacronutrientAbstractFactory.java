package meals;

public class MacronutrientAbstractFactory {
    private static final MacronutrientAbstractFactory INSTANCE = new MacronutrientAbstractFactory();

    private MacronutrientAbstractFactory() {
    }

    public static MacronutrientAbstractFactory getInstance() {
        return INSTANCE;
    }

    public MacronutrientFactory getFactory(Category category) {
        return switch (category) {
            case CARBS -> CarbFactory.getInstance();
            case PROTEIN -> ProteinFactory.getInstance();
            case FATS -> FatFactory.getInstance();
        };
    }

    public Meal createMeal(Customer customer) {
        DietPlan plan = customer.getDietPlan();
        return new Meal(customer,
                getFactory(Category.CARBS).create(plan),
                getFactory(Category.PROTEIN).create(plan),
                getFactory(Category.FATS).create(plan));
    }
}
