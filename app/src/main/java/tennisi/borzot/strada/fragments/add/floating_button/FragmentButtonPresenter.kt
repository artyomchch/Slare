package tennisi.borzot.strada.fragments.add.floating_button


import android.os.Bundle
import android.util.Log

class FragmentButtonPresenter(_view: FragmentButtonInterface.View): FragmentButtonInterface.Presenter {
    private var view: FragmentButtonInterface.View = _view
    private var model: FragmentButtonInterface.Model = FragmentButtonModel()
    var TAG = "test presenter"

    init {

    }

    override fun dataBaseAdd() {
        if (model.addDataToDB(
                view.getBrand(),
                view.getModel(),
                view.getDescription(),
                view.getImageCar())
        ){
            view.logAddDb()
        }


    }

    override fun dataBaseDelete(argument: Bundle) {

    }


}