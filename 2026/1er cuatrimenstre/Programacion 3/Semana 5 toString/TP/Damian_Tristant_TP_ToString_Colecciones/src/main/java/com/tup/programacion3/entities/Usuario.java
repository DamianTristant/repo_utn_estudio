package com.tup.programacion3.entities;
import com.tup.programacion3.enums.Rol;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Usuario extends Base {
    private String username;
    private String password;
    private String email;
    private Rol rol;
    private Set<Pedido> pedidos; // Relación 1 a m

    // Constructor vacío
    public Usuario() {
        super();
        this.pedidos = new HashSet<>();
    }

    // Constructor con parámetros (Le pasamos el ID a Base también)
    public Usuario(Long id, String username, String password, String email, Rol rol) {
        super(id, false, java.time.LocalDateTime.now());
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.pedidos = new HashSet<>();
    }

    // Método para asociar un pedido a este usuario
    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public Set<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(Set<Pedido> pedidos) { this.pedidos = pedidos; }

    // Sobreescritura de Equals y HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(username, usuario.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    // Sobreescritura de ToString
    @Override
    public String toString() {
        return "Usuario{id=" + getId() + ", username='" + username + "', email='" + email + "', rol=" + rol + ", pedidos_realizados=" + pedidos.size() + "}";
    }

}

