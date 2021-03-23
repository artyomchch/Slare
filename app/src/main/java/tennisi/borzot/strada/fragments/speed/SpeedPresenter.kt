package tennisi.borzot.strada.fragments.speed

import tennisi.borzot.strada.fragments.add.MVPAddFragment.AddFragmentInterface
import tennisi.borzot.strada.fragments.add.MVPAddFragment.AddFragmentModel

class SpeedPresenter(_view: SpeedInterface.View): SpeedInterface.Presenter {
    private var view: SpeedInterface.View = _view
    private var model: SpeedInterface.Model = SpeedModel()


}