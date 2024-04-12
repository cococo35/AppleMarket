package com.android.applemarket

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
                val name: String = appleDataList[position].appleName
                Toast.makeText(this@MainActivity, "$name 선택!", Toast.LENGTH_SHORT).show()
            }
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
}