package csv2rdf;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		CsvToRdfWrapper wrapper = new CsvToRdfWrapper("/data.csv",
										"http://example.com",
										"csv", "test",
										"http://example.com#", "ttl");
		wrapper.convert();
	}

}
