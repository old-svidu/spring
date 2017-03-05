package spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.common.exceptions.ThingDaoException;
import spring.models.dao.ThingDao;
import spring.models.entity.Thing;
import spring.services.interfaces.ThingService;

import java.util.List;

/**
 * Created by root on 01.03.17.
 */
@Service
public class ThingServiceImpl  implements ThingService{

    private ThingDao thingDao;
    @Autowired
    public void setThingDao(ThingDao thingDao) {
        this.thingDao = thingDao;
    }

    @Override
    public boolean isThingInserted(Thing thing) throws ThingDaoException {
        return thingDao.insert(thing);
    }

    @Override
    public List<Thing> selectAllThings() throws ThingDaoException {
        return thingDao.selectAllThings();
    }

    @Override
    public boolean isThingDeleted(int id) throws ThingDaoException {
        return thingDao.deleteThingById(id);
    }

    @Override
    public boolean isThingUpdated(int id, String thing, int price, int prior) throws ThingDaoException {
        return thingDao.updateThing(id,thing,price,prior);
    }
}
