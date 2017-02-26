package models.lists;

import models.dao.ReportDao;
import common.utils.DataManager;
import models.pojo.Report;

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
        this.setList(ReportDao.selectAllReports());
    }

    public void insert(){
        ReportDao.insert(DataManager.deserialize("models.lists.Reports.xml"));
    }
}
