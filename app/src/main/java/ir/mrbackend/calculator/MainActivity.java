package ir.mrbackend.calculator;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private double firstOperand = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);


        int[] numberButtons = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        for (int id : numberButtons) {
            Button btn = findViewById(id);
            btn.setOnClickListener(v -> numberClicked(btn.getText().toString()));
        }


        findViewById(R.id.buttonAdd).setOnClickListener(v -> operatorClicked("+"));
        findViewById(R.id.buttonSubtract).setOnClickListener(v -> operatorClicked("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(v -> operatorClicked("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(v -> operatorClicked("/"));


        findViewById(R.id.buttonEquals).setOnClickListener(v -> calculate());


        findViewById(R.id.buttonClear).setOnClickListener(v -> clear());
    }

    private void numberClicked(String number) {
        if (isOperatorClicked) {
            currentInput = "";
            isOperatorClicked = false;
        }
        currentInput += number;
        display.setText(currentInput);
    }

    private void operatorClicked(String op) {
        try {
            firstOperand = Double.parseDouble(currentInput);
        } catch (NumberFormatException e) {
            firstOperand = 0;
        }
        operator = op;
        isOperatorClicked = true;
        display.setText(currentInput + " " + operator);
    }

    private void calculate() {
        try {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+": result = firstOperand + secondOperand; break;
                case "-": result = firstOperand - secondOperand; break;
                case "*": result = firstOperand * secondOperand; break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("خطا");
                        return;
                    }
                    break;
                default: return;
            }

            if (result == (int) result) {
                currentInput = String.valueOf((int) result);
            } else {
                currentInput = String.valueOf(result);
            }

            display.setText(currentInput);
        } catch (Exception e) {
            display.setText("خطا");
        }
    }

    private void clear() {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        display.setText("0");
    }
}
