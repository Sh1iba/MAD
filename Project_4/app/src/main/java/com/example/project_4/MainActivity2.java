package com.example.project_4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView Texttest = findViewById(R.id.textView);
        Bundle arg = getIntent().getExtras();
        if (arg != null) {
            String Name22 = arg.get("myName").toString();
            Texttest.setText("Name: "+ Name22);
    }
    }
static final String ACCESS_MESSAGE = "ACCESS_MESSAGE";
    //registerForActivityResult(). Этот метод в качестве
    //параметров принимает объекты ActivityResultContract и ActivityResultCallback и
    //возвращает объект ActivityResultLauncher, который применяется для запуска другой
    //activity.
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult
    //ActivityResultContract определяет контракт: данные какого типа будут
            //подаваться на вход и какой тип будет представлять результат
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                       // ActivityResultCallback представляет интерфейс с единственным методом
                       // onActivityResult(), который определяет обработку полученного результата.
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            TextView Name2 = findViewById(R.id.Name2);
                            if(result.getResultCode() == Activity.RESULT_OK){
                                Intent intent = result.getData();
                                String message = intent.getStringExtra(ACCESS_MESSAGE);
                                Name2.setText(message);
                            }
                            else{
                                Name2.setText("Ошибка доступа");
                            }
                        }

                    });
    public void onNextActivity(View view)
    {
        Intent intent = new Intent(this, MainActivity3.class);
        mStartForResult.launch(intent);
    }
}