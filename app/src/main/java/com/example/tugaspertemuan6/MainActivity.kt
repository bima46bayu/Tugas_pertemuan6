package com.example.tugaspertemuan6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tugaspertemuan6.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
//    private val province = arrayOf(
//        "Jawa Tengah",
//        "Daerah Istimewa Yogyakarta",
//        "Bali"
//    )

//    private lateinit var city : Array<String>

    private lateinit var kehadiran  : Array<String>

    private var selectedDate: String? = null
    private var selectedTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        city = resources.getStringArray(R.array.city)
        kehadiran = resources.getStringArray(R.array.kehadiran)


        with(binding) {
            val adapterkehadiran = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item, kehadiran
            )
            adapterkehadiran.setDropDownViewResource(

                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )
            spinnerKehadiran.adapter = adapterkehadiran

            spinnerKehadiran.onItemSelectedListener=
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                        if (position>0){
                            alasan.visibility = View.VISIBLE
                        }
                        else{
                            alasan.visibility = View.GONE
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }



            fun getMonthName(month: Int): String {
                val monthNames = arrayOf(
                    "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                    "Juli", "Agustus", "September", "Oktober", "November", "Desember"
                )
                return monthNames[month]
            }

            timePicker.setOnTimeChangedListener{view,hourOfday,minute ->
                selectedTime =String.format("%02d:%02d",hourOfday,minute)
                Log.d("SELECTED TIME ", selectedTime!!)
            }

            datePicker.setOnDateChangeListener { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                selectedDate = dateFormat.format(calendar.time)
                Log.d("SELECTED DATE: ", selectedDate!!)
            }


            btnSubmit.setOnClickListener {
                if (selectedDate != null && selectedTime != null) {
                    val toastMessage = "Presensi berhasil $selectedDate jam $selectedTime"
                    Toast.makeText(this@MainActivity, toastMessage, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Pilih tanggal dan waktu terlebih dahulu",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}