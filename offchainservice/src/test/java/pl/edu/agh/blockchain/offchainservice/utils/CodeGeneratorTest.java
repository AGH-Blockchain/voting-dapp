package pl.edu.agh.blockchain.offchainservice.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CodeGeneratorTest {

    @Test
    void generateNewToken() {
        for (int i = 0; i < 10; i++) {
            assertEquals(8, CodeGenerator.generateNewToken().length());
        }
    }
}