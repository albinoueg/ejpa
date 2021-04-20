package br.com.albinomoreira.ecommerce.mapeamentobasico;

import br.com.albinomoreira.ecommerce.EntityManagerTest;
import br.com.albinomoreira.ecommerce.enums.SexoCliente;
import br.com.albinomoreira.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class MapeandoEnumeracoesTest extends EntityManagerTest {

    @Test
    public void testarEnum() {
        Cliente cliente = new Cliente();
        cliente.setNome("José Mineiro");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao);
    }
}
