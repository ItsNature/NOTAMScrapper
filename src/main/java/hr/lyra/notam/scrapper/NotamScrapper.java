package hr.lyra.notam.scrapper;

import hr.lyra.Manager;
import hr.lyra.Scrapper;
import hr.lyra.utils.FileUtils;
import hr.lyra.utils.Messages;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class NotamScrapper {

    private static final URI NOTAM_URI = URI.create("https://amc.crocontrol.hr/amc/maps/getNOTAMsPDF");

    private final HttpClient client;
    private final NotamNormalizer notamNormalizer;

    public NotamScrapper() {
        this.client = HttpClient.newBuilder().build();
        this.notamNormalizer = new NotamNormalizer();
    }

    public void createDataDirectory() {
        new File(FileUtils.DIRECTORY).mkdirs();
    }

    public void fetchNotams() {
        Scrapper.log("Fetching NOTAMs....");

        var builder = HttpRequest.newBuilder()
            .uri(NOTAM_URI).GET().build();

        this.client
            .sendAsync(builder, HttpResponse.BodyHandlers.ofFile(FileUtils.PDF))
            .thenAccept(httpResponse -> {
                var bot = Manager.getInstance().getTelegramBotManager();
                var statusCode = httpResponse.statusCode();

                if(statusCode != 200) {
                    var message = Messages.toString(Messages.NOTAM_FETCH_FAILED)
                        .replace("{code}", String.valueOf(statusCode));

                    bot.sendMessage(message);
                    Scrapper.log("Failed to fetch NOTAMs");
                    return;
                }

                this.pdfToTxt(httpResponse.body());
                this.notamNormalizer.normalize();
            });
    }

    private void pdfToTxt(Path path) {
        var file = path.toFile();

        try {
            var document = PDDocument.load(file);
            var text = new PDFTextStripper().getText(document);
            var writer = new PrintWriter(FileUtils.TXT_NAME);

            writer.print(text);
            writer.close();

            document.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
