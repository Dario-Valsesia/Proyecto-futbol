package com.mindhub.proyectoFinal.dtos;

import com.mindhub.proyectoFinal.modelos.Cliente;
import com.mindhub.proyectoFinal.modelos.ProductoCliente;
import com.mindhub.proyectoFinal.modelos.Reserva;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<ReservaDTO> reservas = new HashSet<>();
    private Set<ProductoClienteDTO> productoClientes = new HashSet<>();

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.firstName = cliente.getFirstName();
        this.lastName = cliente.getLastName();
        this.email = cliente.getEmail();
        this.reservas = cliente.getReservas().stream().map(reserva -> new ReservaDTO(reserva)).collect(Collectors.toSet());
        this.productoClientes = cliente.getProductosCliente().stream().map(productoCliente -> new ProductoClienteDTO(productoCliente)).collect(Collectors.toSet());
    }

    public Set<ProductoClienteDTO> getProductoClientes() {
        return productoClientes;
    }

    public void setProductoClientes(Set<ProductoClienteDTO> productoClientes) {
        this.productoClientes = productoClientes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(Set<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
}
