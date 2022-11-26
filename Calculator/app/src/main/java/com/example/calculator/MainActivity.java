package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView tv_result;
    TextView tv_operator;
    EditText newValue;

    Double operand1 =null;
    Double operand2 =null;
    String pendingOperator;

    private String pendingOperator_text ="PENDINGOPERATOR";
    private String result_text ="VALUE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_0 = findViewById(R.id.btn_0);
        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_2 = findViewById(R.id.btn_2);
        Button btn_3 = findViewById(R.id.btn_3);
        Button btn_4 = findViewById(R.id.btn_4);
        Button btn_5 = findViewById(R.id.btn_5);
        Button btn_6 = findViewById(R.id.btn_6);
        Button btn_7 = findViewById(R.id.btn_7);
        Button btn_8 = findViewById(R.id.btn_8);
        Button btn_9 = findViewById(R.id.btn_9);
        Button btn_dot = findViewById(R.id.btn_dot);
        Button btn_ac = findViewById(R.id.btn_ac);
        Button btn_del = findViewById(R.id.btn_del);
        Button btn_percent = findViewById(R.id.btn_percent);
        Button btn_equals = findViewById(R.id.btn_equals);
        Button btn_plus = findViewById(R.id.btn_plus);
        Button btn_minus = findViewById(R.id.btn_minus);
        Button btn_multi = findViewById(R.id.btn_multi);
        Button btn_div = findViewById(R.id.btn_div);
        Button btn_sin = findViewById(R.id.btn_sin);
        Button btn_cos = findViewById(R.id.btn_cos);
        Button btn_sqrt = findViewById(R.id.btn_sqrt);
        Button btn_power = findViewById(R.id.btn_power);
        Button btn_factorial = findViewById(R.id.btn_factorial);




        tv_result = findViewById(R.id.tv_result);
        tv_operator = findViewById(R.id.tv_operator);
        newValue = findViewById(R.id.newValue);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                newValue.append(button.getText().toString());
            }
        };
        btn_0.setOnClickListener(numberListener);
        btn_1.setOnClickListener(numberListener);
        btn_2.setOnClickListener(numberListener);
        btn_3.setOnClickListener(numberListener);
        btn_4.setOnClickListener(numberListener);
        btn_5.setOnClickListener(numberListener);
        btn_6.setOnClickListener(numberListener);
        btn_7.setOnClickListener(numberListener);
        btn_8.setOnClickListener(numberListener);
        btn_9.setOnClickListener(numberListener);
        btn_dot.setOnClickListener(numberListener);

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String op = button.getText().toString();
                String value = newValue.getText().toString();
                try {
                    if (!(op.equals("sin") ||op.equals("cos") ||op.equals("!") ||op.equals("√"))){
                        Double doubleValue = Double.valueOf(value);
                        performOperation(doubleValue,op);
                    }

                }
                catch (NumberFormatException e){
                    newValue.setText("");
                }
                pendingOperator = op;
                tv_operator.setText(pendingOperator);
            }
        };
        btn_percent.setOnClickListener(operatorListener);
        btn_equals.setOnClickListener(operatorListener);
        btn_plus.setOnClickListener(operatorListener);
        btn_minus.setOnClickListener(operatorListener);
        btn_multi.setOnClickListener(operatorListener);
        btn_div.setOnClickListener(operatorListener);
        if (btn_power != null) btn_power.setOnClickListener(operatorListener);
        if (btn_sin != null) btn_sin.setOnClickListener(operatorListener);
        if (btn_cos != null) btn_cos.setOnClickListener(operatorListener);
        if (btn_sqrt != null) btn_sqrt.setOnClickListener(operatorListener);
        if (btn_factorial != null) btn_factorial.setOnClickListener(operatorListener);



        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newValue.getText().toString().length() >0){
                    newValue.setText(newValue.getText().toString().substring(0,newValue.getText().toString().length()-1));
                }
            }
        });
        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_result.setText("");
                tv_operator.setText("");
                newValue.setText("");
                operand1 =null;
                operand2=null;
                pendingOperator = null;
            }
        });

    }
    private  void performOperation(Double value, String operator){
        if (operand1 == null){
            operand1 = value;
            if (pendingOperator !=null){
                switch (pendingOperator){
                    case "sin":
                        operand1 = Math.sin(operand1);
                        break;
                    case "cos":
                        operand1 =Math.cos(operand1);
                        break;
                    case "√":
                        operand1 =Math.sqrt(operand1);
                        break;
                    case "!":
                        if (value == 0.0 || value == 1.0){
                            operand1 = 1.0;
                        }
                        else{
                            for (double i = value-1;i > 0;i--){
                                operand1 *=i;
                            }
                        }
                        break;
                }
            }
        }
        else {
            operand2=value;
            if (pendingOperator.equals("=")){
                pendingOperator = operator;
            }
            switch (pendingOperator){
                case "=":
                    operand1 = operand2;
                    break;
                case "+":
                    operand1 +=operand2;
                    break;
                case "-":
                    operand1 -=operand2;
                    break;
                case "*":
                    operand1 *=operand2;
                    break;
                case "/":
                    if (operand2 ==0){
                        operand1 = null;
                    }
                    else {
                        operand1/=operand2;
                    }
                    break;
                case "%":
                    operand1 *= operand2/100;
                    break;
                case "^":
                    operand1 = Math.pow(operand1,operand2);
                    break;
            }
        }
        if (operand1 ==null){
            tv_result.setText("HATA");
            tv_operator.setText("");
            newValue.setText("");
            operand1 =null;
            operand2=null;
            pendingOperator = null;
        }
        else {
            tv_result.setText(operand1.toString());
            tv_operator.setText(operator);
            newValue.setText("");
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(pendingOperator_text,pendingOperator);
        if (operand1 != null){
            outState.putDouble(result_text,operand1);
            super.onSaveInstanceState(outState);

        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operand1 = savedInstanceState.getDouble(result_text,Double.POSITIVE_INFINITY);
        if (operand1 == Double.POSITIVE_INFINITY){
            operand1 =null;
        }
        else {
            tv_result.setText(String.valueOf(operand1));
        }
        pendingOperator = savedInstanceState.getString(pendingOperator_text);
        tv_operator.setText(pendingOperator);
    }
}
// karekök sembolü √