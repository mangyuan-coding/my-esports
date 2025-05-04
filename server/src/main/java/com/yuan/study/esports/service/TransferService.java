package com.yuan.study.esports.service;

import com.yuan.study.esports.controller.api.CompleteTransferCommand;
import com.yuan.study.esports.controller.api.ReviewTransferCommand;
import com.yuan.study.esports.controller.api.SubmitTransferCommand;
import com.yuan.study.esports.domain.Transfer;
import com.yuan.study.esports.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;

    @Transactional
    public SubmitTransferCommand.Result submit(SubmitTransferCommand command) {
        transferRepository.save(command.toTransfer());
        return SubmitTransferCommand.Result.ofSuccess();
    }

    public ReviewTransferCommand.Result review(Long id, ReviewTransferCommand command) {

        Optional<Transfer> queried = transferRepository.query(id);
        if (queried.isEmpty()) {
            return ReviewTransferCommand.Result.ofNone();
        }
        Transfer transfer = queried.get();
        if (!Objects.equals(Transfer.Status.pending, transfer.getStatus())) {
            return ReviewTransferCommand.Result.ofHasReviewed();
        }
        transfer.setStatus(command.getStatus());
        transferRepository.update(transfer);
        return ReviewTransferCommand.Result.ofSuccess();
    }

    public CompleteTransferCommand.Result complete(Long id) {
        return null;
    }
}
