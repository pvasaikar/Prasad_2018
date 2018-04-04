package com.example.prasad.prasad_2018;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TODOActivity extends AppCompatActivity {
    private ListView myTodoList;
    Set<String> toDoSet;
    private ArrayAdapter<String> mAdapter;
    String sharedpreferencename = "com.example.prasad.prasad_2018.spdemo";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        sp = getSharedPreferences(sharedpreferencename , Context.MODE_PRIVATE);
        myTodoList = (ListView) findViewById(R.id.list_todo);
        toDoSet = new HashSet<String>();
        updateUI();
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        Set i  = sp.getStringSet("todo",null);
        if(i!=null)
        {
            Iterator itr = i.iterator();
            while(itr.hasNext())
            {
                if(itr.next().toString()==task)
                {
                    //i.remove("task");
                    i.remove(task);
                    break;
                }
                break;
            }
        }
        sp.edit().putStringSet("todo",i).commit();
        updateUI();
    }

        private void updateUI() {
            Set i  = sp.getStringSet("todo",null);
            ArrayList<String> taskList = new ArrayList<>();
            if(i!=null)
            {
                Iterator itr = i.iterator();
                while(itr.hasNext())
                {
                    taskList.add(itr.next().toString());
                }
            }
            if (mAdapter == null) {
                mAdapter = new ArrayAdapter<String>(this,
                        R.layout.activity_todolistview,
                        R.id.task_title,
                        taskList);
                myTodoList.setAdapter(mAdapter);
            } else {
                mAdapter.clear();
                mAdapter.addAll(taskList);
                mAdapter.notifyDataSetChanged();
            }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_task:
                //Log.d(TAG, "Add a new task");
                //After Add Button Press on Menu
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                //Log.d(TAG, "Task to add: " + task);
                                //On Add Button
                                Set j  = sp.getStringSet("todo",null);
                                Iterator itr = j.iterator();
                                while(itr.hasNext())
                                {
                                    toDoSet.add(itr.next().toString());
                                }
                                toDoSet.add(task);
                                sp.edit().putStringSet("todo",toDoSet).commit();
                                updateUI();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
