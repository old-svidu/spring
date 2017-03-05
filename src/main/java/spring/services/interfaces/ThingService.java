package spring.services.interfaces;

import spring.common.exceptions.ThingDaoException;
import spring.models.entity.Thing;

import java.util.List;

/**
 * Created by root on 02.03.17.
 */
public interface ThingService {
    boolean isThingInserted(Thing thing) throws ThingDaoException;
    List<Thing> selectAllThings() throws ThingDaoException;
    boolean isThingDeleted(int id) throws ThingDaoException;
    boolean isThingUpdated(int id, String thing, int price, int prior) throws ThingDaoException;
}
