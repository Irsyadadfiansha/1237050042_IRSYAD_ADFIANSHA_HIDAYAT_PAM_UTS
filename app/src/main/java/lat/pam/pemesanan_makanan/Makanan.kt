// Di dalam file Makanan.kt
package lat.pam.pemesanan_makanan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Makanan(
    val imageResId: Int,
    val name: String,
    val description: String,
    var isSelected: Boolean = false
) : Parcelable
