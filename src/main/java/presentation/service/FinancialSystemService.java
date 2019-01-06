package presentation.service;

import static presentation.service.FinancialSystemServiceConfiguration.financialSystemService;

import infrastructure.ProductionRepository;

final class FinancialSystemService {

    public static void main(String[] args) throws Exception {
        financialSystemService(ProductionRepository.newInstance());
    }
}
