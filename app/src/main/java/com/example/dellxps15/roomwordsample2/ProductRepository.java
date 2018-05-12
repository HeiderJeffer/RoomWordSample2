package com.example.dellxps15.roomwordsample2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ProductRepository {

    private ProductDao mProductDao;
    private LiveData<List<Products>> mAllProducts;

    ProductRepository(Application application) {
        ProductRoomDatabase db = ProductRoomDatabase.getDatabase(application);
        mProductDao = db.productDao();
        mAllProducts = mProductDao.getAllProducts();
    }

    LiveData<List<Products>> getAllProducts() {
        return mAllProducts;
    }

    public void insert (Products product) {
        new insertAsyncTask(mProductDao).execute(product);
    }

    private static class insertAsyncTask extends AsyncTask<Products, Void, Void> {

        private ProductDao mAsyncTaskDao;

        insertAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Products... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
