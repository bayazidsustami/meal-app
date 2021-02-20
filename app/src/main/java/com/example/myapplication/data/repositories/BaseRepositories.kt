package com.example.myapplication.data.repositories

abstract class BaseRepositories<DataStore> {
    protected var remoteDataStore: DataStore? = null

    fun init(remoteDataStore: DataStore){
        this.remoteDataStore = remoteDataStore
    }
}