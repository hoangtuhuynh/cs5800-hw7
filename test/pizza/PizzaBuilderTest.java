package pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;

class PizzaBuilderTest {

    @Test
    void sizeIsRequiredForEveryChain() {
        assertThrows(IllegalArgumentException.class, () -> new PizzaHut.Builder(null));
        assertThrows(IllegalArgumentException.class, () -> new LittleCaesars.Builder(null));
        assertThrows(IllegalArgumentException.class, () -> new Dominos.Builder(null));
    }

    @Test
    void eachBuilderCreatesItsOwnChain() {
        Pizza pizzaHut = new PizzaHut.Builder(Size.SMALL).build();
        Pizza littleCaesars = new LittleCaesars.Builder(Size.MEDIUM).build();
        Pizza dominos = new Dominos.Builder(Size.LARGE).build();

        assertInstanceOf(PizzaHut.class, pizzaHut);
        assertInstanceOf(LittleCaesars.class, littleCaesars);
        assertInstanceOf(Dominos.class, dominos);

        assertEquals("Pizza Hut", pizzaHut.getChain());
        assertEquals("Little Caesars", littleCaesars.getChain());
        assertEquals("Dominos", dominos.getChain());
    }

    @Test
    void sizeIsKept() {
        assertEquals(Size.SMALL, new PizzaHut.Builder(Size.SMALL).build().getSize());
        assertEquals(Size.MEDIUM, new PizzaHut.Builder(Size.MEDIUM).build().getSize());
        assertEquals(Size.LARGE, new PizzaHut.Builder(Size.LARGE).build().getSize());
    }

    @Test
    void toppingsAreKeptInTheOrderTheyWereAdded() {
        Pizza pizza = new Dominos.Builder(Size.LARGE)
                .spicyPork()
                .peppers()
                .onions()
                .build();

        assertEquals(List.of(Topping.SPICY_PORK, Topping.PEPPERS, Topping.ONIONS), pizza.getToppings());
    }

    @Test
    void pizzaWithNoToppingsIsAllowed() {
        assertTrue(new PizzaHut.Builder(Size.SMALL).build().getToppings().isEmpty());
    }

    @Test
    void addingTheSameToppingTwiceCountsOnce() {
        Pizza pizza = new PizzaHut.Builder(Size.SMALL)
                .pepperoni()
                .pepperoni()
                .build();

        assertEquals(List.of(Topping.PEPPERONI), pizza.getToppings());
    }

    @Test
    void everyBuilderMethodAddsTheMatchingTopping() {
        Pizza pizza = new LittleCaesars.Builder(Size.LARGE)
                .pepperoni()
                .sausage()
                .mushrooms()
                .bacon()
                .onions()
                .extraCheese()
                .peppers()
                .chicken()
                .olives()
                .spinach()
                .tomatoAndBasil()
                .beef()
                .ham()
                .pesto()
                .spicyPork()
                .hamAndPineapple()
                .build();

        assertEquals(List.of(Topping.values()), pizza.getToppings());
    }

    @Test
    void toppingsCannotBeChangedAfterBuild() {
        Pizza pizza = new PizzaHut.Builder(Size.SMALL).pepperoni().build();

        assertThrows(UnsupportedOperationException.class, () -> pizza.getToppings().add(Topping.HAM));
    }

    @Test
    void builderChangesAfterBuildDoNotAffectTheBuiltPizza() {
        Pizza.Builder builder = new PizzaHut.Builder(Size.SMALL).pepperoni();
        Pizza pizza = builder.build();
        builder.ham();

        assertEquals(List.of(Topping.PEPPERONI), pizza.getToppings());
    }

    @Test
    void eatPrintsChainSizeAndToppings() {
        Pizza pizza = new LittleCaesars.Builder(Size.MEDIUM)
                .olives()
                .tomatoAndBasil()
                .build();

        String output = captureOutput(pizza::eat);

        assertTrue(output.contains("Little Caesars"));
        assertTrue(output.contains("Medium"));
        assertTrue(output.contains("2 toppings"));
        assertTrue(output.contains("Olives"));
        assertTrue(output.contains("Tomato and Basil"));
    }

    @Test
    void eatUsesSingularForOneTopping() {
        Pizza pizza = new Dominos.Builder(Size.SMALL).hamAndPineapple().build();

        String output = captureOutput(pizza::eat);

        assertTrue(output.contains("1 topping:"));
        assertTrue(output.contains("Ham and Pineapple"));
    }

    private static String captureOutput(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }
}
