package app;


import interface_adapter.meal_plan_creation.MealPlanController;
import interface_adapter.meal_plan_creation.MealPlanPresenter;
import interface_adapter.meal_plan_creation.MealPlanViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.meal_plan_creation.*;
import view.MealPlanSearchView;
import data_access.MealPlanDataAccessObject;
import data_access.UserDataAccessObject;


public class MealPlanUseCaseFactory {
    private MealPlanUseCaseFactory() {}

    private static MealPlanController createMealPlanController(MealPlanViewModel mealPlanViewModel, DashboardViewModel dashboardViewModel,
                                                               MealPlanAPIDataAccessInterface mealPlanAPIDataAccessInterface, ViewManagerModel viewManagerModel,
                                                               MealPlanDataAccessObject mealPlanDataAccessObject, UserDataAccessObject userDataAccessObject) {

        MealPlanOutputBoundary mealPlanPresenter = new MealPlanPresenter(viewManagerModel, mealPlanViewModel, dashboardViewModel);

        MealPlanInputBoundary mealPlanInteractor = new MealPlanInteractor(mealPlanAPIDataAccessInterface, mealPlanPresenter, mealPlanDataAccessObject, userDataAccessObject);

        return new MealPlanController(mealPlanInteractor);
    }

    public static MealPlanSearchView createMealPlanView(
            MealPlanViewModel mealPlanViewModel,
            DashboardViewModel dashboardViewModel,
            MealPlanAPIDataAccessInterface mealPlanAPIDataAccessInterface, ViewManagerModel viewManagerModel,
            MealPlanDataAccessObject mealPlanDataAccessObject, UserDataAccessObject userDataAccessObject) {

        MealPlanController mealPlanController = createMealPlanController(mealPlanViewModel, dashboardViewModel, mealPlanAPIDataAccessInterface, viewManagerModel, mealPlanDataAccessObject, userDataAccessObject);

        return new MealPlanSearchView(mealPlanController, mealPlanViewModel);
    }
}
