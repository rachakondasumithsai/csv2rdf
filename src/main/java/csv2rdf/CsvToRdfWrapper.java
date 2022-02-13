package csv2rdf;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.jena.propertytable.lang.CSV2RDF;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class CsvToRdfWrapper {
	private static final Integer BUFFER_SIZE = 8192;
	private String resourcePath;
	private String base;
	private String readLang;
	private String prefix;
	private String uri;
	private String writeLang;

	public CsvToRdfWrapper(String resourcePath, String base, String readLang, String prefix, String uri, String writeLang) {
		this.resourcePath = resourcePath;
		this.base = base;
		this.readLang = readLang;
		this.prefix = prefix;
		this.uri = uri;
		this.writeLang = writeLang;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getReadLang() {
		return readLang;
	}

	public void setReadLang(String readLang) {
		this.readLang = readLang;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getWriteLang() {
		return writeLang;
	}

	public void setWriteLang(String writeLang) {
		this.writeLang = writeLang;
	}

	public void convert() throws IOException {
		CSV2RDF.init();
	    //load through manager:
		//Model m = RDFDataMgr.loadModel("test.csv") ;
	    //classic way to load:
	    Model m = ModelFactory.createDefaultModel();
	    try (
	    		InputStream in = Main.class.getResourceAsStream(resourcePath);
	    		BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8), BUFFER_SIZE);
	    		OutputStream out = new FileOutputStream("target/output-model.ttl");
	    		) {
	        m.read(br, base, readLang);
	        m.setNsPrefix(prefix, uri);
		    m.write(out, writeLang);
	    }
	}
}
