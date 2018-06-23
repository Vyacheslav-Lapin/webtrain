package lab.model;

import lab.commons.Identifiable;

public interface Country extends Identifiable<Country> {
    String getName();
    Country setName(String name);
    String getCodeName();
    Country setCodeName(String codeName);
}
