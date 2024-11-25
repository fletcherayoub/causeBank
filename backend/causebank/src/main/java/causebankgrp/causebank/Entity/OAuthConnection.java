package causebankgrp.causebank.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "oauth_connections")
public class OAuthConnection {


    @Id
    private String providerId;
    private String provider;
    private String email;
    private String name;
    private String avatarUrl;
    private String accessToken;
    private String refreshToken;

}
