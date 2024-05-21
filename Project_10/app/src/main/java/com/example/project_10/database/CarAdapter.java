package com.example.project_10.database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_10.R;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private final List<Car> cars;
    public CarAdapter( List<Car> cars) {
        this.cars = cars;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView idTextView;
        private final TextView markTextView;
        private final TextView yearTextView;
        private final TextView colorTextView;
        private final TextView priceTextView;
        public ViewHolder(View view) {
            super(view);
            idTextView = view.findViewById(R.id.car_id);
            markTextView = view.findViewById(R.id.car_mark);
            yearTextView = view.findViewById(R.id.car_year);
            colorTextView = view.findViewById(R.id.car_color);
            priceTextView = view.findViewById(R.id.car_price);

        }
        public void bind(Car car) {
            idTextView.setText(String.valueOf((car.getId())));
            markTextView.setText(car.getMark());
            yearTextView.setText(car.getYearOfRelease());
            colorTextView.setText(car.getColor());
            priceTextView.setText(car.getPrice());

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.bind(cars.get(position));
    }
    @Override
    public int getItemCount() {
        return cars.size();
    }
}
