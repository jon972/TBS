package fr.gemeroi.population.reader;

import java.util.List;

import fr.gemeroi.population.entry.EntryST;

public interface SubtitleReader {
    public void read();
    public EntryST getEntry(int entryPos);
    public List<EntryST> getListEntries();
} 
