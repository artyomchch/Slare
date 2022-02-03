package tennisi.borzot.strada.fragments.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ItemSettingsBinding

class SettingsListAdapter : RecyclerView.Adapter<SettingsItemViewHolder>() {

    val list = listOf(
        SettingsItem(R.drawable.ic_settings_account_24, ACCOUNT_LABEL, ACCOUNT_DESCRIPTION),
        SettingsItem(R.drawable.ic_settings_language_24, LANGUAGE_LABEL, LANGUAGE_DESCRIPTION),
        SettingsItem(R.drawable.ic_settings_notification_24, NOTIFICATION_LABEL, NOTIFICATION_DESCRIPTION),
        SettingsItem(R.drawable.ic_settings_service_24, SERVICE_LABEL, SERVICE_DESCRIPTION),
        SettingsItem(R.drawable.ic_settings_unit_24, UNIT_LABEL, UNIT_DESCRIPTION),
        SettingsItem(R.drawable.ic_settings_appearance_24, APPEARANCE_LABEL, APPEARANCE_DESCRIPTION)

    )

    var onSettingsItemClickListener: ((SettingsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsItemViewHolder {
        val binding = ItemSettingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingsItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: SettingsItemViewHolder, position: Int) {
        val settingsItem = list[position]
        with(viewHolder.binding) {
            settingsImage.setImageResource(settingsItem.image)
            settingsLabel.text = settingsItem.name
            settingsDescription.text = settingsItem.description
        }
        viewHolder.itemView.setOnClickListener {
            onSettingsItemClickListener?.invoke(settingsItem)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    companion object {
        const val ACCOUNT_LABEL = "Account"
        const val ACCOUNT_DESCRIPTION = "Logging in or out\nof your account"
        const val LANGUAGE_LABEL = "Language"
        const val LANGUAGE_DESCRIPTION = "English, Spanish,\nRussian"
        const val NOTIFICATION_LABEL = "Notification"
        const val NOTIFICATION_DESCRIPTION = "Turn notifications\non or off"
        const val SERVICE_LABEL = "Service"
        const val SERVICE_DESCRIPTION = "Internal application\nsetup"
        const val UNIT_LABEL = "Unit"
        const val UNIT_DESCRIPTION = "Speed unit,\nkm/h or mph"
        const val APPEARANCE_LABEL = "Appearance"
        const val APPEARANCE_DESCRIPTION = "Light, dark,\nsystem themes"
    }

}