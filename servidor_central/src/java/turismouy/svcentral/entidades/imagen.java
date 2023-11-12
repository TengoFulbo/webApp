package turismouy.svcentral.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class imagen {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isUser;

    private byte[] data; // Almacena los datos binarios de la imagen


    public imagen() {};
    
    public imagen(boolean isUser, byte[] data) {
        this.isUser = isUser;
        this.data = data;
    };

    public Long getId() {
        return this.id;
    };

    public boolean getIsUser() {
        return this.isUser;
    };

    public byte[] getData() {
        return this.data;
    };

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    };

    public void setData(byte[] data) {
        this.data = data;
    };
}
