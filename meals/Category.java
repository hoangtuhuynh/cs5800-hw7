package meals;

public enum Category {
    CARBS("Carbs"),
    PROTEIN("Protein"),
    FATS("Fats");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
