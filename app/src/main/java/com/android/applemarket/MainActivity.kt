package com.android.applemarket

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var appleDataList = init()

        val adapter = AppleAdapter(appleDataList)
        binding.mainRecyclerview.adapter = adapter
        binding.mainRecyclerview.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : AppleAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val appleItem = AppleItem(
                    appleDataList[position].appleImg,
                    appleDataList[position].appleName,
                    appleDataList[position].appleIntro,
                    appleDataList[position].appleSeller,
                    appleDataList[position].applePrice,
                    appleDataList[position].appleAddress,
                    appleDataList[position].appleHeart,
                    appleDataList[position].appleChat
                )
                val argument = Bundle()
                val detailFragment = DetailFragment()
                argument.putParcelable("Apple_Key", appleItem)
                detailFragment.arguments = argument

                supportFragmentManager.beginTransaction()
                    .add(R.id.main_fragment, detailFragment)
                    .commit()
            }

            override fun onLongClick(view: View, position: Int) {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("상품 삭제")
                builder.setMessage("상품을 정말로 삭제하시겠습니까?")
                builder.setIcon(R.drawable.chat)

                val listener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        when(which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                adapter.removeItem(position)
                            }
                        }
                    }
                }

                builder.setPositiveButton("확인", listener)
                builder.setNegativeButton("취소", listener)
                builder.show()
            }
        }

        binding.btnMainNotification.setOnClickListener {
            notification()
        }

        binding.btnMainFloat.setOnClickListener {
            binding.mainRecyclerview.scrollToPosition(0)
        }

        binding.mainRecyclerview.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
            this.adapter = adapter

            addOnScrollListener(scrollListener())
        }

    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.dialog_title)
        builder.setMessage(R.string.dialog_message)
        builder.setIcon(R.drawable.chat)

        val listener = DialogInterface.OnClickListener { dialog, which ->
            when(which) {
                DialogInterface.BUTTON_POSITIVE -> finish()
            }
        }
        builder.setPositiveButton(R.string.dialog_positive, listener)
        builder.setNegativeButton(R.string.dialog_negative, listener)

        builder.show()
    }

    private fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        val channelID = "one-channel"
        val channelName = "My Channel One"
        val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = "My Channel One Description"
            setShowBadge(true)
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            enableVibration(true)
        }
        manager.createNotificationChannel(channel)
        builder = NotificationCompat.Builder(this, channelID)

        builder.run {
            setSmallIcon(R.drawable.apple)
            setWhen(System.currentTimeMillis())
            setContentTitle(getText(R.string.notification_title))
            setContentText(getText(R.string.notification_text))
        }
        manager.notify(1, builder.build())
    }
    
    private fun init(): MutableList<AppleItem> {
        val name = resources.getStringArray(R.array.sample_names)
        val intro = resources.getStringArray(R.array.sample_intro)
        val seller = resources.getStringArray(R.array.sample_seller)
        val price = resources.getStringArray(R.array.sample_price)
        val address = resources.getStringArray(R.array.sample_address)
        val heart = resources.getStringArray(R.array.sample_heart)
        val chat = resources.getStringArray(R.array.sample_chat)
        val decimal = DecimalFormat("#,###")
        val dataList = mutableListOf<AppleItem>()
        for(i in name.indices) {
            dataList.add(
                AppleItem(
                    resources.getIdentifier("sample${i + 1}", "drawable", packageName),
                    name[i],
                    intro[i],
                    seller[i],
                    decimal.format(price[i].toInt()),
                    address[i],
                    heart[i].toInt(),
                    chat[i].toInt()
                )
            )
        }
        return dataList
    }

    private fun scrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, newState: Int, dy: Int) {
                super.onScrolled(recyclerView, newState, dy)
                with(binding.btnMainFloat) {
                    if (!binding.mainRecyclerview.canScrollVertically(-1)) {
                        visibility = INVISIBLE
                        animate().alpha(0f).duration = 400
                    }
                    else {
                        visibility = VISIBLE
                        animate().alpha(1f).duration = 400
                    }
                }
            }
        }
    }
}