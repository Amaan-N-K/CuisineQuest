package View;

import InterfaceAdapters.ViewFavorites.ViewFavoritesState;
import InterfaceAdapters.ViewFavorites.ViewFavoritesViewModel;
import InterfaceAdapters.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewFavoritesView extends JPanel implements PropertyChangeListener {
    public final String viewName = "view favorites";
    private final ViewFavoritesViewModel viewModel;
    private final ViewManagerModel viewManagerModel;
    private final JList<String> favoriteRecipesList;
    private final JButton backButton;

    public ViewFavoritesView(ViewFavoritesViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.favoriteRecipesList = new JList<>();
        this.backButton = new JButton("Back");
        viewModel.addPropertyChangeListener(this);
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        add(new JScrollPane(favoriteRecipesList), BorderLayout.CENTER);

        backButton.addActionListener(this::handleBackAction);
        add(backButton, BorderLayout.SOUTH);
    }

    private void handleBackAction(ActionEvent e) {
        viewManagerModel.setActiveView("dashboard");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if ("state".equals(propertyChangeEvent.getPropertyName())) {
            ViewFavoritesState state = viewModel.getState();
            favoriteRecipesList.setListData(state.getFavoriteRecipes().toArray(new String[0]));

            if (state.getFavoriteRecipes().isEmpty()) {
                JOptionPane.showMessageDialog(this, state.getMessage(), "Favorites", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}

