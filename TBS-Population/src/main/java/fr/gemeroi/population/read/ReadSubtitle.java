package fr.gemeroi.population.read;

import java.util.List;
import java.io.File;

import fr.gemeroi.population.entry.EntryST;

public interface ReadSubtitle {
    public void read(File fileToRead, String nameSerie, int saison, int episode);
    public EntryST getEntry(int entryPos);
    public List<EntryST> getListEntries();
} 
