package com.example.needcalendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.needcalendar.DBHelper;
import com.example.needcalendar.R;
import com.example.needcalendar.TodoItem;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {


    private ArrayList<TodoItem> mTodoItems;
    private Context mContext;
    private DBHelper mDBHelper;

    public CustomAdapter(ArrayList<TodoItem> mTodoItems, Context mContext) {
        this.mTodoItems = mTodoItems;
        this.mContext = mContext;
        mDBHelper = new DBHelper(mContext);
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily,parent, false);
        return new ViewHolder(holder);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        holder.tv_title.setText(mTodoItems.get(position).getTitle());
        holder.tv_content.setText(mTodoItems.get(position).getContent());
        holder.tv_writeDate.setText(mTodoItems.get(position).getWriteDate());

    }
    @Override
    public int getItemCount() {
        return mTodoItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private EditText tv_title;
        private EditText tv_content;
        private EditText tv_writeDate;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_board_title);
            tv_content = itemView.findViewById(R.id.item_board_place);
            tv_writeDate = itemView.findViewById(R.id.item_board_memo);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int curPos = getAdapterPosition();// 현재 리스트 아이템 위치
                    TodoItem todoItem = mTodoItems.get(curPos);

                    String[] strChoiceItems = {"수정하기", "삭제하기"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("원하는 작업을 선택 해주세요");

                }

            });

        }

    }
    //액티비테에서 호출되는 함수이며, 현재 어댑터에서 새로운 게시글 아이템을 전달받아 추가하는 목적이다.//1:58:50
    public void addItem(TodoItem _item){
        mTodoItems.add(0, _item);
        notifyItemInserted(0);

    }
}
