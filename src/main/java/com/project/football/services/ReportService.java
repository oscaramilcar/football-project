package com.project.football.services;

import com.project.football.model.Team;
import com.project.football.repository.TeamRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
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
    private TeamRepository teamRepository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Report";
        Map<String, Object> parameters = new HashMap<>();
        File file = ResourceUtils.getFile("classpath:clubs.jrxml");
        List<Team> teams = teamRepository.findAll();
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(teams);
        parameters.put("createdBy ", "Best Team");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("xlsx")) {
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path + "\\clubs.xlsx"));
            exporter.exportReport();
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\clubs.pdf");
        }
        return "Report generated in path : " + path;
    }
}
