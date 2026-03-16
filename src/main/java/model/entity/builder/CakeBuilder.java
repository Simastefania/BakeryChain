package model.entity.builder;

import model.entity.Cake;

public class CakeBuilder {

    private Cake cake;

    public CakeBuilder() {
        cake = new Cake();
    }

    public CakeBuilder setId(int id) {
        cake.setId(id);
        return this;
    }

    public CakeBuilder setName(String name) {
        cake.setName(name);
        return this;
    }

    public CakeBuilder setPrice(double price) {
        cake.setPrice(price);
        return this;
    }

    public CakeBuilder setImagePath(String imagePath) {
        cake.setImagePath(imagePath);
        return this;
    }

    public Cake build() {
        return cake;
    }
}