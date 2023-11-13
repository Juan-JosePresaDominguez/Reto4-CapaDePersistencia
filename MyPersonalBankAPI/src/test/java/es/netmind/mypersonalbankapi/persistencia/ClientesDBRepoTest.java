package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

class ClientesDBRepoTest {

    private IClientesRepo repo;

    @BeforeEach
    void sepUp() throws Exception {
        repo = new ClientesDBRepo();
    }

    @Test
    void dadosClientes_cuandogetAll_entoncesClientes() {
        List<Cliente> clientes = repo.getAll();

        System.out.println(clientes);

        assertThat(clientes.size(), greaterThan(0));
    }
}
