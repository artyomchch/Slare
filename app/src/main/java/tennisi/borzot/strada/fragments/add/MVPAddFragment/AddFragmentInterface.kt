package tennisi.borzot.strada.fragments.add.MVPAddFragment


import tennisi.borzot.strada.database.Cars
import tennisi.borzot.strada.fragments.add.floating_button.AddCarButtonFragment

interface AddFragmentInterface {

    interface View{
        fun showFragment(carButtonFragment: AddCarButtonFragment)
    }

    interface Presenter{
        fun checkAuto():Boolean
        fun getListCar():MutableList<Cars>
        fun notifyChanges()
        fun saveData(id: Long)
        fun clearFragment()
        fun getFragment(): AddCarButtonFragment
    }

    interface Model{
       fun getListCar():MutableList<Cars>
       fun checkAuto():Boolean
       fun getAutoFromDb()
       fun notifyChanges()
       fun setEmptyData()
       fun setFullData(id: Long)
       fun getFragment(): AddCarButtonFragment
    }
}