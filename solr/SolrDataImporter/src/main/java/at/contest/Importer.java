package at.contest;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class Importer {

    // private final String solrUrl = "http://localhost:8983/solr/";
    // private final String solrCore = "bigboxstore";
    private final String solrUrl = "http://solr-contest.apps.play.gepaplexx.com/solr/";
    private final String solrCore = "contest-core";

    private final SolrClient solrClient;
    private final PDFParser pdfparser = new PDFParser();
    private final Metadata metadata = new Metadata();
    private final ParseContext pcontext = new ParseContext();
    private int docId = 0;

    public Importer() {
        solrClient = new HttpSolrClient.Builder(String.format("%s%s", solrUrl, solrCore)).build();
    }

    public void processFiles(File[] files, String currentDir) {
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getAbsolutePath());
                processFiles(Objects.requireNonNull(file.listFiles()), file.getName());
            } else if (!StringUtils.startsWith(file.getName(), ".")) {
                System.out.printf("Dir: %s, File: %s%n", currentDir, file.getAbsolutePath());
                processFile(file, currentDir);
            } else {
                System.out.printf("Ignoring file %s%n", file.getName());
            }
        }
    }

    private void processFile(File file, String currentDir) {
        BodyContentHandler handler = new BodyContentHandler(-1);

        try {
            // parsing the document using PDF parser
            pdfparser.parse(new FileInputStream(file), handler, metadata, pcontext);

            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", docId++);
            document.addField("title", file.getName());
            document.addField("subject", currentDir);
            document.addField("text", handler.toString());
            solrClient.add(document);
            solrClient.commit();
        } catch (Exception e) {
            System.err.printf("not able to process file %s, cause: %s", file.getName(), e.getMessage());
        }
    }

    public static void main(String[] args) {
        File file = new File("/Users/markogattringer/Documents/SolrData");
        new Importer().processFiles(Objects.requireNonNull(file.listFiles()), file.getName());
    }
}
