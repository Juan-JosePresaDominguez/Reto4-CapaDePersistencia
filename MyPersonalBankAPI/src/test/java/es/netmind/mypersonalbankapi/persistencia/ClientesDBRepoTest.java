package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ClientesDBRepoTest {

    private IClientesRepo repo;

    @BeforeEach
    void sepUp() throws Exception {
        repo = new ClientesDBRepo();
    }

    @Test
    void dadosClientes_cuandogetAll_entoncesClientes() throws Exception {
        //Como usuario del sistema, quiero poder ver nuestra lista de clientes para tener una visión general de los mismos.
        List<Cliente> clientes = repo.getAll();

        System.out.println(clientes);

        assertThat(clientes.size(), greaterThan(0));
    }

    @Test
    void dadosClientes_cuandogetClientById_entoncesClientePersonal() throws Exception {
        //Como usuario del sistema, quiero poder ver el detalle de un cliente para entender su perfil.
        Cliente cliente = repo.getClientById(1);

        System.out.println(cliente);

        assertThat(cliente.getId(), is(1));
    }

    @Test
    void dadosClientes_cuandogetClientById_entoncesClienteEmpresa() throws Exception {
        //Como usuario del sistema, quiero poder ver el detalle de un cliente para entender su perfil.
        Cliente cliente = repo.getClientById(3);

        System.out.println(cliente);

        assertThat(cliente.getId(), is(3));
    }
}
