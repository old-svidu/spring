package lists;

import databases.ReportDB;
import utils.DataManager;
import tables.Report;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 19.02.17.
 */
@XmlRootElement
public class Reports implements Serializable {

    private List<Report> reports;

    @XmlElement(name = "report")
    public void setList(List<Report> list) {
        this.reports = list;
    }

    public List<Report> getList() {
        return reports;
    }

    public void selectAll(){
        this.setList(ReportDB.selectAll());
    }

    public void insert(){
        ReportDB.insert(DataManager.deserialize("lists.Reports.xml"));
    }
}
