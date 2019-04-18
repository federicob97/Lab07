package it.polito.tdp.poweroutages.model;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<Nerc> ln;
	List<PowerOutages> totale;
	List<PowerOutages> best;
	int maxpers;
	int termina;
	
	public Model() {
		podao = new PowerOutageDAO();
		ln = new ArrayList<Nerc>(podao.getNercList());
		totale = new ArrayList<PowerOutages>();
		best = new ArrayList<PowerOutages>();
		maxpers=0;
		termina=0;
	}
	
	public List<Nerc> getNercList() {
		return ln;
	}
	public Nerc getNerc(String value) {
		Nerc res = null;
		for(Nerc n : ln) {
			if(n.getValue().compareTo(value)==0) {
				res = n;
				break;
			}	
		}
		return res;
	}
	
	public List<PowerOutages> ricorsione(Nerc n, int year,int hour) {
		
		this.totale = new ArrayList<PowerOutages>(podao.getOutages(n));
		List<PowerOutages> parziale = new ArrayList<PowerOutages>();
		//best = new ArrayList<PowerOutages>();
		best.clear();
		recursive(parziale,year,hour,0);
		
		return best;
		
	}
	
	private void recursive(List<PowerOutages> parziale,int year,int hour, int L) {
		
		//condizione terminazione
	//	if(L==totale.size()) {
			int nuevo = calcolaPersone(parziale);
			if(best==null || nuevo>maxpers) {
				this.best = new ArrayList<PowerOutages>(parziale);
			}
		//}
	//	else {
		// troppi anni
		//troppe ore
			for(PowerOutages po : totale) {
				if(aggiuntaValida(parziale,year,hour,po)){
					//aggiunta
					parziale.add(po);
					//ricorsione
					recursive(parziale,year,hour,L+1);
					//back
					parziale.remove(parziale.size()-1);
				}	
			}
			
		//}
		
	}
	
	public int calcolaPersone(List<PowerOutages> best2) {
			int pers =0;
			for(PowerOutages po : best2) {
				pers+=po.getCostomersAffected();
			}
		return pers;
	}

	/*private int calcolaOre(List<PowerOutages> par,PowerOutages po) {
		
		int monteOre=0;
		for(PowerOutages p : par) {
			monteOre += (int) p.getDateBegan().until(p.getDateFinished(), ChronoUnit.HOURS);
		}
		int oreNuove =(int) po.getDateBegan().until(po.getDateFinished(), ChronoUnit.HOURS);
		return monteOre + oreNuove;
			
	}
	
	private int calcolaDifAnni(List<PowerOutages> par,PowerOutages po) {
		int diff=0;
			for(PowerOutages p : par) {
				int d =(int) p.getDateBegan().until(po.getDateBegan(), ChronoUnit.YEARS);
				if(d>diff)
					diff=d;
			}
		return diff;
	}*/
	
	private boolean aggiuntaValida(List<PowerOutages> par, int year,int hour,PowerOutages po) {
		if(par.contains(po))
			return false;
		
		int diff=0;
		int monteOre=0;
		for(PowerOutages p : par) {
			monteOre += (int) p.getDateBegan().until(p.getDateFinished(), ChronoUnit.HOURS);
			int d =(int) p.getDateBegan().until(po.getDateBegan(), ChronoUnit.YEARS);
			if(d>diff)
				diff=d;
		}
		monteOre += (int) po.getDateBegan().until(po.getDateFinished(), ChronoUnit.HOURS);
		
		if(monteOre<=hour && diff<=year)
			return true;
		else
			return false;
		
		
		/*if(calcolaDifAnni(par,po)<=year && calcolaOre(par,po)<=hour)
			return true;
		else
			return false;*/
	}
	
	private int calcolaBlock(List<PowerOutages> par) {
		int monteOre=0;
		for(PowerOutages p : par) {
			monteOre += (int) p.getDateBegan().until(p.getDateFinished(), ChronoUnit.HOURS);
		}
		return monteOre;
	}
}
