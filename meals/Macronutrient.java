package meals;

public abstract class Macronutrient {
    private final Food food;
    private final Category category;

    protected Macronutrient(Food food, Category category) {
        if (food.getCategory() != category) {
            throw new IllegalArgumentException(food + " is not a " + category + " item");
        }
        this.food = food;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }
}
