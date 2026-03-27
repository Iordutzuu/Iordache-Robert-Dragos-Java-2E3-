package command;

import repository.Catalog;
import exception.ResourceException;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    private Catalog catalog;

    public ReportCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() throws ResourceException {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
            cfg.setDefaultEncoding("UTF-8");

            Template template = cfg.getTemplate("report.ftl");

            Map<String, Object> templateData = new HashMap<>();
            templateData.put("resources", catalog.getResources());

            File htmlFile = new File("catalog_report.html");
            Writer fileWriter = new FileWriter(htmlFile);
            template.process(templateData, fileWriter);
            fileWriter.close();

            Desktop.getDesktop().browse(htmlFile.toURI());

        } catch (Exception e) {
            throw new ResourceException("Eroare la generarea raportului HTML", e);
        }
    }
}