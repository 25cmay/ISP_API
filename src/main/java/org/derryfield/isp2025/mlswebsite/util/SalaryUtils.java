package org.derryfield.isp2025.mlswebsite.util;

import org.derryfield.isp2025.mlswebsite.model.Player;
import org.derryfield.isp2025.mlswebsite.model.enums.SpecialContractDesignations;

import java.util.Map;

import static org.derryfield.isp2025.mlswebsite.model.enums.SpecialContractDesignations.*;

public class SalaryUtils {

    public static final Map<SpecialContractDesignations, Integer> SPECIAL_CONTRACT_DESIGNATIONS_SALARY_MAP = Map.of(
            YOUNG_DESIGNATED_PLAYER, 150000,
            DESIGNATED_PLAYER, 743750
    );
    public static int resolveSalaryImpact(Player player, SpecialContractDesignations designation) {
        switch (designation) {
            case YOUNG_DESIGNATED_PLAYER:
                return 150000;
            case DESIGNATED_PLAYER:
                return 743750;
            case U22_INITIATIVE:
                //todo after birthday is added
                return 0;
            case HOME_GROWN_PLAYER:

            case PROFESSIONAL_PLAYER_DEVELOPMENT_ROLE:
            case GENERATION_ADIDAS:
                return 0;
            case TARGETED_ALLOCATION_MONEY:
            case NONE:
                return 0;
        }
        return 0;
    }
}
