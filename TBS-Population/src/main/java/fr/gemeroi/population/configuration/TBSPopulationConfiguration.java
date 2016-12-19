package fr.gemeroi.population.configuration;


import fr.gemeroi.population.SubtitlePersistor;

//@Configuration
public class TBSPopulationConfiguration {

//	@Bean
	SubtitlePersistor subtitlePersistor() {
		return new SubtitlePersistor("(.*)(\\d+)x(\\d+)(.*)");
	}
}
