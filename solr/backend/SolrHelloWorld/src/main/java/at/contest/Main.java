package at.contest;

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
import java.io.IOException;

public class Main {

    public static void main(final String[] args) throws IOException, TikaException, SAXException {

        String urlString = "http://localhost:8983/solr/bigboxstore";
        SolrClient solr = new HttpSolrClient.Builder(urlString).build();

        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        // File file = new File("/Users/markogattringer/Documents/BSY_Teil_01_Einführung.pdf");
        File file = new File("/Users/markogattringer/Documents/sta3_skriptum.pdf");
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext pcontext = new ParseContext();

        // parsing the document using PDF parser
        PDFParser pdfparser = new PDFParser();
        pdfparser.parse(inputstream, handler, metadata, pcontext);

        // getting the content of the document
        //System.out.println("Contents of the PDF :" + handler.toString());

        try {
            String fileName = file.getName();
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", "654321");
            document.addField("title", fileName);
            document.addField("text", handler.toString());
            solr.add(document);
            solr.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

}
