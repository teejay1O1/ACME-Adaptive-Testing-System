package com.example.examapp.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import com.example.examapp.model.Candidate;
import com.example.examapp.model.Exam;
import com.example.examapp.service.CandidateService;
import com.example.examapp.service.ExamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private ExamService examService;
    private final String SECRET_KEY="secret";

    public String extractCandidateId(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails candidateDetails){
        Map<String,Object> claims= new HashMap<>();

        String candidateId=candidateDetails.getUsername();
        return createToken(claims, candidateId);
    }

    public String createToken(Map<String,Object> claims, String candidateId){
        Candidate candidate= candidateService.getCandidateById(UUID.fromString(candidateId));
        UUID examId=candidate.getExam();
        Exam exam= examService.getExamById(examId);
        int examDurationMillis= exam.getExamDurationMins() * 1000 * 60;
        return Jwts.builder().setClaims(claims).setSubject(candidateId).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+ examDurationMillis))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token,UserDetails userDetails){
        final String candidateId = extractCandidateId(token);
        return (candidateId.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
