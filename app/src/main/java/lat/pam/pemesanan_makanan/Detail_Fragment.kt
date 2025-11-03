// Di dalam file DetailFragmentFragment.kt
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

private lateinit var orderButton: Button
private lateinit var recyclerView: RecyclerView
private lateinit var makananAdapter: MakananAdapter
private lateinit var textViewGreeting: TextView
class Detail_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_, container, false)


        orderButton = view.findViewById(R.id.buttonOrderNow)
        recyclerView = view.findViewById(R.id.recyclerViewMakanan)
        textViewGreeting = view.findViewById(R.id.textViewGreeting)

        val username = activity?.intent?.getStringExtra("EXTRA_USERNAME")
        if (!username.isNullOrEmpty()) {
            textViewGreeting.text = "Halo, $username"
        } else {
            textViewGreeting.text = "Halo, Pengguna"
        }
        setupRecyclerView()
        setupOrderButton()

        return view
    }


    private fun setupOrderButton() {
        orderButton.setOnClickListener {

            val orderedItems = makananAdapter.getSelectedItems()


            if (orderedItems.isEmpty()) {
                Toast.makeText(context, "Tidak ada item untuk dipesan.", Toast.LENGTH_SHORT).show()
            } else {

                val itemNames = orderedItems.joinToString(", ") { it.name }


                Toast.makeText(context, "Pesanan untuk: $itemNames berhasil dibuat!", Toast.LENGTH_LONG).show()



                val intent = Intent(requireContext(), SendActivity::class.java)


                intent.putExtra("EXTRA_ORDER_SUMMARY", itemNames)
                val username = activity?.intent?.getStringExtra("EXTRA_USERNAME")
                intent.putExtra("EXTRA_USERNAME", username)

                startActivity(intent)
            }
        }
    }

private fun setupRecyclerView() {
        val selectedFoodsArray = arguments?.getParcelableArray("selected_foods") as? Array<Makanan>


        if (selectedFoodsArray != null && selectedFoodsArray.isNotEmpty()) {

            val selectedFoodsList = selectedFoodsArray.toList()


            makananAdapter = MakananAdapter(selectedFoodsList.toMutableList()) {

            }


            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = makananAdapter
        } else {

            Toast.makeText(context, "Tidak ada item yang dipilih", Toast.LENGTH_SHORT).show()
        }
    }}

    