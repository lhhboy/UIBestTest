package com.lhh.uibestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private List<Msg> msgList=new ArrayList<>();
private EditText inputText;
private Button send;
private RecyclerView msgRecycleView;
private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecycleView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        msgRecycleView.setLayoutManager(layoutManager);
        adapter=new MsgAdapter(msgList);
        msgRecycleView.setAdapter(adapter);
        initMsgs();
    }

    private void initMsgs() {
        Msg msg1 = new Msg("hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("hello ,GOOD,guy",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("你做什么呢？",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
//监听方法
    public void onViewClick(View view){
        String content = inputText.getText().toString();
        if(!"".equals(content)){
            Msg msg = new Msg(content,Msg.TYPE_SENT);
            msgList.add(msg);
            adapter.notifyItemInserted(msgList.size() - 1);
          //  当有新消息时刷新ListView中的显示
            msgRecycleView.scrollToPosition(msgList.size()-1);
            //将ListView定位到最后一行
            inputText.setText("");
            //清空输入框
        }
    }
}
