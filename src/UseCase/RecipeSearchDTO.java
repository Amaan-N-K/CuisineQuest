package UseCase;
import Entities.Recipe;
import java.util.List;

public class RecipeSearchDTO {
    private final String recipeID;
    private final String name;
    private final List<String> ingredients;
    private final List<String> mealType;
    private final List<String> diet;
    private final List<String> health;
    private final List<String> cuisineType;
    private final int calorie;
    private final int carbohydrates;
    private final int protein;
    private final int sugar;
    private final int fiber;
    private final String description;

    public RecipeSearchDTO(Recipe recipe) {
        this.recipeID = recipe.getRecipeId();
        this.name = recipe.getName();
        this.ingredients = recipe.getIngredients();
        this.mealType = recipe.getMealType();
        this.diet = recipe.getDiet();
        this.health = recipe.getHealth();
        this.cuisineType = recipe.getIngredients();
        this.calorie = recipe.getNutrition().getCalorie();
        this.carbohydrates = recipe.getNutrition().getCarbohydrates();
        this.protein = recipe.getNutrition().getProtein();
        this.sugar = recipe.getNutrition().getSugar();
        this.fiber = recipe.getNutrition().getFiber();
        this.description = recipe.getDescription();
    }

    public String getRecipeID() {
        return recipeID;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getMealType() {
        return mealType;
    }

    public List<String> getDiet() {
        return diet;
    }

    public List<String> getHealth() {
        return health;
    }

    public List<String> getCuisineType() {
        return cuisineType;
    }

    public int getCalorie() {
        return calorie;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getProtein() {
        return protein;
    }

    public int getSugar() {
        return sugar;
    }

    public int getFiber() {
        return fiber;
    }

    public String getDescription() {
        return description;
    }
}

