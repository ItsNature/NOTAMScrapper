package hr.lyra.scrapper;

import hr.lyra.telegram.TelegramBotManager;
import hr.lyra.utils.FileUtils;
import hr.lyra.utils.Messages;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

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

    public NotamScrapper() {
        this.client = HttpClient.newBuilder().build();
    }

    public void fetchNotams() {
        var builder = HttpRequest.newBuilder()
            .uri(NOTAM_URI).GET().build();

        this.client
            .sendAsync(builder, HttpResponse.BodyHandlers.ofFile(FileUtils.PDF))
            .thenAccept(httpResponse -> {
                var bot = TelegramBotManager.getInstance();

                var statusCode = httpResponse.statusCode();
                if(statusCode != 200) {
                    String message = Messages.toString(Messages.NOTAM_FETCH_FAILED)
                        .replace("{code}", String.valueOf(statusCode));

                    bot.sendMessage(message);
                    return;
                }

                this.pdfToTxt(httpResponse.body());
            });
    }

    private void pdfToTxt(Path path) {
        var file = path.toFile();

        PDFParser parser;
        try {
            parser = new PDFParser(new RandomAccessFile(file, "r"));
            parser.parse();

            var cosDoc = parser.getDocument();
            var pdfStripper = new PDFTextStripper();
            var pdDoc = new PDDocument(cosDoc);

            var text = pdfStripper.getText(pdDoc);
            var writer = new PrintWriter(FileUtils.TXT_NAME);

            writer.print(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
