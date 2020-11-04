package tennisi.borzot.slare.onboarding.fragments.add

import java.util.*
import kotlin.collections.ArrayList

class TagClass {
    private var code: String? = null
    private var name: String? = null
    private var color: String? = null

    fun TagClass() {}

    fun TagClass(sinif: String?, name: String?) {
        code = sinif
        this.name = name
        color = getRandomColor()
    }

    fun getRandomColor(): String? {
        val colors: ArrayList<String> = ArrayList()
        colors.add("#ED7D31")
        colors.add("#00B0F0")
        colors.add("#FF0000")
        colors.add("#D0CECE")
        colors.add("#00B050")
        colors.add("#9999FF")
        colors.add("#FF5FC6")
        colors.add("#FFC000")
        colors.add("#7F7F7F")
        colors.add("#4800FF")
        return colors[Random().nextInt(colors.size)]
    }


    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getColor(): String? {
        return color
    }

    fun setColor(color: String?) {
        this.color = color
    }

    fun getSinif(): String? {
        return code
    }

    fun setSinif(code: String?) {
        this.code = code
    }

}