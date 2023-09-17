package com.picpayApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpayApi.Dtos.TransacaoDto;
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
        try {
            if (transacaoService.validaTransacao(transacao)) {
                TransacaoDto transacaoDto = transacaoService.realizaTransacao(transacao);
                return ResponseEntity.ok(transacaoDto);
            } else {
                return ResponseEntity.badRequest().body(
                        "A transação não pôde ser concluída devido a saldo insuficiente ou lojista não autorizado.");
            }
        } catch (Exception e) {
            // Lide com exceções de maneira adequada (por exemplo, registre-as ou retorne um
            // erro de servidor interno)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao processar a transação.");
        }
    }
}
