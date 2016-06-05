package fr.gemeroi.population.read;

import java.util.List;

import fr.gemeroi.population.entry.EntryST;

public interface ReadSubtitle {
    public void read();
    public EntryST getEntry(int entryPos);
    public List<EntryST> getListEntries();
} 
