package fr.gemeroi.population.entry;


public class EntrySRT extends EntryST {

	public EntrySRT(int rank, String dateStart, String dateEnd,
			String subtitle) {
		super(dateStart, dateEnd, subtitle, rank);
		this.rank = rank;
	}
}
