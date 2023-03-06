import java.util.List;

public class Match {
    private List<Solution> solutions;
    private Game game;
    private MatchFinishedObserver matchFinishedObserver;
    private MatchReport report;

    public Match(List<Solution> solutions, Game game) {
        this.solutions = solutions;
        this.game = game;
    }

    public void run(MatchFinishedObserver observer) {
        this.matchFinishedObserver = observer;
        new InvokerPull().getInstance().addToPool(
                new InvokerRequest(
                        prepareInvokers(),
                        createReport,
                        Some(setupInvokers)
                )
        );
    }

    private void setupInvokers() {

    }

    private void createReport() {
        matchFinishedObserver.receive(report);
    }
}