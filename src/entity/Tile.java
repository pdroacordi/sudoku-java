package entity;

public class Tile {
    private boolean fixed;
    private Integer value;

    public boolean isFixed() {
        return fixed;
    }

    public Integer getValue() {
        return value;
    }

    public Tile(Builder builder) {
        this.fixed = builder.fixed;
        this.value = builder.value;
    }



    public static class Builder {
        private boolean fixed;
        private Integer value;

        public Builder fixed(boolean fixed) {
            this.fixed = fixed;
            return this;
        }
        public Builder value(Integer value) {
            this.value = value;
            return this;
        }

        public Tile build() {
            return new Tile(this);
        }
    }
}
