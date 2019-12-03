package com.psulccomsci.civilservicereviewer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.psulccomsci.civilservicereviewer.ui.books.Books;
import com.psulccomsci.civilservicereviewer.ui.pdfreader.PDFFragment;

public class reader extends AppCompatActivity {
String title, temp1, temp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        title = Books.title();
        title = PDFFragment.title();

        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset(title+".pdf")
                .enableSwipe(true)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)
                .spacing(0)
                .swipeHorizontal(false)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();

    }



}
