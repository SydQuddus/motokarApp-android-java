package com.example.motokarkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment



class ChatFragmentBC : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.chat_fragment, container, false)

//        val chatUserPicture1 = view.findViewById(R.id.chatUserPicture1) as CircleImageView
//        val chatUserPicture2 = view.findViewById(R.id.chatUserPicture2) as CircleImageView
//        val chatUserPicture3 = view.findViewById(R.id.chatUserPicture3) as CircleImageView
//        val chatUserPicture4 = view.findViewById(R.id.chatUserPicture4) as CircleImageView
//        val chatUserPicture5 = view.findViewById(R.id.chatUserPicture5) as CircleImageView
//        val chatUserPicture6 = view.findViewById(R.id.chatUserPicture6) as CircleImageView
//        val chatUserPicture7 = view.findViewById(R.id.chatUserPicture7) as CircleImageView
//
//        val chatUserName1 = view.findViewById(R.id.chatUserName1) as TextView
//        val chatUserName2 = view.findViewById(R.id.chatUserName2) as TextView
//        val chatUserName3 = view.findViewById(R.id.chatUserName3) as TextView
//        val chatUserName4 = view.findViewById(R.id.chatUserName4) as TextView
//        val chatUserName5 = view.findViewById(R.id.chatUserName5) as TextView
//        val chatUserName6 = view.findViewById(R.id.chatUserName6) as TextView
//        val chatUserName7 = view.findViewById(R.id.chatUserName7) as TextView
//
//        val chatUserActive1 = view.findViewById(R.id.chatUserActive1) as TextView
//        val chatUserActive2 = view.findViewById(R.id.chatUserActive2) as TextView
//        val chatUserActive3 = view.findViewById(R.id.chatUserActive3) as TextView
//        val chatUserActive4 = view.findViewById(R.id.chatUserActive4) as TextView
//        val chatUserActive5 = view.findViewById(R.id.chatUserActive5) as TextView
//        val chatUserActive6 = view.findViewById(R.id.chatUserActive6) as TextView
//        val chatUserActive7 = view.findViewById(R.id.chatUserActive7) as TextView
//
//        chatUserPicture1.setImageResource(R.drawable.masha)
//        chatUserPicture2.setImageResource(R.drawable.mrcarl)
//        chatUserPicture3.setImageResource(R.drawable.jerome)
//        chatUserPicture4.setImageResource(R.drawable.memum)
//        chatUserPicture5.setImageResource(R.drawable.jimmy)
//        chatUserPicture6.setImageResource(R.drawable.joshuack)
//        chatUserPicture7.setImageResource(R.drawable.kelly)
//
//        chatUserName1.setText("Masha")
//        chatUserName2.setText("Mr Carl")
//        chatUserName3.setText("Jerome")
//        chatUserName4.setText("Tish Simone")
//        chatUserName5.setText("Jimmy Chan")
//        chatUserName6.setText("Joshua CK")
//        chatUserName7.setText("Gwen Kelly")
//
//        chatUserActive1.setText("Active Now")
//        chatUserActive2.setText("Active Now")
//        chatUserActive3.setText("Active 3h ago")
//        chatUserActive4.setText("Active 30m ago")
//        chatUserActive5.setText("Active Now")
//        chatUserActive6.setText("Active 1h aggo")
//        chatUserActive7.setText("Active 1h ago")

        return view
    }
}
