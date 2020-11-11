package com.example.examapp;

import com.example.examapp.model.Candidate;
import com.example.examapp.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CandidateDetailsService implements UserDetailsService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public UserDetails loadUserByUsername(String candidateId) throws UsernameNotFoundException {
        Optional<Candidate> foundCandidate= candidateRepository.findById(UUID.fromString(candidateId));
        if (foundCandidate.isEmpty()){
            throw new UsernameNotFoundException("Candidate Not found");

        }

            Candidate candidate = foundCandidate.get();

//        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("candidate"));

        return new User(candidate.getCandidateId().toString(),candidate.getPassword(),new ArrayList<>());
}
}
