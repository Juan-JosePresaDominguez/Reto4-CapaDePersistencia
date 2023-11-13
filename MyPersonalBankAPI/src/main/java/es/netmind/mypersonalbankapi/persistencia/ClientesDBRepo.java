package es.netmind.mypersonalbankapi.persistencia;

import es.netmind.mypersonalbankapi.exceptions.ClienteNotFoundException;
import es.netmind.mypersonalbankapi.modelos.clientes.Cliente;
import es.netmind.mypersonalbankapi.modelos.clientes.Empresa;
import es.netmind.mypersonalbankapi.modelos.clientes.Personal;
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
    public List<Cliente> getAll() throws Exception {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM cliente u WHERE 1";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                    if (rs.getString("dtype").equals("Empresa")) {
                        clientes.add(new Empresa(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("email"),
                                rs.getString("direccion"),
                                rs.getDate("alta").toLocalDate(),
                                rs.getBoolean("activo"),
                                rs.getBoolean("moroso"),
                                rs.getString("cif"),
                                new String[]{rs.getString("unidades_de_negocio")}));
                    } else {
                            clientes.add(new Personal(
                                    rs.getInt("id"),
                                    rs.getString("nombre"),
                                    rs.getString("email"),
                                    rs.getString("direccion"),
                                    rs.getDate("alta").toLocalDate(),
                                    rs.getBoolean("activo"),
                                    rs.getBoolean("moroso"),
                                    rs.getString("dni")));
                        }
                    }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return clientes;
    }

    @Override       //Devuelve el cliente indicado por parámetro
    public Cliente getClientById(Integer id) throws Exception {
        Cliente cliente = null;
        String sql = "SELECT c.* FROM cliente c WHERE id=?";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                // ordenes sql
                PreparedStatement pstm = conn.prepareStatement(sql);
        ) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getString("dtype").equals("Empresa")) {
                    cliente = new Empresa(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("direccion"),
                            rs.getDate("alta").toLocalDate(),
                            rs.getBoolean("activo"),
                            rs.getBoolean("moroso"),
                            rs.getString("cif"),
                            new String[]{rs.getString("unidades_de_negocio")});
                } else {
                    cliente = new Personal(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("direccion"),
                            rs.getDate("alta").toLocalDate(),
                            rs.getBoolean("activo"),
                            rs.getBoolean("moroso"),
                            rs.getString("dni"));
                }
            } else {
                throw new ClienteNotFoundException();
            }
        }

        return cliente;
    }

    @Override
    public Cliente addClient(Cliente cliente) throws Exception {
        return null;
    }

    @Override       //INSERT
    public Cliente addClientPersonal(Personal cliente) throws Exception {
        String sql = "INSERT INTO cliente (`dtype`, `id`, `nombre`, `email`, `direccion`, `alta`, `activo`, `moroso`, `dni`)  values (?,NULL,?,?,?,?,?,?,?)";

        try (
                Connection conn = DriverManager.getConnection(db_url);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, "Personal");
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getAlta().toString());
            stmt.setBoolean(6, cliente.isActivo());
            stmt.setBoolean(7, cliente.isMoroso());
            stmt.setString(8, cliente.getDni());

            int rows = stmt.executeUpdate();

            ResultSet genKeys = stmt.getGeneratedKeys();
            if (genKeys.next()) {
                cliente.setId(genKeys.getInt(1));
            } else {
                throw new SQLException("Usuario creado erróneamente!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return cliente;
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
