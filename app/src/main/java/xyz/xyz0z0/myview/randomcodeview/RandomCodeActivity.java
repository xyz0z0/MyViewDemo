package xyz.xyz0z0.myview.randomcodeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import xyz.xyz0z0.myview.R;

public class RandomCodeActivity extends AppCompatActivity {

    private Button button;
    private RandomCodeView codeView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_code);
        initView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editText.getText().toString();
                Toast.makeText(RandomCodeActivity.this, "check: " + codeView.checkRes(code), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        codeView = findViewById(R.id.randomCodeView);
        codeView.setOnClickRefresh(true);
    }


}
