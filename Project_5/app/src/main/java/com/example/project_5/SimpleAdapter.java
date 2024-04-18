package com.example.project_5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//Прежде чем наполнить RecycleView данными, необходимо создать отдельный
//класс для обработки. Как и в случае с ListView, для вывода сложных объектов
//в RecyclerView необходимо определить свой адаптер.
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
    private List<Data> data;

    public SimpleAdapter(List<Data> data) {
        this.data = data;
    }

//onCreateViewHolder: возвращает объект ViewHolder, который будет
//хранить данные по одному объекту.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
// onBindViewHolder: выполняет привязку объекта ViewHolder к объекту
//элемента по определенной позиции.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data item = data.get(position);
        //задаем изображение и текст для элемента списка ViewHolder
        holder.imageView.setImageResource(item.getImage());
        holder.textView.setText(item.getText());
    }
// getItemCount: возвращает количество объектов в списке
    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
//в этом методе мы присваиваем нашим объектам представление разметки элемента в списке
        ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.image_view);
            textView = view.findViewById(R.id.text_view);
        }
    }
}

