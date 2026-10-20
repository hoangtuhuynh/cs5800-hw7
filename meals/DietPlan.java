package meals;

import java.util.EnumSet;
import java.util.Set;

public enum DietPlan {
    NO_RESTRICTION("No Restriction", EnumSet.noneOf(Food.class)),

    // No carbs except pistachio, no tofu, no dairy.
    PALEO("Paleo", EnumSet.of(
            Food.CHEESE, Food.BREAD, Food.LENTILS,
            Food.TOFU,
            Food.SOUR_CREAM)),

    // No meat, no dairy.
    VEGAN("Vegan", EnumSet.of(
            Food.FISH, Food.CHICKEN, Food.BEEF, Food.TUNA,
            Food.CHEESE, Food.SOUR_CREAM)),

    // No nuts.
    NUT_ALLERGY("Nut Allergy", EnumSet.of(
            Food.PISTACHIO, Food.PEANUTS));

    private final String displayName;
    private final Set<Food> banned;

    DietPlan(String displayName, Set<Food> banned) {
        this.displayName = displayName;
        this.banned = banned;
    }

    public boolean allows(Food food) {
        return !banned.contains(food);
    }

    @Override
    public String toString() {
        return displayName;
    }
}
