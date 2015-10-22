package fr.gemeroi.population.entry;


public class EntrySRT extends EntryST {
	private int rank;
	public EntrySRT(int rank, String dateStart, String dateEnd,
			String subtitle) {
		super(dateStart, dateEnd, subtitle, rank);
		this.rank = rank;
	}

	public int getRank() {
		return this.rank;
	}
}
