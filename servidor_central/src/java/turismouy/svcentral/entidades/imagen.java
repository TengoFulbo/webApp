package turismouy.svcentral.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class imagen {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isUser;
    
    // @OneToOne(mappedBy = "imagen", targetEntity = usuario.class)
    // @OneToOne
    // @JoinColumn(name = "usuario_pk")
    // private usuario usuario; 

    private byte[] data; // Almacena los datos binarios de la imagen

    public imagen() {};
    
    public imagen(boolean isUser, usuario usuario, byte[] data) {
        this.isUser = isUser;
        // this.usuario = usuario;
        this.data = data;
    };

    public Long getId() {
        return this.id;
    };

    public boolean getIsUser() {
        return this.isUser;
    };

    // public usuario getUsuario() {
    //     return this.usuario;
    // };

    public byte[] getData() {
        return this.data;
    };

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    };

    // public void setUsuario(usuario usuario) {
    //     this.usuario = usuario;
    // };

    public void setData(byte[] data) {
        this.data = data;
    };
}
