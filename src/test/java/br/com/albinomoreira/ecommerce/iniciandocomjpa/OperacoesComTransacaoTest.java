package br.com.albinomoreira.ecommerce.iniciandocomjpa;

import br.com.albinomoreira.ecommerce.EntityManagerTest;
import br.com.albinomoreira.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    public void inserirPrimeiroObjetoComMerge(){
        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("Microfone");
        produto.setDescricao("melhor qualidade");
        produto.setPreco(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificado);
    }

    @Test
    public void atualizarObjetoGerenciado(){
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Teste atualizarObjetoGerenciado");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("Teste atualizarObjetoGerenciado", produtoVerificado.getNome());
    }

    @Test
    public void atualizarObjeto(){
        Produto produto = new Produto();

        produto.setId(1);
        produto.setNome("Teste edição com merge");
        produto.setDescricao("blblablabla");
        produto.setPreco(new BigDecimal(670));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificado);
    }

    @Test
    public void removerObjeto(){
        Produto produto = entityManager.find(Produto.class, 3);
        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificado = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVerificado);
    }

    @Test
    public void inserirPrimeiroObjeto(){
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setDescricao("blblablabla");
        produto.setPreco(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificado);
    }

    @Test
    public void abrirEFecharATransacao(){
        entityManager.getTransaction().begin();
/*

        entityManager.persist(new Produto());
        entityManager.merge(new Produto());
        entityManager.remove(new Produto());

*/

        entityManager.getTransaction().commit();
    }
}
