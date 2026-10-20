package meals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class DietPlanTest {

    @Test
    void noRestrictionAllowsEveryFood() {
        for (Food food : Food.values()) {
            assertTrue(DietPlan.NO_RESTRICTION.allows(food), food.toString());
        }
    }

    @Test
    void paleoAllowsOnlyPistachioAsACarb() {
        assertTrue(DietPlan.PALEO.allows(Food.PISTACHIO));
        assertFalse(DietPlan.PALEO.allows(Food.CHEESE));
        assertFalse(DietPlan.PALEO.allows(Food.BREAD));
        assertFalse(DietPlan.PALEO.allows(Food.LENTILS));
    }

    @Test
    void paleoBansTofuAndDairy() {
        assertFalse(DietPlan.PALEO.allows(Food.TOFU));
        assertFalse(DietPlan.PALEO.allows(Food.CHEESE));
        assertFalse(DietPlan.PALEO.allows(Food.SOUR_CREAM));
    }

    @Test
    void veganBansMeatAndDairy() {
        for (Food food : List.of(Food.FISH, Food.CHICKEN, Food.BEEF, Food.TUNA,
                Food.CHEESE, Food.SOUR_CREAM)) {
            assertFalse(DietPlan.VEGAN.allows(food), food.toString());
        }
    }

    @Test
    void nutAllergyBansNuts() {
        assertFalse(DietPlan.NUT_ALLERGY.allows(Food.PISTACHIO));
        assertFalse(DietPlan.NUT_ALLERGY.allows(Food.PEANUTS));
    }

    @Test
    void allowedFoodsPerCategoryMatchTheRules() {
        assertEquals(List.of(Food.PISTACHIO),
                CarbFactory.getInstance().availableFoods(DietPlan.PALEO));
        assertEquals(List.of(Food.FISH, Food.CHICKEN, Food.BEEF),
                ProteinFactory.getInstance().availableFoods(DietPlan.PALEO));
        assertEquals(List.of(Food.AVOCADO, Food.TUNA, Food.PEANUTS),
                FatFactory.getInstance().availableFoods(DietPlan.PALEO));

        assertEquals(List.of(Food.BREAD, Food.LENTILS, Food.PISTACHIO),
                CarbFactory.getInstance().availableFoods(DietPlan.VEGAN));
        assertEquals(List.of(Food.TOFU),
                ProteinFactory.getInstance().availableFoods(DietPlan.VEGAN));
        assertEquals(List.of(Food.AVOCADO, Food.PEANUTS),
                FatFactory.getInstance().availableFoods(DietPlan.VEGAN));

        assertEquals(List.of(Food.CHEESE, Food.BREAD, Food.LENTILS),
                CarbFactory.getInstance().availableFoods(DietPlan.NUT_ALLERGY));
        assertEquals(List.of(Food.AVOCADO, Food.SOUR_CREAM, Food.TUNA),
                FatFactory.getInstance().availableFoods(DietPlan.NUT_ALLERGY));
    }
}
