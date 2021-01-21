package com.spacepuzzle;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

//Card type (small square)
public class Card extends FrameLayout {

    private int num = 0;//Number on card
    private TextView lable;

    public Card(Context context) {
        super(context);
        lable = new TextView(getContext());
        lable.setTextSize(32);
        lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.card_background));
        lable.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.gravity = 16; //Gravity = center_vertical

        lp.setMargins(10, 10, 0, 0);
        addView(lable, lp);
        setNum(0);
    }

    public int getNum() {
        return num;
    }

    //Set the number on the card
    public void setNum(int num) {

        //Set the size of the number
        this.num = num;
//        if (num > 0) {
//            lable.setText(num + "");
//        } else {
//            lable.setText("");
//        }
        //Change the background color of the card according to the value
        switch (num) {
            case 0: lable.setBackgroundColor(0xffccc0b2);
            lable.setAlpha(.4f);
            break;
            case 2: lable.setBackgroundColor(0xffeee4da);
                lable.setAlpha(1f);
                lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_giant_image_two)); break;
            case 4: lable.setBackgroundColor(0xffede0c8);
                lable.setAlpha(1f);
                lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.binary_stars_four));break;
            case 8: lable.setBackgroundColor(0xfff2b179);
                lable.setAlpha(1f);
                lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.black_hole_absorbing_eight));break;
            case 16: lable.setBackgroundColor(0xfff59563);
                lable.setAlpha(1f);
                lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.cyan_blue_many_stars_sixteen));break;
            case 32: lable.setBackgroundColor(0xfff67c5f);
                lable.setAlpha(1f);
                lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_many_stars_thirty_two)); break;
            case 64: lable.setBackgroundColor(0xfff65e3b);
                lable.setAlpha(1f);
                lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.many_many_start_sixty_four)); break;
            case 128: lable.setBackgroundColor(0xffedcf72);
                lable.setAlpha(1f);
                lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.bown_galaxy_hundred_twenty_eight)); break;
            case 256: lable.setBackgroundColor(0xffedc750);
                lable.setAlpha(1f);
                lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.quasars_rip_across_galaxy_two_fifty_six)); break;
            case 512: lable.setBackgroundColor(0xffedc850);
                lable.setAlpha(1f);
                lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.accelerating_galaxy_through_milky_way_five_twelve)); break;
            case 1024: lable.setBackgroundColor(0xffecc640);
                lable.setAlpha(1f);
                lable.setBackgroundDrawable(getResources().getDrawable(R.drawable.many_galaxies_thousand_twenty_four)); break;
            default: lable.setBackgroundColor(0xffedc22d);
                lable.setAlpha(1f);break;
        }

    }

    //Determine whether the values ​​on the two cards are equal
    public boolean equals(Card card) {
        return getNum()==card.getNum();
    }

}
