package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PowerOutages {

	int id;
	private Nerc nerc;
	private int costomersAffected;
	private	LocalDateTime dateBegan;
	private LocalDateTime dateFinished;
	public PowerOutages(int id, Nerc nerc, int costomersAffected, LocalDateTime dateBegan, LocalDateTime dateFinished) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.costomersAffected = costomersAffected;
		this.dateBegan = dateBegan;
		this.dateFinished = dateFinished;
	}
	public int getId() {
		return id;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public int getCostomersAffected() {
		return costomersAffected;
	}
	public LocalDateTime getDateBegan() {
		return dateBegan;
	}
	public LocalDateTime getDateFinished() {
		return dateFinished;
	}
	@Override
	public String toString() {
		return String.format("id=%s, costomersAffected=%s, dateBegan=%s, dateFinished=%s", id,
				costomersAffected, dateBegan, dateFinished);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + costomersAffected;
		result = prime * result + ((dateBegan == null) ? 0 : dateBegan.hashCode());
		result = prime * result + ((dateFinished == null) ? 0 : dateFinished.hashCode());
		result = prime * result + id;
		result = prime * result + ((nerc == null) ? 0 : nerc.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (costomersAffected != other.costomersAffected)
			return false;
		if (dateBegan == null) {
			if (other.dateBegan != null)
				return false;
		} else if (!dateBegan.equals(other.dateBegan))
			return false;
		if (dateFinished == null) {
			if (other.dateFinished != null)
				return false;
		} else if (!dateFinished.equals(other.dateFinished))
			return false;
		if (id != other.id)
			return false;
		if (nerc == null) {
			if (other.nerc != null)
				return false;
		} else if (!nerc.equals(other.nerc))
			return false;
		return true;
	}
	
	
}
