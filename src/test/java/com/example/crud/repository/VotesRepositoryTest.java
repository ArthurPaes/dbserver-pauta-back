package com.example.crud.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.crud.domain.model.Votes;

@SpringBootTest
@Transactional
public class VotesRepositoryTest {

    @Autowired
    private VotesRepository votesRepository;

    @BeforeEach
    public void setUp() {
        Votes vote = new Votes();
        vote.setUserId(1L);
        vote.setSectionId(1L);
        vote.setVote(true);
        votesRepository.save(vote);
    }

    @Test
    public void whenFindByUserIdAndSectionId_thenReturnVote() {
        Optional<Votes> foundVote = votesRepository.findByUserIdAndSectionId(1L, 1L);

        assertThat(foundVote).isPresent();
        assertThat(foundVote.get().getUserId()).isEqualTo(1L);
        assertThat(foundVote.get().getSectionId()).isEqualTo(1L);
        assertThat(foundVote.get().getVote()).isEqualTo(true);
    }

    @Test
    public void whenFindByUserIdAndSectionId_thenReturnEmpty() {
        Optional<Votes> foundVote = votesRepository.findByUserIdAndSectionId(2L, 2L);

        assertThat(foundVote).isNotPresent();
    }
}
