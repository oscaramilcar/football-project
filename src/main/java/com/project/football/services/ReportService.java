package com.project.football.services;

import com.project.football.item.TeamClassificationItem;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private StatisticsReportService statisticsReportService;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Report";
        Map<String, Object> parameters = new HashMap<>();
        File file = ResourceUtils.getFile("classpath:stats.jrxml");
        List<TeamClassificationItem> items = statisticsReportService.getGoalsByMatchByLeague(1);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(items);
        parameters.put("createdBy ", "Best Team");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("xlsx")) {
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path + "\\stats.xlsx"));
            exporter.exportReport();
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\stats.pdf");
        }
        return "Report generated in path : " + path;
    }

}
