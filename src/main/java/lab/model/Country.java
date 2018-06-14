package lab.model;

import lab.dao.Identifiable;

public interface Country extends Identifiable {
    Country setId(long id);
    String getName();
    Country setName(String name);
    String getCodeName();
    Country setCodeName(String codeName);
}
