package com.healthx.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "client_grant_types")
public class ClientGrantType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "grant_type", nullable = false)
    private String grantType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", foreignKey=@ForeignKey(name = "FK_grant_types_client"))
    private Client client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientGrantType that = (ClientGrantType) o;
        return id == that.id && Objects.equals(grantType, that.grantType) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grantType, client);
    }

    @Override
    public String toString() {
        return "ClientGrantType{" +
                "id=" + id +
                ", grantType='" + grantType + '\'' +
                ", client=" + client +
                '}';
    }
}
