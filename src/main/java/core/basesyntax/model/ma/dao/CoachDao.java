package core.basesyntax.model.ma.dao;

import core.basesyntax.model.ma.Coach;
import java.util.List;

public interface CoachDao extends PersonDao<Coach> {
    List<Coach> getWithExperienceMoreThan(Integer years);
}
