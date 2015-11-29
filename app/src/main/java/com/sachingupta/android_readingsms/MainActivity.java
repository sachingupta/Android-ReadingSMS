package com.sachingupta.android_readingsms;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    //  GUI Widget
    Button btnSent, btnInbox, btnDraft;
    TextView lblMsg, lblNo;
    ListView lvMsg;
    // Cursor Adapter
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Init GUI Widget
        btnInbox = (Button) findViewById(R.id.btnInbox);
        btnInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClickHandler(v);
            }
        });

        btnSent = (Button)findViewById(R.id.btnSentBox);
        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClickHandler(v);
            }
        });

        btnDraft = (Button)findViewById(R.id.btnDraft);
        btnDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClickHandler(v);
            }
        });

        lvMsg = (ListView) findViewById(R.id.messageList);
    }

    public void btnClickHandler(View v){
        Cursor c = null;
        if (v == btnInbox) {

            // Create Inbox box URI
            Uri inboxURI = Uri.parse("content://sms/inbox");
            // List required columns
            String[] reqCols = new String[] { "_id", "address", "body" };
            // Get Content Resolver object, which will deal with Content Provider
            ContentResolver cr = getContentResolver();
            // Fetch Inbox SMS Message from Built-in Content Provider
            c = cr.query(inboxURI, reqCols, null, null, null);
        }

        if(v==btnSent)
        {

            // Create Sent box URI
            Uri sentURI = Uri.parse("content://sms/sent");

            // List required columns
            String[] reqCols = new String[] { "_id", "address", "body" };

            // Get Content Resolver object, which will deal with Content Provider
            ContentResolver cr = getContentResolver();

            // Fetch Sent SMS Message from Built-in Content Provider
            c = cr.query(sentURI, reqCols, null, null, null);
        }

        if(v==btnDraft)
        {
            // Create Draft box URI
            Uri draftURI = Uri.parse("content://sms/draft");

            // List required columns
            String[] reqCols = new String[] { "_id", "address", "body" };

            // Get Content Resolver object, which will deal with Content Provider
            ContentResolver cr = getContentResolver();

            // Fetch Sent SMS Message from Built-in Content Provider
            c = cr.query(draftURI, reqCols, null, null, null);
        }
        // Attached Cursor with adapter and display in listview
        adapter = new SimpleCursorAdapter(this, R.layout.sms_list_item, c,
                new String[] { "body", "address" }, new int[] {
                R.id.bodyView, R.id.addressView });
        lvMsg.setAdapter(adapter);
    }
}
