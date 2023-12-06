package InterfaceAdapters.ViewFavorites;

import UseCase.ViewFavorites.ViewFavoritesInputBoundary;

public class ViewFavoritesController {
    private final ViewFavoritesInputBoundary interactor;

    public ViewFavoritesController(ViewFavoritesInputBoundary interactor) {
        this.interactor = interactor;
    }
    public void execute(){
        interactor.execute();
    }
}
