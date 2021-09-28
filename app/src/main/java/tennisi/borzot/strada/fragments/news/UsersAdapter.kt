package tennisi.borzot.strada.fragments.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.widget.CustomPopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.Job
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ItemNewsBinding
import tennisi.borzot.strada.databinding.ItemUserBinding
import tennisi.borzot.strada.fragments.news.model.User
import tennisi.borzot.strada.fragments.news.viewModel.UserListItem

interface UserActionListener {
    fun onUserMove(user: User, moveBy: Int): Job

    fun onUserDelete(user: User): Job

    fun onUserDetails(user: User)

    fun onUserFire(user: User): Job
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
        when (v.id) {
            R.id.moreImageViewButton -> {
                showPopupMenu(v)
            }
            else -> {
                actionListener.onUserDetails(user)
            }
        }
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

    private fun showPopupMenu(view: View) {
        val popupMenu = CustomPopupMenu(view.context, view)
        val context = view.context
        val user = view.tag as User
        val position = users.indexOfFirst { it.user.id == user.id }

        popupMenu.menu.add(0, ID_MOVE_UP, Menu.NONE, context.getString(R.string.move_up)).apply {
            isEnabled = position > 0
            setIcon(R.drawable.ic_up)
        }
        popupMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE, context.getString(R.string.move_down)).apply {
            isEnabled = position < users.size - 1
            setIcon(R.drawable.ic_down)
        }
        popupMenu.menu.add(0, ID_REMOVE, Menu.NONE, context.getString(R.string.remove)).apply {
            setIcon(R.drawable.ic_delete)
        }

        if (user.company.isNotBlank()){
            popupMenu.menu.add(0, ID_FIRE, Menu.NONE, context.getString(R.string.fire)).apply {
                setIcon(R.drawable.ic_fire)
            }
        }

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_MOVE_UP -> {
                    actionListener.onUserMove(user, -1)
                }
                ID_MOVE_DOWN -> {
                    actionListener.onUserMove(user, 1)
                }
                ID_REMOVE -> {
                    actionListener.onUserDelete(user)
                }
                ID_FIRE -> {
                    actionListener.onUserFire(user)
                }
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }

    companion object {
        private const val ID_MOVE_UP = 1
        private const val ID_MOVE_DOWN = 2
        private const val ID_REMOVE = 3
        private const val ID_FIRE = 4
    }

    class UsersViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)


}