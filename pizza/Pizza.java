package pizza;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class Pizza {
    private final Size size;
    private final List<Topping> toppings;

    protected Pizza(Builder builder) {
        this.size = builder.size;
        this.toppings = List.copyOf(builder.toppings);
    }

    public abstract String getChain();

    public Size getSize() {
        return size;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void eat() {
        System.out.println("Eating a " + size + " " + getChain() + " pizza with "
                + toppings.size() + (toppings.size() == 1 ? " topping:" : " toppings:"));
        for (Topping topping : toppings) {
            System.out.println("  - " + topping);
        }
        System.out.println();
    }

    // Size is a constructor argument so a pizza can never be built without one.
    public abstract static class Builder {
        private final Size size;
        private final Set<Topping> toppings = new LinkedHashSet<>();

        protected Builder(Size size) {
            if (size == null) {
                throw new IllegalArgumentException("Pizza size is required");
            }
            this.size = size;
        }

        public abstract Pizza build();

        public Builder pepperoni() {
            return add(Topping.PEPPERONI);
        }

        public Builder sausage() {
            return add(Topping.SAUSAGE);
        }

        public Builder mushrooms() {
            return add(Topping.MUSHROOMS);
        }

        public Builder bacon() {
            return add(Topping.BACON);
        }

        public Builder onions() {
            return add(Topping.ONIONS);
        }

        public Builder extraCheese() {
            return add(Topping.EXTRA_CHEESE);
        }

        public Builder peppers() {
            return add(Topping.PEPPERS);
        }

        public Builder chicken() {
            return add(Topping.CHICKEN);
        }

        public Builder olives() {
            return add(Topping.OLIVES);
        }

        public Builder spinach() {
            return add(Topping.SPINACH);
        }

        public Builder tomatoAndBasil() {
            return add(Topping.TOMATO_AND_BASIL);
        }

        public Builder beef() {
            return add(Topping.BEEF);
        }

        public Builder ham() {
            return add(Topping.HAM);
        }

        public Builder pesto() {
            return add(Topping.PESTO);
        }

        public Builder spicyPork() {
            return add(Topping.SPICY_PORK);
        }

        public Builder hamAndPineapple() {
            return add(Topping.HAM_AND_PINEAPPLE);
        }

        private Builder add(Topping topping) {
            toppings.add(topping);
            return this;
        }
    }
}
