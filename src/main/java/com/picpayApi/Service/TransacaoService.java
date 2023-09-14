package com.picpayApi.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.picpayApi.Model.Transacao;
import com.picpayApi.Model.User;
import com.picpayApi.Repository.TransacaoRepository;
import com.picpayApi.Repository.UserRepository;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UserRepository userRepository;


    public boolean validaTransacao(Transacao transacao) {

        System.out.println(transacao.getSender().toString());
        System.out.println(transacao.getReceiver().toString());
        Optional<User> sender = userRepository.findById(transacao.getSender().getId());
        if (sender.get().isVarejista() || sender.get().getSaldo() < transacao.getValor()) {
            return false;
        }
        // validador externo

        return true;
    }

    public Transacao realizaTransacao(Transacao transacao) {

        Optional<User> sender = userRepository.findById(transacao.getSender().getId());
        Optional<User> receiver = userRepository.findById(transacao.getReceiver().getId());
        sender.get().setSaldo(sender.get().getSaldo() - transacao.getValor());
        receiver.get().setSaldo(receiver.get().getSaldo() + transacao.getValor());
        userRepository.save(sender.get());
        userRepository.save(receiver.get());
        transacaoRepository.save(transacao);
        return transacao;
    }
}
