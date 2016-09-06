package com.cyril.training.day25;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    // List to store the list of image-resource-ids.
    List<Integer> mImageResourcesList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting the image resources into the list.
        mImageResourcesList= new ArrayList<>();
        mImageResourcesList.addAll(addResourcesToList());

        // ViewPager to set the image resource.
        ViewPager viewPager= (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ImagePagerAdapter(getApplicationContext()));
    }

    /**
     * Method to populate mImageResourcesList
     * @return ArrayList object containing the image-resource-ids
     */
    private ArrayList<Integer> addResourcesToList()
    {
        ArrayList<Integer> drawables= new ArrayList<>();

        drawables.add(R.drawable.cap_amer_shield);
        drawables.add(R.drawable.hydra_icon);
        drawables.add(R.drawable.flash_icon);
        drawables.add(R.drawable.spiderman_icon);
        drawables.add(R.drawable.deadpool_icon);

        return drawables;
    }

    /**
     * Custom PagerAdapter to populate images into the ViewPager.
     */
    class ImagePagerAdapter extends PagerAdapter
    {
        Context mContext;
        LayoutInflater mLayoutInflater;

        public ImagePagerAdapter(Context context)
        {
            mContext= context;
            mLayoutInflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        /**
         * Method to inflate the ViewPager with the image resouce
         * based on the position of the ImageView in the ViewPager.
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            View rootView= mLayoutInflater.inflate(R.layout.image_pager_item, container, false);

            // Setting the ImageView with appropriate resource based on the position.
            ImageView imageView= (ImageView) rootView.findViewById(R.id.imageView_for_ViewPager);

            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),mImageResourcesList.get(position)));

            // Adding the View to the ViewGroup.
            container.addView(rootView);

            return rootView; // returning the rootView to isViewFromObject()
        }

        /**
         * Method to check if the Object (View object) returned is same as the
         * View Object present.
         *
         * @param view
         * @param object
         * @return A boolean value based on the Object type being same as the View type.
         */
        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return (view == object);
        }

        /**
         * Method to get the total number of Images in the List.
         * @return The size of the List.
         */
        @Override
        public int getCount()
        {
            return mImageResourcesList.size();
        }

        /**
         * Method to destroy the off-screen Views.
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((LinearLayout) object);
        }
    }
}
