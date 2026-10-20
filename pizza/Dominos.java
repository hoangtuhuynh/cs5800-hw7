package pizza;

public class Dominos extends Pizza {

    private Dominos(Builder builder) {
        super(builder);
    }

    @Override
    public String getChain() {
        return "Dominos";
    }

    public static class Builder extends Pizza.Builder {
        public Builder(Size size) {
            super(size);
        }

        @Override
        public Dominos build() {
            return new Dominos(this);
        }
    }
}
