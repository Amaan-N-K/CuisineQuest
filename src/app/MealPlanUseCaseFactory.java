package app;


import InterfaceAdapters.MealPlanCreation.MealPlanController;
import InterfaceAdapters.MealPlanCreation.MealPlanPresenter;
import InterfaceAdapters.MealPlanCreation.MealPlanViewModel;
import InterfaceAdapters.ViewManagerModel;
import InterfaceAdapters.dashboard.DashboardViewModel;
import UseCase.MealPlanCreation.*;
import View.MealPlanSearchView;
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
