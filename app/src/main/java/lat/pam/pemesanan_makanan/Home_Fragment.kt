// Di dalam file HomeFragment.kt
package lat.pam.pemesanan_makanan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Home_Fragment : Fragment() {

    private lateinit var orderButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var makananAdapter: MakananAdapter
    private lateinit var textViewGreeting: TextView


    private val daftarMakanan = mutableListOf<Makanan>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home_, container, false)


        orderButton = view.findViewById(R.id.buttonOrderNow)
        recyclerView = view.findViewById(R.id.recyclerViewMakanan)
        textViewGreeting = view.findViewById(R.id.textViewGreeting)


        prepareMakananData()


        setupGreeting()

        setupRecyclerView()
        setupOrderButton()

        return view
    }


    private fun prepareMakananData() {
        val imageFOodies = resources.obtainTypedArray(R.array.food_images)
        val foodNames = resources.getStringArray(R.array.food_names)
        val foodDescriptions = resources.getStringArray(R.array.food_descriptions)


        daftarMakanan.clear()

        for (i in foodNames.indices) {
            val imageResId = imageFOodies.getResourceId(i, -1)
            daftarMakanan.add(Makanan(imageResId,foodNames[i], foodDescriptions[i]))
        }
        imageFOodies.recycle()
    }


    private fun setupGreeting() {
        val username = activity?.intent?.getStringExtra("EXTRA_USERNAME")
        if (!username.isNullOrEmpty()) {
            textViewGreeting.text = "Halo, $username"
        } else {
            textViewGreeting.text = "Halo, Pengguna"
        }
    }

    private fun setupRecyclerView() {

        makananAdapter = MakananAdapter(daftarMakanan) { selectedItems: List<Makanan> ->

            if (selectedItems.isNotEmpty()) {
                orderButton.visibility = View.VISIBLE
                orderButton.text = "Pesan ${selectedItems.size} Makanan"
            } else {
                orderButton.visibility = View.GONE
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = makananAdapter
    }


    private fun setupOrderButton() {
        orderButton.setOnClickListener {

            val selectedMakanan = daftarMakanan.filter { it.isSelected }

            if (selectedMakanan.isEmpty()) {
                Toast.makeText(context, "Pilih minimal satu makanan", Toast.LENGTH_SHORT).show()
            } else {

                val orderedItemsString = selectedMakanan.joinToString(", ") { it.name }
                Toast.makeText(context, "Memesan: $orderedItemsString", Toast.LENGTH_LONG).show()


                val detailFragment = Detail_Fragment()


                val bundle = Bundle()

                val selectedFoodsArray = selectedMakanan.toTypedArray()
                bundle.putParcelableArray("selected_foods", selectedFoodsArray)
                detailFragment.arguments = bundle


                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.nav_host_fragment, detailFragment)
                    addToBackStack(null)
                    commit()
                }
            }
        }}}


