package app;

import InterfaceAdapters.ViewFavorites.ViewFavoritesController;
import InterfaceAdapters.ViewFavorites.ViewFavoritesPresenter;
import InterfaceAdapters.ViewFavorites.ViewFavoritesViewModel;
import InterfaceAdapters.ViewManagerModel;
import UseCase.ViewFavorites.ViewFavoritesDataAccessInterface;
import UseCase.ViewFavorites.ViewFavoritesInputBoundary;
import UseCase.ViewFavorites.ViewFavoritesInteractor;
import UseCase.ViewFavorites.ViewFavoritesOutputBoundary;
import View.ViewFavoritesView;

import javax.swing.*;

public class ViewFavoritesUseCaseFactory {
    private ViewFavoritesUseCaseFactory() {}

    public static ViewFavoritesController createViewFavoritesController(
            ViewManagerModel viewManagerModel,
            ViewFavoritesViewModel viewFavoritesViewModel,
            ViewFavoritesDataAccessInterface userDataAccessObject) {

        // Create the Presenter
        ViewFavoritesOutputBoundary presenter = new ViewFavoritesPresenter(viewFavoritesViewModel, viewManagerModel);

        // Create the Interactor
        ViewFavoritesInputBoundary interactor = new ViewFavoritesInteractor(presenter, userDataAccessObject);

        // Create and return the Controller
        return new ViewFavoritesController(interactor);
    }

    public static ViewFavoritesView create(
            ViewManagerModel viewManagerModel,
            ViewFavoritesViewModel viewFavoritesViewModel) {

        try {
            return new ViewFavoritesView(viewFavoritesViewModel, viewManagerModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred in View Favorites use case setup.");
            return null;
        }
    }

}
