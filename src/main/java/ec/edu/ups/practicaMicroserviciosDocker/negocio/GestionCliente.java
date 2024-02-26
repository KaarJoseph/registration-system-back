package ec.edu.ups.practicaMicroserviciosDocker.negocio;

import ec.edu.ups.practicaMicroserviciosDocker.dao.ClienteDAO;
import ec.edu.ups.practicaMicroserviciosDocker.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class GestionCliente {
    @Inject
    private ClienteDAO clienteDAO;
    boolean bandera=false;
    public void guardarCliente(Cliente cliente) throws Exception {
        if(!this.verificadorCedula(cliente.getCedula())) {
            throw new Exception("la cedula es incorrecta");
        }else {
            if(clienteDAO.read(cliente.getCedula())==null) {
                try {
                    clienteDAO.create(cliente);
                } catch (Exception e) {
                    throw new Exception("Error al insertar: " + e.getMessage());
                }
            } else {
                try {
                    clienteDAO.update(cliente);
                } catch (Exception e) {
                    throw new Exception("Error al actualizar: "+e.getMessage());
                }
            }

        }
    }

    public boolean verificadorCedula(String cedula) throws Exception {
        int c;
        int suma = 0;
        int contador;
        int resta = 0;
        boolean bandera = false;
        if (cedula.length() != 10) {
            throw new Exception("La cedula debe tener 10 digitos ");
        }
        for (int i = 0; i < cedula.length(); i++) {
            if (!(cedula.charAt(i) == '0' || cedula.charAt(i) == '1' || cedula.charAt(i) == '2'
                    || cedula.charAt(i) == '3' || cedula.charAt(i) == '4' || cedula.charAt(i) == '5'
                    || cedula.charAt(i) == '6' || cedula.charAt(i) == '7' || cedula.charAt(i) == '8'
                    || cedula.charAt(i) == '9')) {
                throw new Exception("La cedula solo debe tener numeros");
            }
        }
        int h = cedula.length();
        for (int i = 0; i < h - 1; i++) {
            c = Integer.parseInt(cedula.charAt(i) + "");
            if (i % 2 == 0) {
                c = c * 2;
                if (c > 9) {
                    c = c - 9;
                }
            }
            suma = suma + c;
        }
        if (suma % 10 != 0) {
            contador = ((suma / 10) + 1) * 10;
            resta = contador - suma;
        }
        int ultimo = Integer.parseInt(cedula.charAt(9) + "");
        if (ultimo == resta) {
            System.out.println("La cedula ingresada es correcta");
            bandera = true;
            System.out.println(bandera);
            return bandera;
        } else {
            System.out.println(bandera);
            return bandera;
        }


    }
    //Listar
    public List<Cliente> listar(){
        return clienteDAO.getAll();
    }
    //Borrar
    public boolean borrar(String cedula) {
        clienteDAO.delete(cedula);
        return true;
    }
    //Buscar
    public Cliente buscar(String cedula) {
        return clienteDAO.read(cedula);
    }
}