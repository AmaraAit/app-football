package com.amara.matchs_service.entities;

import java.util.Comparator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Matchs {
	@Id 
	private String matchId;
	private String homeTeam;
	private String awayTeam;
	private String dateMatch;
	private String league;
	private int goalHomeTeam;
	private int goalAwayTeam;
	
	public static Comparator<Matchs> ComparatorDate=new Comparator<Matchs>() {
		
		@Override
		public int compare(Matchs o1, Matchs o2) {
			// TODO Auto-generated method stub
			return o1.getDateMatch().compareTo(o2.getDateMatch());
		}
	};

}
