package presentation.frontend;

import application.FinancialSystem;
import infrastructure.ProductionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FinancialSystemConfiguration {

    @Bean
    public FinancialSystem financialSystem() {
        return application.FinancialSystemConfiguration.financialSystem(ProductionRepository.newInstance());
    }

}
