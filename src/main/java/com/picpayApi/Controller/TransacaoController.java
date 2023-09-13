package com.picpayApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.picpayApi.Model.Transacao;
import com.picpayApi.Model.User;
import com.picpayApi.Service.TransacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/pagamento")
    public ResponseEntity<?> realizaPagamento(@RequestBody @Valid Transacao transacao) {
        // lojista não pode enviar pagamento
        // validar saldo
        if (transacaoService.validaTransacao(transacao)) {

            return ResponseEntity.ok(transacaoService.realizaTransacao(transacao));
        }
        return ResponseEntity.badRequest().body("erro");
    }
}
