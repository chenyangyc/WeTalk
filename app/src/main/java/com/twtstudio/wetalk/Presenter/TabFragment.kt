package com.twtstudio.wetalk.Presenter

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.edu.twt.retrox.recyclerviewdsl.withItems
import com.orhanobut.hawk.Hawk
import com.twtstudio.wetalk.Model.MessageToRead
import com.twtstudio.wetalk.R
import com.twtstudio.wetalk.View.setFriendItem
import com.twtstudio.wetalk.View.setIntroItem
import com.twtstudio.wetalk.View.setProfileItem
import com.twtstudio.wetalk.View.setTalkItem
import kotlinx.android.synthetic.main.fragment_layout.*

class TabFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_layout, container, false)

        recyclerView = view.findViewById(R.id.content_rc)
        recyclerView.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        return view
    }

    fun setTalkList() {
        content_rc.withItems {
            for (i in MessageToRead) {
                val show = i.messages[i.messages.size - 1]
                if (!show.text.contains("https://"))
                    setTalkItem(i.name, show.text, "", "", show.time)
                else
                    setTalkItem(i.name, "[图片]", "","", show.time)
            }
        }
    }

    fun setMineList(act: Activity) {
        content_rc.withItems {
            setProfileItem("", Hawk.get("userID", ""))
            setIntroItem("退出", act)
        }

    }

    fun setFriendList(names: List<String>) {
        content_rc.withItems {
            for (name in names)
                setFriendItem("", name)
        }
    }
}
