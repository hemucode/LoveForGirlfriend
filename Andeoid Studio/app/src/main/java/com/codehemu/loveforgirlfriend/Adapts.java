package com.codehemu.loveforgirlfriend;



import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;


public class Adapts extends RecyclerView.Adapter<Adapts.ViewHolder> {
    private Context context;
    private List<ListItem> listItems;

    public Adapts(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }
    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = this.listItems.get(position);
        holder.desc.setText(listItem.getDesc());
        holder.likeImageView.setTag(Integer.valueOf((int) R.drawable.ic_baseline_content_copy_24));
    }


    @Override
    public int getItemCount() {
        return this.listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView desc;
        public ImageView likeImageView;
        public ImageView shareImg;

        public ViewHolder(View itemView) {
            super(itemView);
            this.desc = (TextView) itemView.findViewById(R.id.status_View);
            this.shareImg = (ImageView) itemView.findViewById(R.id.share_icon);
            this.likeImageView = (ImageView) itemView.findViewById(R.id.copy_icon);
            this.likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String position = ViewHolder.this.desc.getText().toString();
                    ClipboardManager Clipboard_Manager = (ClipboardManager) Adapts.this.context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData myClip = ClipData.newPlainText("text", position);
                    Clipboard_Manager.setPrimaryClip(myClip);
                    Toast.makeText(v.getContext(), (R.string.copy), Toast.LENGTH_LONG).show();
                }
            });

            this.desc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String position = ViewHolder.this.desc.getText().toString();
                    ClipboardManager Clipboard_Manager = (ClipboardManager) Adapts.this.context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData myClip = ClipData.newPlainText("text", position);
                    Clipboard_Manager.setPrimaryClip(myClip);
                    Toast.makeText(v.getContext(), (R.string.copy), Toast.LENGTH_SHORT).show();

                }
            });

            this.shareImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String position = ViewHolder.this.desc.getText().toString();
                    Intent share = new Intent("android.intent.action.SEND");
                    share.setType("text/plain");
                    share.putExtra("android.intent.extra.TEXT", position);
                    v.getContext().startActivity(Intent.createChooser(share, "Letter Share"));
                }
            });
        }
    }

}
