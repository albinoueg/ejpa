package br.com.albinomoreira.ecommerce.mapeamentoavancao;

import br.com.albinomoreira.ecommerce.EntityManagerTest;
import br.com.albinomoreira.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PropriedadesTransientesTest extends EntityManagerTest {

    @Test
    public void validarPrimeiroNome(){
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assert.assertEquals("Fernando", cliente.getPrimeiroNome());
    }
}
