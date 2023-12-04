package app;


import InterfaceAdapters.MealPlanCreation.MealPlanController;
import InterfaceAdapters.MealPlanCreation.MealPlanPresenter;
import InterfaceAdapters.MealPlanCreation.MealPlanViewModel;
import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.dashboard.DashboardViewModel;
import UseCase.MealPlanCreation.*;
import View.MealPlanSearchView;


public class MealPlanUseCaseFactory {
    private MealPlanUseCaseFactory() {}

    private static MealPlanController createMealPlanController(MealPlanViewModel mealPlanViewModel, DashboardViewModel dashboardViewModel,
                                                               MealPlanAPIDataAccessInterface mealPlanAPIDataAccessInterface, ViewManagerModel viewManagerModel) {

        MealPlanOutputBoundary mealPlanPresenter = new MealPlanPresenter(viewManagerModel, mealPlanViewModel, dashboardViewModel);

        MealPlanInputBoundary mealPlanInteractor = new MealPlanInteractor(mealPlanAPIDataAccessInterface, mealPlanPresenter);

        return new MealPlanController(mealPlanInteractor);
    }

    public static MealPlanSearchView createMealPlanView(
            MealPlanViewModel mealPlanViewModel,
            DashboardViewModel dashboardViewModel,
            MealPlanAPIDataAccessInterface mealPlanAPIDataAccessInterface, ViewManagerModel viewManagerModel) {

        MealPlanController mealPlanController = createMealPlanController(mealPlanViewModel, dashboardViewModel, mealPlanAPIDataAccessInterface, viewManagerModel);

        return new MealPlanSearchView(mealPlanController, mealPlanViewModel);
    }
}
