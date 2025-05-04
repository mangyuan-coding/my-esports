package com.yuan.study.esports.service;

import com.yuan.study.esports.controller.api.CreateClubCommand;
import com.yuan.study.esports.controller.api.UpdateClubCommand;
import com.yuan.study.esports.domain.Club;
import com.yuan.study.esports.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    @Transactional
    public CreateClubCommand.Result create(CreateClubCommand command) {
        if (clubRepository.existedByName(command.getName())) {
            return CreateClubCommand.Result.ofExisted();
        }
        clubRepository.save(command.toClub());
        return CreateClubCommand.Result.ofSuccess();
    }

    @Transactional
    public UpdateClubCommand.Result update(Long id, UpdateClubCommand command) {
        Optional<Club> queried = clubRepository.query(id);
        if (queried.isEmpty()) {
            return UpdateClubCommand.Result.ofNone();
        }
        Club existed = queried.get();
        command.merge(existed);
        clubRepository.update(existed);
        return UpdateClubCommand.Result.ofSuccess();
    }


    public void delete(Long id) {
        clubRepository.delete(id);
    }
}
