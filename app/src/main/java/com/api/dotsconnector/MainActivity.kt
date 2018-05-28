package com.api.dotsconnector

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Dotsadptr.DotsSelection {

    val list = arrayListOf<String>()
    var listlinesSet = arrayListOf<DotsHelper>()
    var adptr: Dotsadptr? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..100) {
            var item = DotsHelper()
            item.left = false
            item.top = false

            listlinesSet.add(item)
        }

        val manager = GridLayoutManager(this@MainActivity, 5, LinearLayoutManager.VERTICAL, false)

//        manager.setSpanSizeLookup(object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                Log.d("SpanSize","${Accessibility.get_screen_width(this@MainActivity)/Accessibility.dpToPx(this@MainActivity,50)}")
//                return Accessibility.get_screen_width(this@MainActivity)/50//Accessibility.dpToPx(this@MainActivity,20)
//            }
//        })

        Log.d("Width", "${Accessibility.get_screen_width(this@MainActivity)}")
        Log.d("RowPixel", "${Accessibility.dpToPx(this@MainActivity, 50)}")

        recycler_dots.setLayoutManager(manager)
        adptr = Dotsadptr(this@MainActivity, listlinesSet, this)
        recycler_dots.setAdapter(adptr)
    }

    override fun topSelection(itemView: View, position: Int) {
        listlinesSet.get(position).top = true
        Log.d("Position Top", "" + position)
        if (position > 4) {
            listlinesSet.get(position - 5).bottom = true
            Log.d("Position Bottom", "" + (position - 5))
        }




        MatchChecker(position, "top")

    }

    override fun leftSelection(itemView: View, position: Int) {
        listlinesSet.get(position).left = true
        Log.d("Position Left", "" + position);

        if (position > 0) {
            listlinesSet.get(position - 1).right = true
            Log.d("Position Right", "" + (position - 1))
        } else {

        }

        MatchChecker(position, "left")
    }

    private fun MatchChecker(position: Int, side: String) {
        /*        //For 10 Row
//        if (listlinesSet.get(position).left && listlinesSet.get(position + 10).top &&
//                listlinesSet.get(position + 1).left && listlinesSet.get(position).top) {
//        //  Case 0-10-1-0
//            Log.d("Position All Set 1", "True")
//            Toast.makeText(this@MainActivity, "True", Toast.LENGTH_LONG).show()
//        } else if (listlinesSet.get(position).left && listlinesSet.get(position + 9).top &&
//                listlinesSet.get(position - 1).left && listlinesSet.get(position - 1).top) {
//        //  Case 1-10-0-0
//            Log.d("Position All Set 2", "True")
//            Toast.makeText(this@MainActivity, "True", Toast.LENGTH_LONG).show()
//        } else if (position > 9) {
//            if(side.equals("top")) {
//                if (listlinesSet.get(position - 10).left && listlinesSet.get(position - 10).top &&
//                        listlinesSet.get(position - 9).left && listlinesSet.get(position).top) {
//                    //  Case 0-0-1-10
//                    Log.d("Position All Set 3", "True")
//                    Toast.makeText(this@MainActivity, "True", Toast.LENGTH_LONG).show()
//                }
//            }
//        }*/

        /* For 5 Row
        if (listlinesSet.get(position).left && listlinesSet.get(position + 5).top &&
                 listlinesSet.get(position + 1).left && listlinesSet.get(position).top) {
             //  Case 0-10-1-0
             Log.d("Position All Set 1", "True")
             Toast.makeText(this@MainActivity, "True", Toast.LENGTH_LONG).show()
         } else if (listlinesSet.get(position).left && listlinesSet.get(position + 4).top &&
                 listlinesSet.get(position - 1).left && listlinesSet.get(position - 1).top) {
             //  Case 1-10-0-0
             Log.d("Position All Set 2", "True")
             Toast.makeText(this@MainActivity, "True", Toast.LENGTH_LONG).show()
         } else if (position > 4) {
             if (side.equals("top")) {
                 if (listlinesSet.get(position - 5).left && listlinesSet.get(position - 5).top &&
                         listlinesSet.get(position - 4).left && listlinesSet.get(position).top) {
                     //  Case 0-0-1-10
                     Log.d("Position All Set 3", "True")
                     Toast.makeText(this@MainActivity, "True", Toast.LENGTH_LONG).show()
                 }
             }
         }*/

        if (listlinesSet.get(position).top && listlinesSet.get(position).left
                && listlinesSet.get(position).right && listlinesSet.get(position).bottom) {
            listlinesSet.get(position).allset = true
            Log.d("Position","Position")
        } else {
            if (position > 0) {
                if (listlinesSet.get(position - 1).top && listlinesSet.get(position - 1).left
                        && listlinesSet.get(position - 1).right && listlinesSet.get(position - 1).bottom) {
                    listlinesSet.get(position - 1).allset = true
                    Log.d("Position","Position-1")
                } else if (listlinesSet.get(position + 1).top && listlinesSet.get(position + 1).left
                        && listlinesSet.get(position + 1).right && listlinesSet.get(position + 1).bottom) {

                    listlinesSet.get(position + 1).allset = true
                    Log.d("Position","Position+1")
                } else if (position > 4) {
                    if (listlinesSet.get(position + 5).top && listlinesSet.get(position + 5).left
                            && listlinesSet.get(position + 5).right && listlinesSet.get(position + 5).bottom) {

                        listlinesSet.get(position + 5).allset = true
                        Log.d("Position","Position+5")

                    } else if (listlinesSet.get(position - 5).top && listlinesSet.get(position - 5).left
                            && listlinesSet.get(position - 5).right && listlinesSet.get(position - 5).bottom) {

                        listlinesSet.get(position - 5).allset = true
                        Log.d("Position","Position-5")

                    }
                }
            }
        }


        adptr!!.notifyItemChanged(position)
    }
}
