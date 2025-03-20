
package com.carango.bom.secret;

import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SecretKeyGeneratorTest {

	//Teste Gerar chave criptográfica Token JWT - Chave é inseria na Classe JwtUtil
	//SECRET_KEY = Base64.getDecoder().decode("Chave Gerada");
    @Test
    public void testGenerateSecretKey() throws Exception {
        // Gera uma chave secreta usando o algoritmo HMAC SHA-256
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGen.generateKey();

        // Codifica a chave secreta em Base64
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        // Verifica se a chave secreta codificada não é nula
        assertNotNull(encodedKey);
        System.out.println("Base64 Encoded Secret Key: " + encodedKey);
    }
}
