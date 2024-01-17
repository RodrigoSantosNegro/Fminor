package com.example.aptmc;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class ScaleCalculator extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scalecalculator);

        Spinner rootListBox = findViewById(R.id.rootListBox);
        Spinner scaleTypeListBox = findViewById(R.id.scaleTypeListBox);

        ArrayAdapter<CharSequence> rootAdapter=ArrayAdapter.createFromResource(this, R.array.root_listBox, android.R.layout.simple_gallery_item);
        rootAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> scaleTypeAdapter=ArrayAdapter.createFromResource(this, R.array.scaleType_listBox, android.R.layout.simple_gallery_item);
        scaleTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);


        rootListBox.setAdapter(rootAdapter);
        scaleTypeListBox.setAdapter(scaleTypeAdapter);
    }


}
