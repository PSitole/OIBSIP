package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.R;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;
    StringBuilder expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.result_text_view);
        expression = new StringBuilder();
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        expression.append(buttonText);
        resultTextView.setText(expression.toString());
    }

    public void onOperatorClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        expression.append(" ").append(buttonText).append(" ");
        resultTextView.setText(expression.toString());
    }

    public void onClearClick(View view) {
        expression.setLength(0);
        resultTextView.setText("");
    }

    @SuppressLint("SetTextI18n")
    public void onEqualsClick(View view) {
        String[] parts = expression.toString().split(" ");
        if (parts.length == 3) {
            double operand1 = Double.parseDouble(parts[0]);
            double operand2 = Double.parseDouble(parts[2]);
            String operator = parts[1];

            double result;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        resultTextView.setText("Cannot divide by zero");
                        return;
                    }
                    break;
                default:
                    resultTextView.setText("Invalid expression");
                    return;
            }

            resultTextView.setText(String.valueOf(result));
            expression.setLength(0);
        } else {
            resultTextView.setText("Invalid expression");
        }
    }
}
