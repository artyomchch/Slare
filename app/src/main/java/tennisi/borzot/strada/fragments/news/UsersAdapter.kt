package tennisi.borzot.strada.fragments.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.CustomPopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ItemNewsBinding
import tennisi.borzot.strada.fragments.news.model.User
import tennisi.borzot.strada.fragments.news.viewModel.UserListItem

interface UserActionListener {

    fun onUserDetails(user: User)

}

class UsersDiffCallback(
    private val oldList: List<UserListItem>,
    private val newList: List<UserListItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.user.id == newUser.user.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser == newUser
    }

}

class UsersAdapter(private val actionListener: UserActionListener) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(), View.OnClickListener {

    var users: List<UserListItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            val diffCallback = UsersDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)

        binding.changeElement.setOnClickListener(this)

        return UsersViewHolder(binding)
    }

    override fun onClick(v: View) {
        val user = v.tag as User
        actionListener.onUserDetails(user)
    }


    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val userListItem = users[position]
        val user = userListItem.user
        val context = holder.itemView.context

        with(holder.binding) {
            holder.itemView.tag = user
            changeElement.tag = user

            if (userListItem.isInProgress) {
                changeElement.visibility = View.INVISIBLE
                newsProgressBar.visibility = View.VISIBLE
                holder.binding.root.setOnClickListener(null)
            } else {
                changeElement.visibility = View.VISIBLE
                newsProgressBar.visibility = View.GONE
                holder.binding.root.setOnClickListener(this@UsersAdapter)
            }

            titleNews.text = user.name
            descNews.text = if (user.company.isNotBlank()) user.company else context.getString(R.string.unemployed)
            if (user.photo.isNotBlank()) {
                Glide.with(imageNews.context)
                    .load(user.photo)
                   // .circleCrop()
                    .placeholder(R.drawable.ic_user_avatar)
                    .error(R.drawable.ic_user_avatar)
                    .into(imageNews)
            } else {
                Glide.with(imageNews.context).clear(imageNews)
                imageNews.setImageResource(R.drawable.ic_user_avatar)
            }
        }
    }

    override fun getItemCount(): Int = users.size

    class UsersViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

}