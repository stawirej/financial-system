package presentation.frontend;

import application.FinancialSystem;
import application.FinancialSystemConfiguration;
import infrastructure.ProductionRepository;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
class Configuration {

    @Bean
    public FinancialSystem financialSystem() {
        return FinancialSystemConfiguration.financialSystem(ProductionRepository.newInstance());
    }

}
