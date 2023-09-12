package com.picpayApi.Model;


import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private UUID senderID;

    @Column(nullable = false)
    private UUID recivierID;
}
