import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.List;
public class PDFSplitter {
	public static void Split(String inputFilePaths, String outputDirectory)
	{
		String[] files = inputFilePaths.split(";");
        File outputDir = new File(outputDirectory);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        for (String filePath : files) {
            if (filePath.trim().isEmpty()) continue;
            try {
                PDDocument document = Loader.loadPDF(new File(filePath.trim()));
                Splitter splitter = new Splitter();
                List<PDDocument> pages = splitter.split(document);

                String fileName = new File(filePath).getName().replace(".pdf", "");
                File newFileFolder = new File(outputDirectory + "/" + fileName);
                if (!newFileFolder.exists())
                {
                	newFileFolder.mkdirs();
                }
                for (int i = 0; i < pages.size(); i++) {
                    PDDocument page = pages.get(i);
                    String outputFilePath = newFileFolder.getAbsolutePath() + "/" + fileName + "_page_" + (i + 1) + ".pdf";
                    page.save(outputFilePath);
                    page.close();
                }

                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
