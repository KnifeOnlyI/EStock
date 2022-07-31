package fr.knife.estockapi.service;

import fr.knife.estockapi.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * The StockService test class
 */
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {HTMLService.class})
class StockServiceTest extends BaseTest {
    /**
     * The service to manage stocks
     */
    private StockService stockService;

    /**
     * The service to manage HTTP operations
     */
    @Mock
    private HTTPService httpService;

    /**
     * The service to manage HTML operations
     */
    @Autowired
    private HTMLService htmlService;

    /**
     * Setup test
     */
    @BeforeEach
    void setUp() {
        this.stockService = new StockService(this.httpService, this.htmlService, null);
    }

    /**
     * Test a valid get value
     */
    @Test
    void testValidGetValue() {
        Mockito.when(this.httpService.fetch(Mockito.any())).thenReturn(VALID_STOCK_HTML);
        Assertions.assertEquals(20.000d, this.stockService.getValue("FR0000130809"));
    }
}
