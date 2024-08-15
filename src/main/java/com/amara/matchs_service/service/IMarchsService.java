package com.amara.matchs_service.service;

import java.util.List;
import java.util.Optional;

import com.amara.matchs_service.entities.Matchs;

public interface IMarchsService {
	
	Matchs findMatchsById(String id);
	List<Matchs> findALLMatchs();
	List<Matchs> findALLMatchsByEquipe(String equipeName);
	List<Matchs> findAllMatchsByLeague(String leagueName);	
	List<Matchs> findAllMatchsByDate(String dateMatch);
	List<Matchs> insertData();
	List<String> findAllEquipesByLeague(String name);
}
