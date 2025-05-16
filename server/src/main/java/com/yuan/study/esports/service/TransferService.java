package com.yuan.study.esports.service;

import com.yuan.study.esports.controller.api.CompleteTransferCommand;
import com.yuan.study.esports.controller.api.ReviewTransferCommand;
import com.yuan.study.esports.controller.api.SubmitTransferCommand;
import com.yuan.study.esports.domain.Player;
import com.yuan.study.esports.domain.Transfer;
import com.yuan.study.esports.repository.PlayerRepository;
import com.yuan.study.esports.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final PlayerRepository playerRepository;

    @Transactional
    public SubmitTransferCommand.Result submit(SubmitTransferCommand command) {
        Transfer transfer = command.toTransfer();
        if (transferRepository.existPendingByPlayerId(transfer.getPlayerId())) {
            return SubmitTransferCommand.Result.ofExisted();
        }
        Optional<Player> transPlayer = playerRepository.query(transfer.getPlayerId());
        if (transPlayer.isEmpty()) {
            return SubmitTransferCommand.Result.ofPlayerNotExist();
        }
        if (Objects.equals(transPlayer.get().getClubId(), transfer.getToClubId())) {
            return SubmitTransferCommand.Result.ofApplyOwnClub();
        }
        transferRepository.save(transfer);
        return SubmitTransferCommand.Result.ofSuccess();
    }

    @Transactional
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
        if (Objects.equals(Transfer.Status.approved, transfer.getStatus())) {
            Optional<Player> transPlayer = playerRepository.query(transfer.getPlayerId());
            if (transPlayer.isEmpty()) {
                return ReviewTransferCommand.Result.ofNone();
            }
            transPlayer.get().setClubId(transfer.getToClubId());
            transPlayer.get().setJoinDate(LocalDate.now());
            playerRepository.update(transPlayer.get());
        }
        transferRepository.update(transfer);
        return ReviewTransferCommand.Result.ofSuccess();
    }

    public CompleteTransferCommand.Result complete(Long id) {
        return null;
    }
}
