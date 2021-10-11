package com.srssoftwsarelogics.task1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends AppCompatActivity {
    private PackageManager manager;
    private List<Item> apps;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ShowApps();
        loadListview();
        addClickListner();

    }
    private void ShowApps(){
        manager = getPackageManager();
        apps = new ArrayList<>();
        Intent i = new Intent(Intent.ACTION_MAIN,null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i,0);
        for (ResolveInfo ri :availableActivities){
            Item app = new Item();
            app.label = ri.activityInfo.packageName;
            app.name = ri.loadLabel(manager);
            app.icon = ri.loadIcon(manager);
            apps.add(app);
        }
    }
    private void loadListview (){
        list = (ListView) findViewById(R.id.list);
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,R.layout.item,apps){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.item,null);
                }
                ImageView appicon = (ImageView) convertView.findViewById(R.id.iv_icon);
                appicon.setImageDrawable(apps.get(position).icon);
                TextView appaName = (TextView)  convertView.findViewById(R.id.tv_name);
                appaName.setText(apps.get(position).name);
                return convertView;

            }
        };
        list.setAdapter(adapter);
    }
    private void addClickListner(){
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = manager.getLaunchIntentForPackage(apps.get(i).label.toString());
               startActivity(intent);
           }
       });
    }
}