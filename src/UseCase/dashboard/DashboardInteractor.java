package UseCase.dashboard;

public class DashboardInteractor implements DashboardInputBoundary {
    private DashboardOutputBoundary presenter;

    public DashboardInteractor(DashboardOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(DashboardInputData inputData) {
        DashboardOutputData outputData = new DashboardOutputData(inputData.getButtonName());
        presenter.updateView(outputData);
    }
}
