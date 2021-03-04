package tennisi.borzot.strada.fragments.add.floating_button


import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View

class FragmentButtonPresenter(_view: FragmentButtonInterface.View): FragmentButtonInterface.Presenter {
    private var view: FragmentButtonInterface.View = _view
    private var model: FragmentButtonInterface.Model = FragmentButtonModel()
    var TAG = "test presenter"

    init {
        model.restoreData(view.getArgument())
    }

    override fun dataBaseAdd() {
        if (model.addDataToDB(
                view.getBrand(),
                view.getModel(),
                view.getDescription(),
                view.getImageCar())
        ){
            view.closeFragment()
            view.logAddDb()
        }
    }

    override fun dataBaseDelete() {
        model.deleteDataToDB()
        view.closeFragment()
    }


    override fun dataBaseUpdate() {
        model.dataBaseUpdate(view.getImageCar())
        view.closeFragment()
    }



    override fun searchInputTags(inputCar: String):ArrayList<String> = model.searchInputTags(inputCar)
    override fun searchInputPicture(inputCar: String): Int = model.searchInputPicture(inputCar)







    override fun setCarId(): String = model.setCarId()
    override fun setCarBrand(): String = model.setCarBrand()
    override fun setCarModel(): String = model.setCarModel()
    override fun setCarDescription(): String = model.setCarDescription()



    override fun setCarPicture(): Bitmap = model.setCarPicture()
    override fun setTagCars(): ArrayList<String> = model.getCarTag()

    override fun getUpdateCarBrand(updateBrand: String) {
       model.getUpdateCarBrand(updateBrand)
    }
    override fun getUpdateCarModel(updateModel: String) {
        model.getUpdateCarModel(updateModel)
    }

    override fun getUpdateCarDescription(updateDescription: String) {
        model.getUpdateCarDescription(updateDescription)
    }


    override fun getRemake(): Boolean = model.getCarChoose()




}


