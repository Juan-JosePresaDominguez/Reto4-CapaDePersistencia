package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.properties.PropertyValues;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDBRepo implements IClientesRepo {

    private static String db_url = null;

    public ClientesDBRepo() throws Exception {
        PropertyValues props = new PropertyValues();
        db_url = props.getPropValues().getProperty("db_url");
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();

//        String sql = "SELECT * FROM cliente u WHERE 1";
//
//        try (
//                Connection conn = DriverManager.getConnection(db_url);
//                PreparedStatement stmt = conn.prepareStatement(sql);
//        ) {
//
//            //stmt.setString(1, );
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                clientes.add(
//                        new Cliente(
//                                rs.getInt("id"),
//                                rs.getString("nombre"),
//                                rs.getString("email"),
//                                rs.getString("direccion"),
//                                rs.getDate("alta").toLocalDate(),
//                                rs.getBoolean("activo"),
//                                rs.getBoolean("moroso"),
//                                rs.getString("cif"),
//                                rs.getString("unidadesNegocio"),
//                                rs.getString("dni")
//                        )
//                );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new Exception(e);
//        }

        return clientes;
    }

    @Override
    public Cliente getClientById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Cliente addClient(Cliente cliente) throws Exception {
        return null;
    }

    @Override
    public boolean deleteClient(Cliente cliente) throws Exception {
        return false;
    }

    @Override
    public Cliente updateClient(Cliente cliente) throws Exception {
        return null;
    }
}
