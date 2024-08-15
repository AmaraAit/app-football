package com.amara.matchs_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amara.matchs_service.entities.Matchs;
@Repository
public interface MatchsRepository extends JpaRepository<Matchs, String>{
	

	Matchs findByHomeTeam(String homeTeam);
	List<Matchs> findByLeague(String league);
	List<Matchs> findAllByHomeTeam(String homeTeam);
	List<Matchs> findAllByAwayTeam(String awayTeam);
	List<Matchs> findByDateMatch(String date);


}
