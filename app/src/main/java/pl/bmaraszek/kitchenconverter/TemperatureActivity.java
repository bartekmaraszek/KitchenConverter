package pl.bmaraszek.kitchenconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class TemperatureActivity extends AppCompatActivity {

    public enum Temperature {
        C, F
    }

    private static Temperature editing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        addListeners();
    }

    private Integer celsiusToFahrenheit(Integer temperature) {
        Double result = Double.valueOf(temperature.toString()) * 9.0d / 5.0d + 32.0d;
        return result.intValue();
    }

    private Integer fahrenheitToCelsius(Integer temperature) {
        Double result = (Double.valueOf(temperature.toString()) - 32.0d) * 5.0d / 9.0d;
        return result.intValue();
    }

    private Integer stringToInt(final String number) {
        Integer result;
        try {
            result = Integer.valueOf(number.toString());
        } catch(Exception e) {
            result = 0;
        }
        return result;
    }

    private void addListeners() {
        final EditText fahrenheitTemperature = (EditText) findViewById(R.id.fahrenheitEdit);
        final EditText celsiusTemperature = (EditText) findViewById(R.id.celsiusEdit);

        fahrenheitTemperature.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editing = Temperature.F;
                celsiusTemperature.setText(fahrenheitToCelsius(stringToInt(fahrenheitTemperature.getText().toString())) + "");
                return false;
            }
        });

        celsiusTemperature.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editing = Temperature.C;
                fahrenheitTemperature.setText(celsiusToFahrenheit(stringToInt(celsiusTemperature.getText().toString())) + "");
                return false;
            }
        });

        fahrenheitTemperature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editing == Temperature.F && fahrenheitTemperature.getText().length() > 0) {
                    final Editable text = fahrenheitTemperature.getText();
                    int fTemp = stringToInt(text.toString());
                    celsiusTemperature.setText(fahrenheitToCelsius(fTemp) + "");
                }
            }
        });

        celsiusTemperature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editing == Temperature.C && celsiusTemperature.getText().length() > 0) {
                    final Editable text = celsiusTemperature.getText();
                    int cTemp = stringToInt(text.toString());
                    fahrenheitTemperature.setText(celsiusToFahrenheit(cTemp) + "");
                }
            }
        });
    }
}
