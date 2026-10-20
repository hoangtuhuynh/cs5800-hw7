package meals;

public class ProteinFactory extends MacronutrientFactory {
    private static final ProteinFactory INSTANCE = new ProteinFactory();

    private ProteinFactory() {
    }

    public static ProteinFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public Category getCategory() {
        return Category.PROTEIN;
    }

    @Override
    public Protein create(DietPlan dietPlan) {
        return new Protein(pickRandomFood(dietPlan));
    }
}
