package interface_adapter.view_favorites;

import use_case.view_favorites.ViewFavoritesInputBoundary;

public class ViewFavoritesController {
    private final ViewFavoritesInputBoundary interactor;

    public ViewFavoritesController(ViewFavoritesInputBoundary interactor) {
        this.interactor = interactor;
    }
    public void execute(){
        interactor.execute();
    }
}
