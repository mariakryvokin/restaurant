package app.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Dish {
    private int id;
    private int categoryId;
    private String name;
    private String nameUa;
    private BigDecimal price;
    Category category;

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }


    public Dish(){

    }

    public Dish(int id,String name, BigDecimal price, int categoryId) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;
        Dish dish = (Dish) o;
        return getCategoryId() == dish.getCategoryId() &&
                Objects.equals(getName(), dish.getName()) &&
                Objects.equals(getPrice(), dish.getPrice());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCategoryId(), getName(), getPrice());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return  "" + name +
                "/" + price+"\n";
    }
}
