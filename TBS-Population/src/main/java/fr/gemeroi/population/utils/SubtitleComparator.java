package fr.gemeroi.population.utils;

import java.util.Comparator;

import fr.gemeroi.persistence.bean.Subtitle;

public class SubtitleComparator implements Comparator<Subtitle> {

	@Override
	public int compare(Subtitle sub1, Subtitle sub2) {
		if (sub1.getTimeend() > sub2.getTimeend())
			return -1;
		if (sub1.getTimeend() < sub2.getTimeend())
			return 1;
		return 0;
	}

}
