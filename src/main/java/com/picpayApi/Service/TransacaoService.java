package com.picpayApi.Service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpayApi.Dtos.TransacaoDto;
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
        Optional<User> sender = userRepository.findById(transacao.getSender().getId());
        Optional<User> receiver = userRepository.findById(transacao.getReceiver().getId());

        if (sender.isEmpty() || receiver.isEmpty() ||
                sender.get().isVarejista() || sender.get().getSaldo() < transacao.getValor()) {
            return false;
        }

        return true;
    }

    public TransacaoDto realizaTransacao(Transacao transacao) throws Exception {
        TransacaoDto transacaoDto = new TransacaoDto();
        Optional<User> sender = userRepository.findById(transacao.getSender().getId());
        Optional<User> receiver = userRepository.findById(transacao.getReceiver().getId());

        if (!sender.isPresent() || !receiver.isPresent()) {
            throw new Exception("Usuário remetente ou destinatário não encontrado");
        }

        double valor = transacao.getValor();
        sender.get().setSaldo(sender.get().getSaldo() - valor);
        receiver.get().setSaldo(receiver.get().getSaldo() + valor);

        userRepository.save(sender.get());
        userRepository.save(receiver.get());
        transacaoRepository.save(transacao);

        BeanUtils.copyProperties(transacao, transacaoDto);
        transacaoDto.setReceiverId(transacao.getReceiver().getId());
        transacaoDto.setSenderId(transacao.getSender().getId());

        return transacaoDto;
    }
}
