package meals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class MacronutrientFactoryTest {

    private static final int RUNS = 500;

    @Test
    void eachFactoryCreatesItsOwnProductType() {
        assertInstanceOf(Carb.class, CarbFactory.getInstance().create(DietPlan.NO_RESTRICTION));
        assertInstanceOf(Protein.class, ProteinFactory.getInstance().create(DietPlan.NO_RESTRICTION));
        assertInstanceOf(Fat.class, FatFactory.getInstance().create(DietPlan.NO_RESTRICTION));
    }

    @Test
    void createdItemsBelongToTheFactoryCategory() {
        for (Category category : Category.values()) {
            MacronutrientFactory factory = MacronutrientAbstractFactory.getInstance().getFactory(category);
            for (int i = 0; i < RUNS; i++) {
                Macronutrient item = factory.create(DietPlan.NO_RESTRICTION);
                assertEquals(category, item.getCategory());
                assertEquals(category, item.getFood().getCategory());
            }
        }
    }

    @Test
    void createdItemsNeverBreakTheDietPlan() {
        for (DietPlan plan : DietPlan.values()) {
            for (Category category : Category.values()) {
                MacronutrientFactory factory = MacronutrientAbstractFactory.getInstance().getFactory(category);
                for (int i = 0; i < RUNS; i++) {
                    Food food = factory.create(plan).getFood();
                    assertTrue(plan.allows(food), plan + " got " + food);
                }
            }
        }
    }

    @Test
    void selectionIsRandomAcrossAllAllowedFoods() {
        Set<Food> seen = EnumSet.noneOf(Food.class);
        for (int i = 0; i < RUNS; i++) {
            seen.add(CarbFactory.getInstance().create(DietPlan.NO_RESTRICTION).getFood());
        }

        assertEquals(EnumSet.of(Food.CHEESE, Food.BREAD, Food.LENTILS, Food.PISTACHIO), seen);
    }

    @Test
    void macronutrientRejectsFoodFromAnotherCategory() {
        assertThrows(IllegalArgumentException.class, () -> new Carb(Food.TOFU));
        assertThrows(IllegalArgumentException.class, () -> new Protein(Food.AVOCADO));
        assertThrows(IllegalArgumentException.class, () -> new Fat(Food.BREAD));
    }
}
