package com.example.aptmc;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;



public class ScaleCalculator extends AppCompatActivity{

    private Button buttonScaleCalculator;

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

        buttonScaleCalculator = findViewById(R.id.buttonScaleCalculator);

        buttonScaleCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedRoot = rootListBox.getSelectedItem().toString();
                String selectedScaleType = scaleTypeListBox.getSelectedItem().toString();
                if(selectedScaleType.equals("Select scale type") || selectedRoot.equals("Select root")){
                    Toast.makeText(ScaleCalculator.this, "You must select the Root and a Scale", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ScaleCalculator.this, "Calculating scale... Root: " + selectedRoot + ", Scale Type: " + selectedScaleType, Toast.LENGTH_SHORT).show();
                    // Send information to ScaleName
                    Intent intent = new Intent(ScaleCalculator.this, ScaleName.class);
                    intent.putExtra("ROOT_SELECTION", selectedRoot);
                    intent.putExtra("SCALE_TYPE_SELECTION", selectedScaleType);
                    startActivity(intent);
                }
            }
        });

    }
}
