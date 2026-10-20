package pizza;

public class PizzaHut extends Pizza {

    private PizzaHut(Builder builder) {
        super(builder);
    }

    @Override
    public String getChain() {
        return "Pizza Hut";
    }

    public static class Builder extends Pizza.Builder {
        public Builder(Size size) {
            super(size);
        }

        @Override
        public PizzaHut build() {
            return new PizzaHut(this);
        }
    }
}
