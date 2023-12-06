package app;

import interface_adapter.view_favorites.ViewFavoritesController;
import interface_adapter.view_favorites.ViewFavoritesPresenter;
import interface_adapter.view_favorites.ViewFavoritesViewModel;
import interface_adapter.ViewManagerModel;
import use_case.view_favorites.ViewFavoritesDataAccessInterface;
import use_case.view_favorites.ViewFavoritesInputBoundary;
import use_case.view_favorites.ViewFavoritesInteractor;
import use_case.view_favorites.ViewFavoritesOutputBoundary;
import view.ViewFavoritesView;

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
