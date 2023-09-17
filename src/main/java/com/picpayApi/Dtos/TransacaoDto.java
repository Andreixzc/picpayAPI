package com.picpayApi.Dtos;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class TransacaoDto {
    private UUID id;
    private double valor;
    private UUID senderId; 
    private UUID receiverId;
}
