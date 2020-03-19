package net.awrp.mvvmdemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.awrp.mvvmdemo.R;
import net.awrp.mvvmdemo.model.Model;

import java.util.Observable;
import java.util.Observer;

public class AndroidView extends AppCompatActivity {

    private TextView outputView;
    private EditText inputText;

    private LowerCasePresenter lowerCaseViewModel = new LowerCasePresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        observeViewModel();

        outputView = findViewById(R.id.outputView);
        outputView.setText(lowerCaseViewModel.getPresentableData());

        inputText = findViewById(R.id.inputText);
        inputText.setText(lowerCaseViewModel.getPresentableData());
    }

    private void observeViewModel() {
        lowerCaseViewModel.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (o instanceof Model) {
                    String data = ((Model) o).getData();
                    outputView =findViewById(R.id.outputView);
                    outputView.setText(data);
                }
            }
        });
    }

    public void enterInput(View view) {
        inputText =findViewById(R.id.inputText);

        String input = inputText.getText().toString();
        lowerCaseViewModel.setData(input);
    }

}
