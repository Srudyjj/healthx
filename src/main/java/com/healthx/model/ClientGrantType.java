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

    @Column(name = "client_id", nullable = false)
    private long clientId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
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

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientGrantType that = (ClientGrantType) o;
        return id == that.id && clientId == that.clientId && Objects.equals(grantType, that.grantType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grantType, clientId);
    }

    @Override
    public String toString() {
        return "ClientGrantType{" +
                "id=" + id +
                ", grantType='" + grantType + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
