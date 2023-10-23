package Entities;
public class Nutrition {
    private int calorie;
    private int carbohydrates;
    private int protein;
    private int sugar;
    private int fiber;

    Nutrition(int calorie, int carbohydrates, int protein, int sugar, int fiber){
        this.calorie = calorie;
        this.carbohydrates = carbohydrates;
        this.fiber = fiber;
        this.protein = protein;
        this.sugar = sugar;
    }

    public int getCalorie() {
        return calorie;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getFiber() {
        return fiber;
    }

    public int getProtein() {
        return protein;
    }

    public int getSugar() {
        return sugar;
    }
}
