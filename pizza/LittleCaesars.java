package pizza;

public class LittleCaesars extends Pizza {

    private LittleCaesars(Builder builder) {
        super(builder);
    }

    @Override
    public String getChain() {
        return "Little Caesars";
    }

    public static class Builder extends Pizza.Builder {
        public Builder(Size size) {
            super(size);
        }

        @Override
        public LittleCaesars build() {
            return new LittleCaesars(this);
        }
    }
}
