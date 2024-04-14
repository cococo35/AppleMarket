package com.android.applemarket

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.applemarket.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val decimal = DecimalFormat("#,###")

        val appleDataList = mutableListOf<AppleItem>()
        appleDataList.add(
            AppleItem(
                R.drawable.sample1,
                getString(R.string.sample1_name),
                getString(R.string.sample1_intro),
                getString(R.string.sample1_seller),
                decimal.format(getString(R.string.sample1_price).toInt()),
                getString(R.string.sample1_address),
                getString(R.string.sample1_heart).toInt(),
                getString(R.string.sample1_chat).toInt()
            )
        )
        appleDataList.add(
            AppleItem(
                R.drawable.sample2,
                getString(R.string.sample2_name),
                getString(R.string.sample2_intro),
                getString(R.string.sample2_seller),
                decimal.format(getString(R.string.sample2_price).toInt()),
                getString(R.string.sample2_address),
                getString(R.string.sample2_heart).toInt(),
                getString(R.string.sample2_chat).toInt()
            )
        )
        appleDataList.add(
            AppleItem(
                R.drawable.sample3,
                getString(R.string.sample3_name),
                getString(R.string.sample3_intro),
                getString(R.string.sample3_seller),
                decimal.format(getString(R.string.sample3_price).toInt()),
                getString(R.string.sample3_address),
                getString(R.string.sample3_heart).toInt(),
                getString(R.string.sample3_chat).toInt()
            )
        )
        appleDataList.add(
            AppleItem(
                R.drawable.sample4,
                getString(R.string.sample4_name),
                getString(R.string.sample4_intro),
                getString(R.string.sample4_seller),
                decimal.format(getString(R.string.sample4_price).toInt()),
                getString(R.string.sample4_address),
                getString(R.string.sample4_heart).toInt(),
                getString(R.string.sample4_chat).toInt()
            )
        )
        appleDataList.add(
            AppleItem(
                R.drawable.sample5,
                getString(R.string.sample5_name),
                getString(R.string.sample5_intro),
                getString(R.string.sample5_seller),
                decimal.format(getString(R.string.sample5_price).toInt()),
                getString(R.string.sample5_address),
                getString(R.string.sample5_heart).toInt(),
                getString(R.string.sample5_chat).toInt()
            )
        )
        appleDataList.add(
            AppleItem(
                R.drawable.sample6,
                getString(R.string.sample6_name),
                getString(R.string.sample6_intro),
                getString(R.string.sample6_seller),
                decimal.format(getString(R.string.sample6_price).toInt()),
                getString(R.string.sample6_address),
                getString(R.string.sample6_heart).toInt(),
                getString(R.string.sample6_chat).toInt()
            )
        )
        appleDataList.add(
            AppleItem(
                R.drawable.sample7,
                getString(R.string.sample7_name),
                getString(R.string.sample7_intro),
                getString(R.string.sample7_seller),
                decimal.format(getString(R.string.sample7_price).toInt()),
                getString(R.string.sample7_address),
                getString(R.string.sample7_heart).toInt(),
                getString(R.string.sample7_chat).toInt()
            )
        )
        appleDataList.add(
            AppleItem(
                R.drawable.sample8,
                getString(R.string.sample8_name),
                getString(R.string.sample8_intro),
                getString(R.string.sample8_seller),
                decimal.format(getString(R.string.sample8_price).toInt()),
                getString(R.string.sample8_address),
                getString(R.string.sample8_heart).toInt(),
                getString(R.string.sample8_chat).toInt()
            )
        )
        appleDataList.add(
            AppleItem(
                R.drawable.sample9,
                getString(R.string.sample9_name),
                getString(R.string.sample9_intro),
                getString(R.string.sample9_seller),
                decimal.format(getString(R.string.sample9_price).toInt()),
                getString(R.string.sample9_address),
                getString(R.string.sample9_heart).toInt(),
                getString(R.string.sample9_chat).toInt()
            )
        )
        appleDataList.add(
            AppleItem(
                R.drawable.sample10,
                getString(R.string.sample10_name),
                getString(R.string.sample10_intro),
                getString(R.string.sample10_seller),
                decimal.format(getString(R.string.sample10_price).toInt()),
                getString(R.string.sample10_address),
                getString(R.string.sample10_heart).toInt(),
                getString(R.string.sample10_chat).toInt()
            )
        )

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
        }

        binding.btnMainNotification.setOnClickListener {
            notification()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        var builder = AlertDialog.Builder(this)
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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
        }
        else builder = NotificationCompat.Builder(this)

        builder.run {
            setSmallIcon(R.drawable.apple)
            setWhen(System.currentTimeMillis())
            setContentTitle(getText(R.string.notification_title))
            setContentText(getText(R.string.notification_text))
        }
        manager.notify(1, builder.build())
    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.main_fragment, frag)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }
}