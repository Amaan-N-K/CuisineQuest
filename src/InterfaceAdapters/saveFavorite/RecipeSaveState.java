package InterfaceAdapters.saveFavorite;

public class RecipeSaveState {
    private boolean saveSuccessful = false;
    private String message = "";

    public RecipeSaveState(RecipeSaveState state){
        saveSuccessful = state.saveSuccessful;
        message = state.message;
    }


    public RecipeSaveState() {

    }
    public boolean isSaveSuccessful() {
        return saveSuccessful;
    }

    public void setSaveSuccessful(boolean saveSuccessful) {
        this.saveSuccessful = saveSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}



