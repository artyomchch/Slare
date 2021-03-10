package tennisi.borzot.strada.fragments.add.MVPAddFragment

import tennisi.borzot.strada.database.Cars
import tennisi.borzot.strada.fragments.add.floating_button.AddCarButtonFragment


class AddFragmentPresenter(_view: AddFragmentInterface.View): AddFragmentInterface.Presenter {
    private var view: AddFragmentInterface.View = _view
    private var model: AddFragmentInterface.Model = AddFragmentModel()

    init {

    }

    override fun checkAuto(): Boolean = model.checkAuto()
    override fun getListCar(): MutableList<Cars> {
        model.getAutoFromDb()
        return model.getListCar()
    }

    override fun notifyChanges() {
        model.notifyChanges()
    }

    override fun saveData(id: Long) {
        model.setFullData(id)
        view.showFragment(model.getFragment())
    }

    override fun clearFragment() {
        model.setEmptyData()
        view.showFragment(model.getFragment())
    }

    override fun getFragment(): AddCarButtonFragment = model.getFragment()



}