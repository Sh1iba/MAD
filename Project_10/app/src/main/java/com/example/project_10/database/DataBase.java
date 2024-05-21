package com.example.project_10.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_10.R;
import com.example.project_10.database.Car;

import java.util.List;

public class DataBase extends AppCompatActivity {
    EditText idInput,markInput,yearInput,colorInput,priceInput;

    RecyclerView carsList;
    DatabaseHelper dbHelper;
    List<Car> car_list;
    CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        idInput = findViewById(R.id.id_input);
        markInput = findViewById(R.id.mark_input);
        yearInput = findViewById(R.id.year_input);
        colorInput = findViewById(R.id.color_input);
        priceInput = findViewById(R.id.price_input);

        RecyclerView carsList = findViewById(R.id.recycler_view);

        dbHelper = new DatabaseHelper(this);
        car_list = dbHelper.getAllCars();
        adapter = new CarAdapter(car_list);

        carsList.setLayoutManager(new LinearLayoutManager(this));
        carsList.setAdapter(adapter);
    }
    public void Save(View view) {
        String str_id = idInput.getText().toString();
        String mark = markInput.getText().toString();
        String year = yearInput.getText().toString();
        String color = colorInput.getText().toString();
        String price = priceInput.getText().toString();
        int id = 0;
        if (!str_id.isEmpty()) {
            try {
                id = Integer.parseInt(str_id);
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Id должен быть числом", Toast.LENGTH_SHORT).show();
            }
        }

        if (mark.isEmpty() || year.isEmpty() || price.isEmpty() || color.isEmpty() || str_id.isEmpty() )
        {
            Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
        }
        else{
            Car car = new Car(id,mark,year,color,price);
            if (dbHelper.addCar(car)) {
                car_list.add(car);
                adapter.notifyItemInserted(car_list.size() - 1);
                Toast.makeText(this, "Contact saved successfully!", Toast.LENGTH_SHORT).show();

                carsList.setLayoutManager(new LinearLayoutManager(this)); //Устанавливает макет отображения - гориозонтально
                carsList.setAdapter(adapter); // Устанавливаем адаптер для RecyclerView

            }
            else {
                Toast.makeText(this, "Failed to save contact",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Find(View view){
        //ищем контакт по номеру телефона
         String mark = markInput.getText().toString();
         Car foundContact = dbHelper.findCar(mark);
         if (foundContact != null) {
             markInput.setText(foundContact.getMark());
             Toast.makeText(this, "Contact found: " +
                     foundContact.getMark(), Toast.LENGTH_SHORT).show();
            }
         else {
             Toast.makeText(this, "Contact not found",
                     Toast.LENGTH_SHORT).show();
         }
        }

    public void Delete(View view){

        String mark = markInput.getText().toString();
        if (dbHelper.deleteCar(mark)) {
            int position = -1;
            for (int i = 0; i < car_list.size(); i++) {
                if (car_list.get(i).getMark().equals(mark)) {
                    position = i;
                    car_list.remove(i);
                    break;
                }
            }
            if (position != -1) {
                adapter.notifyItemRemoved(position);
                Toast.makeText(this, "Contact deleted successfully!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Contact not found",
                        Toast.LENGTH_SHORT).show();
            }
            }
        else {
                    Toast.makeText(this, "Failed to delete contact",
                            Toast.LENGTH_SHORT).show();
        }
    }


    // Метод для обновления списка контактов после изменения в базе данных
    private void refreshContactsList(DatabaseHelper dbHelper,
                                     List<Car> cars, CarAdapter adapter, RecyclerView
                                             contactsList) {
        cars = dbHelper.getAllCars(); // Загружаем обновленный список
        adapter = new CarAdapter(cars);
        contactsList.setAdapter(adapter);
    }

}


