package com.amara.matchs_service.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.amara.matchs_service.entities.Matchs;
import com.amara.matchs_service.repository.MatchsRepository;
@Service
public class MatchsServiceImpl implements IMarchsService{
MatchsRepository matchsRepository;
	
	public MatchsServiceImpl(MatchsRepository matchsRepository) {
		this.matchsRepository = matchsRepository;
	}

	
	

	@Override
	public Matchs findMatchsById(String id) {
		
		return matchsRepository.findById(id).get();
	}

	@Override
	public List<Matchs> findALLMatchs() {
		
		return matchsRepository.findAll();
	}

	@Override
	public List<Matchs> findALLMatchsByEquipe(String equipeName) {
		List<Matchs> listMacths=matchsRepository.findAllByHomeTeam(equipeName);
		listMacths.addAll(matchsRepository.findAllByAwayTeam(equipeName));
		Collections.sort(listMacths, Matchs.ComparatorDate);
		
		return listMacths;
	}
	@Override
	public List<String> findAllEquipesByLeague(String name){
		List<String> equipes=new ArrayList<>();
		for (Matchs m : matchsRepository.findByLeague(name)) {
			if(!equipes.contains(m.getHomeTeam())) {
				equipes.add(m.getHomeTeam());
			}
			
		}
		
		return equipes;
	}

	@Override
	public List<Matchs> findAllMatchsByLeague(String leagueName) {
		List<String> equipes=new ArrayList<>();
		for (Matchs m : matchsRepository.findByLeague(leagueName)) {
			if(!equipes.contains(m.getHomeTeam())) {
				equipes.add(m.getHomeTeam());
			}
			
		}
		for (String e : equipes) {
			System.out.println(e);
		}
		
		return matchsRepository.findByLeague(leagueName);
	}

	@Override
	public List<Matchs> findAllMatchsByDate(String dateMatch) {
		
		return matchsRepository.findByDateMatch(dateMatch);
	}


public List<Matchs> getListMatchs(String url,String saison,String championnat) {
		
		List<Matchs> listMatch=new ArrayList<>();
		try {
			
			Document doc = Jsoup.connect(url).get();
			Elements newsHeadlines = doc.select("table.stats_table");
			String d=null;
			String score;
			int bEI=-1;
			int bEO=-1;
			for (Element headline :  newsHeadlines.select("tr")) {
				d=headline.select("td[data-stat=date]").text();
				Elements scoreurl=headline.select("td[data-stat=score]");
				score=headline.select("td[data-stat=score]").text();;
				if(score!="" && !score.contains("(")) {
					String a="–";
					String b=" ";
				    score=score.replace(a, b);
				   
					String [] but=score.split(b);
					
					if(but.length>1) {
						bEI=Integer.parseInt(but[0]);
						bEO=Integer.parseInt(but[1]);
						
						if(d!="") {
							String id=headline.select("td[data-stat=home_team]").text()+headline.select("td[data-stat=away_team]").text()+d;
						Matchs m=new Matchs(id,headline.select("td[data-stat=home_team]").text() , headline.select("td[data-stat=away_team]").text(),d, championnat, bEI, bEO);
						//Optional<Matchs> mA=matchsRepository.findById(id);
						//if(mA == null) {
							matchsRepository.save(m);
							listMatch.add(m);
						//}
								
						
						}
					}
				}else if(headline.select("td[data-stat=home_team]").text()!=""){
					
					String id=headline.select("td[data-stat=home_team]").text()+headline.select("td[data-stat=away_team]").text()+d;
					
					Matchs m=new Matchs(id, headline.select("td[data-stat=home_team]").text(), headline.select("td[data-stat=away_team]").text(), d, championnat,1,1);
					//Optional<Matchs> mA=matchsRepository.findById(id);
					//if(mA == null) {
						matchsRepository.save(m);		
						listMatch.add(m);
					//}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listMatch;
	}

	@Override
	public List<Matchs> insertData() {
		String url=null;
		String[] saisons= {"2016-2017",
				"2017-2018","2018-2019","2019-2020","2020-2021",
				"2021-2022","2022-2023","2023-2024"};
	    int [] index= {9,12,20,11,13,23,32};
	    String[] championnat= {"Premier-League","La-Liga","Bundesliga","Serie-A",
	    		"Ligue-1","Eredivisie","Primeira-Liga","Belgian-Pro-League"};
	    String com="/calendrier/Calendrier-et-resultats-";
	    String h2tp="https://fbref.com/fr/comps/";
	    List<Matchs> nextMatch=new ArrayList<>();
	    int i=0;
		for(String ch : championnat) {
			for (String s : saisons) {
				url=h2tp+index[i]+"/"+s+com+s+"-"+ch;
				for (Matchs matchsNext : getListMatchs(url, s,ch)) {
					
					nextMatch.add(matchsNext);
				}
					
			}
			i+=1;
			
		}
		return nextMatch;
	}

}
