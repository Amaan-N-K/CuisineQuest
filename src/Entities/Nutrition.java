package Entities;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Nutrition {
    private int calorie;
    private int carbohydrates;
    private int protein;
    private int sugar;
    private int fiber;

    public Nutrition(@JsonProperty("calories") int calorie, @JsonProperty("carbohydrates") int carbohydrates, @JsonProperty("protein") int protein, @JsonProperty("sugar") int sugar,  @JsonProperty("fiber") int fiber){
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
