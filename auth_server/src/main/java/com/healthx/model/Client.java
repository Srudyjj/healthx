package com.healthx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("client_id")
    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String secret;

    @Column(nullable = false)
    private String scope;

    @Column(name = "redirect_uri", nullable = false)
    private String redirectUri;

    @OneToMany(mappedBy ="client", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<ClientGrantType> grantTypes = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public List<ClientGrantType> getGrantTypes() {
        return grantTypes;
    }

    private void setGrantTypes(List<ClientGrantType> grantTypes) {
        this.grantTypes = grantTypes;
    }

    public void addGrantType(ClientGrantType grantType) {
        grantType.setClient(this);
        this.grantTypes.add(grantType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientId='" + clientId + '\'' +
                ", secret='" + "******" + '\'' +
                ", scope='" + scope + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                '}';
    }
}
