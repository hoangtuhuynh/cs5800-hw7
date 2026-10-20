package pizza;

import java.util.List;

public class PizzaDriver {
    public static void main(String[] args) {
        System.out.println("===== Part 1: Pizza Hut (3, 6 and 9 toppings) =====\n");

        Pizza small = new PizzaHut.Builder(Size.SMALL)
                .pepperoni()
                .mushrooms()
                .extraCheese()
                .build();

        Pizza medium = new PizzaHut.Builder(Size.MEDIUM)
                .sausage()
                .bacon()
                .onions()
                .peppers()
                .olives()
                .beef()
                .build();

        Pizza large = new PizzaHut.Builder(Size.LARGE)
                .chicken()
                .spinach()
                .tomatoAndBasil()
                .ham()
                .pesto()
                .spicyPork()
                .hamAndPineapple()
                .mushrooms()
                .extraCheese()
                .build();

        for (Pizza pizza : List.of(small, medium, large)) {
            pizza.eat();
        }

        System.out.println("===== Part 2: Pizza Hut, Little Caesars and Dominos =====\n");

        Pizza pizzaHutLarge = new PizzaHut.Builder(Size.LARGE)
                .pepperoni()
                .sausage()
                .mushrooms()
                .build();

        Pizza pizzaHutSmall = new PizzaHut.Builder(Size.SMALL)
                .bacon()
                .onions()
                .build();

        Pizza littleCaesarsMedium = new LittleCaesars.Builder(Size.MEDIUM)
                .pepperoni()
                .sausage()
                .mushrooms()
                .bacon()
                .onions()
                .extraCheese()
                .peppers()
                .chicken()
                .build();

        Pizza littleCaesarsSmall = new LittleCaesars.Builder(Size.SMALL)
                .olives()
                .spinach()
                .tomatoAndBasil()
                .beef()
                .ham()
                .pesto()
                .build();

        Pizza dominosSmall = new Dominos.Builder(Size.SMALL)
                .hamAndPineapple()
                .build();

        Pizza dominosLarge = new Dominos.Builder(Size.LARGE)
                .spicyPork()
                .peppers()
                .onions()
                .build();

        for (Pizza pizza : List.of(pizzaHutLarge, pizzaHutSmall,
                littleCaesarsMedium, littleCaesarsSmall,
                dominosSmall, dominosLarge)) {
            pizza.eat();
        }
    }
}
