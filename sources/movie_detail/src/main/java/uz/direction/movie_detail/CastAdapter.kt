package uz.direction.movie_detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.direction.movie_detail.data.model.Cast
import uz.direction.movie_detail.databinding.ItemCastBinding
import uz.med.shared.BaseViewHolder
import uz.med.shared.util.inflate

class CastAdapter(
    private val cast: List<Cast>,
    private val onClickListener: (Long) -> Unit
) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {


    class CastViewHolder(view: View) :
        BaseViewHolder<ItemCastBinding>(view, ItemCastBinding::class.java) {

        fun onBind(cast: Cast) = bindView {
            imgPerson.setImageFromUrlPath(cast.profilePath)
            tvPersonCharacter.text = cast.character
            tvPersonName.text = cast.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(parent.inflate(R.layout.item_cast))
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClickListener.invoke(cast[position].id)
        }
        holder.onBind(cast[position])
    }

    override fun getItemCount() = cast.size

}
