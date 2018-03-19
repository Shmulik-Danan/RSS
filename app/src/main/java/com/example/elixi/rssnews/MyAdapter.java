package com.example.elixi.rssnews;
/**
 * Created by Shmulik on 18 מרץ 2018.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.example.elixi.rssnews.Fragment.descriptionFragment;
import com.example.elixi.rssnews.model.Item;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RssItemHolder> {
    private static final String TITLE = "title";
    private static final String IMAGE = "image";
    private static final String SRC = "src";
    private static final String URL = "url";
    private List<Item> rssFeedItems;
    Context context;
    FragmentManager supportFragmentManager;

    public MyAdapter(Context context, FragmentManager supportFragmentManager) {
        this.context = context;
        this.supportFragmentManager = supportFragmentManager;
    }

    public void setList(List<Item> feed) {
        this.rssFeedItems = feed;

    }

    @Override
    public RssItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View v = layoutInflater.inflate(R.layout.cardview, viewGroup, false);
        return new RssItemHolder(v);
    }

    @Override
    public int getItemCount() {
        return rssFeedItems.size();
    }

    public void clearData() {
        // clear the data
        rssFeedItems.clear();
    }

    public void setData(List<Item> feed) {
        this.rssFeedItems = feed;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public void onBindViewHolder(final RssItemHolder holder, int pos) {
        Item item = rssFeedItems.get(pos);
        Bundle args = new Bundle();

        args.putString(URL, item.getLink());
        args.putString(TITLE, item.getTitle());

        holder.titleTextField.setText(item.getTitle());

        ////parse HTML data
        String des = item.getDescription();
        if (des != null) {
            Document doc = Jsoup.parse(item.getDescription());
            Element imageElement = doc.select("img").first();
            if (imageElement != null) {
                String absoluteUrl = imageElement.absUrl("src");
                if (absoluteUrl != null) {

                    //  Glide.with(context).load(absoluteUrl).into(holder.imageView);
                    Picasso.with(context).load(absoluteUrl).fit().centerCrop().into(holder.imageView);
                    args.putString(IMAGE, absoluteUrl);
                    args.putString(SRC, doc.body().text());

                }

            }else  Picasso.with(context).load(R.drawable.news_image).fit().centerCrop().into(holder.imageView);


            holder.publicationDateTextField.setText(item.getPubDate());
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Open description windows -- when cardView is clicked
                descriptionFragment descriptionFragment = new descriptionFragment();
                descriptionFragment.setArguments(args);
                FragmentManager manager = supportFragmentManager;

                manager.beginTransaction().addToBackStack(null)
                        .replace(R.id.relativelayout_for_fragment,
                                descriptionFragment,
                                descriptionFragment.getTag()
                        ).commit();

            }
        });

    }




    public static class RssItemHolder extends RecyclerView.ViewHolder {
        private TextView titleTextField;
        CardView cardView;
        private ImageView imageView;
        private TextView publicationDateTextField;

        RssItemHolder(View itemView) {
            super(itemView);
            titleTextField = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            publicationDateTextField = (TextView) itemView.findViewById(R.id.pubdate);

            cardView = (CardView) itemView.findViewById(R.id.cardview);

        }
    }
}
