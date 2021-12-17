package tennisi.borzot.strada.fragments.add.data.datasource

import tennisi.borzot.strada.fragments.add.data.database.AppDatabase

class CarLocalDataSourceImpl(private val appDatabase: AppDatabase): CarLocalDataSource {

    override fun method() {
        appDatabase.carListDao()
    }
}