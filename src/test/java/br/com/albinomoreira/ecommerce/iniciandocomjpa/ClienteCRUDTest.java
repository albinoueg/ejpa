package br.com.albinomoreira.ecommerce.iniciandocomjpa;

import br.com.albinomoreira.ecommerce.EntityManagerTest;
import br.com.albinomoreira.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class ClienteCRUDTest extends EntityManagerTest {

    @Test
    public void create(){
        Cliente cliente = new Cliente();
        cliente.setId(3);
        cliente.setNome("Albino Moreira dos Santos");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteConfirmacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertEquals(cliente.getNome(), clienteConfirmacao.getNome());
    }

    @Test
    public void read(){
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Assert.assertNotNull(cliente);
    }

    @Test
    public void update(){
        Cliente cliente = entityManager.find(Cliente.class, 1);
        cliente.setNome("Teste Update");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Cliente clienteConfirmacao = entityManager.find(Cliente.class, 1);
        Assert.assertEquals("Teste Update", clienteConfirmacao.getNome());
    }

    @Test
    public void delete(){
        Cliente cliente = entityManager.find(Cliente.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteConfirmacao = entityManager.find(Cliente.class, 1);
        Assert.assertNull(clienteConfirmacao);

    }
}
