package fr.opticycle;

import java.util.HashSet;
import java.util.Set;

public enum Region {

    TEST(new Ville("test", 0, 0, 0, "test"), 0, 0, new HashSet<String>());

    private final Ville chefLieu;
    private final int superficie;
    private final int population2024;
    private final Set<String> departements;

    Region(Ville chefLieu, int superficie, int population2024, Set<String> departements) {
        this.chefLieu = chefLieu;
        this.superficie = superficie;
        this.population2024 = population2024;
        this.departements = departements;
    }

    public static Region getRegion(Ville ville) {
        return TEST;
    }

    @Override
    public String toString() {
        return "Region{" +
                "chefLieu=" + chefLieu +
                ", superficie=" + superficie +
                ", population2024=" + population2024 +
                ", departements=" + departements +
                '}';
    }
}
