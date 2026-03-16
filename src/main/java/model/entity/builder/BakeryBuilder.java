package model.entity.builder;

import model.entity.Bakery;

    public class BakeryBuilder {

        private Bakery bakery;

        public BakeryBuilder() {
            bakery = new Bakery();
        }

        public BakeryBuilder setId(int id) {
            bakery.setId(id);
            return this;
        }

        public BakeryBuilder setName(String name) {
            bakery.setName(name);
            return this;
        }

        public BakeryBuilder setAddress(String address) {
            bakery.setAddress(address);
            return this;
        }

        public Bakery build() {
            return bakery;
        }
    }


