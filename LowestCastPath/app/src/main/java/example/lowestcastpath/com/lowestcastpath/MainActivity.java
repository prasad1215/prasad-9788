package example.lowestcastpath.com.lowestcastpath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText rowsEditText;
    private EditText columnsEditText;
    private EditText valuesEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIViews();
    }

    private void initUIViews() {
        rowsEditText = (EditText) findViewById(R.id.rows_edit_text);
        columnsEditText = (EditText) findViewById(R.id.columns_edit_text);
        valuesEditText = (EditText) findViewById(R.id.values_edit_text);
        resultTextView = (TextView) findViewById(R.id.result_text_view);
        findViewById(R.id.find_btn).setOnClickListener(this);
        findViewById(R.id.clear_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_btn:
                onClearButtonClick();
                break;
            case R.id.find_btn:
                onFindButtonClick();
                break;
            default:
                break;
        }
    }

    private void onFindButtonClick() {
        try {
            int rows = Integer.parseInt(rowsEditText.getText().toString());
            int columns = Integer.parseInt(columnsEditText.getText().toString());
            String data = valuesEditText.getText().toString();
            String[] values = data.split("[#]");
            if (values.length != rows * columns) {
                resultTextView.setText("Insufficient data");
                return;
            }
            String[][] array = new String[rows][columns];
            int k = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    array[i][j] = values[k];
                    k++;
                }
            }
            OpResult opResult = LowestCostPath.lowestCostPath(array);
            if (opResult != null) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("Visited All Grid = " + opResult.traversedGrid + "\n");
                buffer.append("Min Cost = " + opResult.minCost + "\n");
                buffer.append("Rows traversed = " + opResult.rowsTraversed);
                resultTextView.setText(buffer);
            } else {
                resultTextView.setText("Invalid data");
            }

        } catch (NumberFormatException e) {
            resultTextView.setText("Invalid data");
        } catch (Exception e) {
            resultTextView.setText("Unknown error");
        }
    }

    private void onClearButtonClick() {
        rowsEditText.setText("");
        columnsEditText.setText("");
        valuesEditText.setText("");
    }
}
