package com.example.android.architecture.blueprints.todoapp.study.serialiable

import android.os.Parcel
import android.os.Parcelable

class MyParcelable() : Parcelable {
    var name:String ?= null
    var age:Int ?= null
    constructor(parcel: Parcel) : this() {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {

    }

    companion object CREATOR : Parcelable.Creator<MyParcelable> {
        override fun createFromParcel(parcel: Parcel): MyParcelable {
            return MyParcelable(parcel)
        }

        override fun newArray(size: Int): Array<MyParcelable?> {
            return arrayOfNulls(size)
        }
    }
}