package application;

import domain.ports.Repository;
import infrastructure.InMemoryRepository;

public final class FinancialSystemConfiguration {

    public static FinancialSystem financialSystem(Repository repository) {
        return SimpleFinancialSystem.newInstance(repository);
    }

    public static FinancialSystem financialSystem() {
        return financialSystem(new InMemoryRepository());
    }

}
