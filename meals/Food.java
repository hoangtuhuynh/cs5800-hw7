package meals;

public enum Food {
    CHEESE("Cheese", Category.CARBS),
    BREAD("Bread", Category.CARBS),
    LENTILS("Lentils", Category.CARBS),
    PISTACHIO("Pistachio", Category.CARBS),

    FISH("Fish", Category.PROTEIN),
    CHICKEN("Chicken", Category.PROTEIN),
    BEEF("Beef", Category.PROTEIN),
    TOFU("Tofu", Category.PROTEIN),

    AVOCADO("Avocado", Category.FATS),
    SOUR_CREAM("Sour cream", Category.FATS),
    TUNA("Tuna", Category.FATS),
    PEANUTS("Peanuts", Category.FATS);

    private final String displayName;
    private final Category category;

    Food(String displayName, Category category) {
        this.displayName = displayName;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
