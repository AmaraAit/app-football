package com.amara.matchs_service.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amara.matchs_service.entities.Matchs;
import com.amara.matchs_service.service.IMarchsService;

@RestController
public class MatchsController {
	IMarchsService iMarchsService;
	
	public MatchsController(IMarchsService iMarchsService) {
		this.iMarchsService = iMarchsService;
	}

	@RequestMapping(value = "/matchs",method = RequestMethod.GET)
	public List<Matchs> getAllMatchs() throws Exception{
		return iMarchsService.findALLMatchs();
	}
	
	@RequestMapping(value = "/match/{id}",method = RequestMethod.GET)
	public Matchs getMatchsById(@PathVariable String id) throws Exception{
		return iMarchsService.findMatchsById(id);
	}
	
	@RequestMapping(value = "/matchs/{equipe}",method = RequestMethod.GET)
	public List<Matchs> getAllMatchsByEquipe(@PathVariable String equipe) throws Exception{
		return iMarchsService.findALLMatchsByEquipe(equipe);
	}
	
	@RequestMapping(value = "/league/matchs/{league}",method = RequestMethod.GET)
	public List<Matchs> getAllMatchsByLeague(@PathVariable String league) throws Exception{
		return iMarchsService.findAllMatchsByLeague(league);
	}
	
	@RequestMapping(value = "/league/equipes/{league}",method = RequestMethod.GET)
	public List<String> getAllEquipeByLeague(@PathVariable String league) throws Exception{
		return iMarchsService.findAllEquipesByLeague(league);
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.GET)
	public List<Matchs> getAllMatchsByLeague() {
		return iMarchsService.insertData();
	}
	
	
	
	

}
