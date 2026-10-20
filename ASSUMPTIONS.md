# Assumptions

## Pizza (Builder)

- Size is passed to the builder's constructor, so a pizza cannot be built without a size. Passing `null` throws an error.
- Each topping has its own builder method (for example `.pepperoni()` or `.hamAndPineapple()`), so it is clear which topping is being added.
- Adding the same topping twice only counts it once.
- The Part 2 table is read as one column per chain:
  - Pizza Hut: Large with 3 toppings, Small with 2 toppings
  - Little Caesars: Medium with 8 toppings, Small with 6 toppings
  - Dominos: Small with 1 topping, Large with 3 toppings
- New chains are added by writing a new `Pizza` subclass with its own `Builder`. No existing code has to change.

## Macronutrient Meals (Abstract Factory, Factory, Singleton)

- The food table is read by column: Carbs = Cheese, Bread, Lentils, Pistachio; Protein = Fish, Chicken, Beef, Tofu; Fats = Avocado, Sour cream, Tuna, Peanuts.
- Vegan also excludes Beef. The handout lists only Fish, Chicken, and Tuna as meat, but beef is meat, so a vegan meal should not include it. That leaves Tofu as the only vegan protein.
- Paleo allows Peanuts because the handout only restricts carbs, tofu, and dairy for Paleo.
- `CarbFactory`, `ProteinFactory`, `FatFactory`, and `MacronutrientAbstractFactory` are eager singletons (private constructor + `getInstance()`).
