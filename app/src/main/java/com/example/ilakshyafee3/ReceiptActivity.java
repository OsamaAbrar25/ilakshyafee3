package com.example.ilakshyafee3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReceiptActivity extends AppCompatActivity {
    Bitmap b;
    String path;
    private String signature_pdf_ = "FeeReceiptPdf", signature_img_ = "FeeReceiptImage";
    Button button;
    NestedScrollView z;
    ArrayList<FeeInfo> arrayList = new ArrayList<>();
    TextView textView_nameR, textView_nameR2, textView_scodeR, textView_scodeR2, textView_feeD, textView_feeDetails, textView_netR, textView_netR2, textView_statusR, textView_statusR2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        button = findViewById(R.id.button_generate);
        textView_nameR = findViewById(R.id.textView_nameR);
        textView_nameR2 = findViewById(R.id.textView_nameR2);
        textView_scodeR = findViewById(R.id.textView_scodeR);
        textView_scodeR2 = findViewById(R.id.textView_scodeR2);
        textView_feeD = findViewById(R.id.textView_feeD);
        textView_feeDetails = findViewById(R.id.textView_feeDetails);
        textView_netR = findViewById(R.id.textView_netR);
        textView_netR2 = findViewById(R.id.textView_netR2);
        textView_statusR = findViewById(R.id.textView_statusR);
        textView_statusR2 = findViewById(R.id.textView_statusR2);
        //z = findViewById(R.id.scrollView);
        arrayList = getIntent().getParcelableArrayListExtra("arrayList");
        if (arrayList != null) {
            textView_nameR2.setText(arrayList.get(0).getStudentCode());
            textView_scodeR2.setText(arrayList.get(0).getSchoolCode());
            int i;
            String details = "";
            int net_payable = 0;
            for (i=0;i<arrayList.size();i++) {
                details = details + arrayList.get(i).getInstallmentName() + "-" + arrayList.get(i).getDueAmount() + "\n";
                net_payable+=arrayList.get(i).getDueAmount();
            }
            textView_feeDetails.setText(details);
            textView_netR2.setText("Rs. "+Integer.toString(net_payable));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                takeScreenShot();
            }
        });


    }

    private void takeScreenShot() {

        //File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Signature/");

        /*if (!folder.exists()) {
            boolean success = folder.mkdir();
        }*/

        //path = folder.getAbsolutePath();
        path = Environment.getExternalStorageDirectory()+"/" + signature_pdf_ + System.currentTimeMillis() + ".pdf";// path where pdf will be stored

        View u = findViewById(R.id.scrollView);
        NestedScrollView z = findViewById(R.id.scrollView); // parent view
        float totalHeight = z.getChildAt(0).getHeight();// parent view height
        float totalWidth = z.getChildAt(0).getWidth();// parent view width

        //Save bitmap to  below path
        String extr = Environment.getExternalStorageDirectory()+"";// + "/Signature/";
        File file = new File(extr);
        if (!file.exists())
            file.mkdir();
        String fileName = signature_img_ + ".jpg";
        File myPath = new File(extr, fileName);
        String imagesUri = myPath.getPath();
        FileOutputStream fos = null;
        b = getBitmapFromView(u, totalHeight, totalWidth);

        try {
            fos = new FileOutputStream(myPath);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        createPdf();// create pdf after creating bitmap and saving

    }

    private void createPdf() {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(b.getWidth(), b.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();


        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawPaint(paint);


        Bitmap bitmap = Bitmap.createScaledBitmap(b, b.getWidth(), b.getHeight(), true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);
        File filePath = new File(path);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Fee Receipt pdf saved in File Manager", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
    }

    private static Bitmap getBitmapFromView(View v, float totalHeight, float totalWidth) {
            Bitmap bitmap = Bitmap.createBitmap((int)totalWidth, (int)totalHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = v.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
            v.draw(canvas);
            return bitmap;
    }
}