/*
 * Created on Jan 12, 2005
 *
 */
package ServidorPersistenteOracle.Oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dominio.projectsManagement.ISummaryEURReportLine;
import Dominio.projectsManagement.SummaryEURReportLine;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistenteOracle.IPersistentReport;
import Util.projectsManagement.ReportType;

/**
 * @author Susana Fernandes
 * 
 */
public class PersistentSummaryEURReport extends PersistentReport implements IPersistentReport {

    public List getCompleteReport(ReportType reportType, Integer projectCode) throws ExcepcaoPersistencia {
        List result = new ArrayList();

        try {
            PersistentSuportOracle p = PersistentSuportOracle.getInstance();
            p.startTransaction();
            String tableOrView = getTableOrViewName(p, reportType);

            String query = new String("select \"RECEITA\", \"DESPESA\", \"IVA\", \"AD_POR_JUST\",  \"TOTAL\" from " + tableOrView
                    + " where PROJECTCODE='" + projectCode + "'");
            PreparedStatement stmt = p.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ISummaryEURReportLine report = new SummaryEURReportLine();
                report.setProjectCode(projectCode);
                report.setRevenue(new Double(rs.getDouble("RECEITA")));
                report.setExpense(new Double(rs.getDouble("DESPESA")));
                report.setTax(new Double(rs.getDouble("IVA")));
                report.setAdiantamentosPorJustificar(new Double(rs.getDouble("AD_POR_JUST")));
                report.setTotal(new Double(rs.getDouble("TOTAL")));
                result.add(report);
            }

            rs.close();
            p.commitTransaction();
        } catch (SQLException e) {
            throw new ExcepcaoPersistencia();
        }

        return result;
    }

}
