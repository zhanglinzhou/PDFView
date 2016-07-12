package com.dashen.pdfdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import static java.lang.String.format;

public class MainActivity extends AppCompatActivity implements OnPageChangeListener{
    public static final String SAMPLE_FILE = "test.pdf";
    public static final String ABOUT_FILE = "test.pdf";
    private Integer pageNumber = 1;

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfView = ((PDFView) findViewById(R.id.pdfview));
        if (!displaying(ABOUT_FILE))
            display(ABOUT_FILE, true);

    }
    @Override
    public void onBackPressed() {
        if (ABOUT_FILE.equals(pdfName)) {
            display(SAMPLE_FILE, true);
        } else {
            super.onBackPressed();
        }
    }


    String pdfName = SAMPLE_FILE;
    @Override
    public void onPageChanged(int page, int pageCount) {

        pageNumber = page;
        setTitle(format("%s %s / %s", pdfName, page, pageCount));
    }

    private boolean displaying(String fileName) {
        return fileName.equals(pdfName);
    }

    private void display(String assetFileName, boolean jumpToFirstPage) {
        if (jumpToFirstPage) pageNumber = 1;
        setTitle(pdfName = assetFileName);

        pdfView.fromAsset(assetFileName)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .load();
    }
}
