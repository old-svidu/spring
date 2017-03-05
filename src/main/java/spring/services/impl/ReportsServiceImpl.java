package spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.common.exceptions.ReportsDaoException;
import spring.models.dao.ReportDao;
import spring.models.entity.Report;
import spring.services.interfaces.ReportsService;

import java.util.List;

/**
 * Created by root on 02.03.17.
 */
@Service
public class ReportsServiceImpl implements ReportsService{

    private ReportDao reportDao;
    @Autowired
    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public void isReportInserted(Report report) throws ReportsDaoException {
        reportDao.insert(report);
    }

    @Override
    public List<Report> selectAllReports() throws ReportsDaoException {
        return reportDao.selectAllReports();
    }

}
