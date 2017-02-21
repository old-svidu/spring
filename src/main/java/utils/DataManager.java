package utils;

import databases.UserDB;
import lists.Users;
import tables.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Created by root on 18.02.17.
 */
public class DataManager {

    public static void serialize(Object obj) {
        try {
            File file = new File(obj.getClass().getCanonicalName()+".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(obj, file);

        } catch (JAXBException e) {
            Log.logger.error(e);
        }
    }

    public static Object deserialize(String path){
        try {
            File file = new File(path);

            Class cl = getClassFromXml(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(cl);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Object obj = jaxbUnmarshaller.unmarshal(file);

            return obj;
        } catch (JAXBException e) {
            Log.logger.error(e);
        }
        return null;
    }

    public static Class getClassFromXml(String path){
        String className = path.substring(0,path.length()-4);
        try {
            Class cl = Class.forName(className);
            return cl;
        } catch (ClassNotFoundException e) {
            Log.logger.error(e);
        }
        return null;
    }


}




