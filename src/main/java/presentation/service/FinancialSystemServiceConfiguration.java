package presentation.service;

import application.FinancialSystemConfiguration;
import domain.ports.Repository;
import infrastructure.InMemoryRepository;
import ratpack.server.RatpackServer;

public final class FinancialSystemServiceConfiguration {

    public static void financialSystemService(Repository repository) throws Exception {
        var financialSystem = FinancialSystemConfiguration.financialSystem(repository);
        var actionChain = Handlers.newActionChain(financialSystem);
        RatpackServer.start(server -> server.handlers(actionChain));
    }

    public static void financialSystemService() throws Exception {
        financialSystemService(new InMemoryRepository());
    }
}
