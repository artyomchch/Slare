package tennisi.borzot.strada.fragments.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tennisi.borzot.strada.R
import tennisi.borzot.strada.databinding.ItemSettingsBinding

class SettingsListAdapter : RecyclerView.Adapter<SettingsItemViewHolder>() {

    val list = listOf(
        SettingsItem(
            R.drawable.ic_settings_account_24, "Account", "Logging in or out\n" +
                    "of your account"
        ), SettingsItem(R.drawable.ic_settings_language_24, "Language", "English, Spanish,\nRussian"),
        SettingsItem(R.drawable.ic_settings_notification_24, "Notification", "Turn notifications\n" +
                "on or off"),
        SettingsItem(R.drawable.ic_settings_service_24, "Service", "Internal application\n" +
                "setup"),
        SettingsItem(R.drawable.ic_settings_unit_24, "Unit", "Speed unit,\n" +
                "km/h or mph"),
        SettingsItem(R.drawable.ic_settings_appearance_24, "Appearance", "Light, dark,\n" +
                "system themes")

    )

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
    }

    override fun getItemCount(): Int {
      return list.size
    }
}