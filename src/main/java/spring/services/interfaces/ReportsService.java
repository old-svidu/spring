package spring.services.interfaces;

import spring.common.exceptions.ReportsDaoException;
import spring.models.entity.Report;

import java.util.List;

/**
 * Created by root on 02.03.17.
 */
public interface ReportsService {
    void isReportInserted(Report report) throws ReportsDaoException;
    List<Report> selectAllReports() throws ReportsDaoException;
}
