package com.example.spacexlaunches.model

import android.os.Parcel
import android.os.Parcelable
import com.example.spacexlaunches.utils.enum.SortType

class Filters(var year : Int? = null, var wasSuccess: Boolean? = null, var sortType: SortType? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(year)
        parcel.writeValue(wasSuccess)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Filters> {
        override fun createFromParcel(parcel: Parcel): Filters {
            return Filters(parcel)
        }

        override fun newArray(size: Int): Array<Filters?> {
            return arrayOfNulls(size)
        }
    }
}
