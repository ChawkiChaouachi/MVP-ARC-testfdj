package com.test.fdj.ui.base

import android.os.Bundle
import java.lang.ref.WeakReference

abstract class Presenter<T : BaseView> {

    private var view: WeakReference<T>? = null

    fun getView(): T? {
        return view?.get()
    }

    fun setView(view: T) {
        this.view = WeakReference(view)
    }

    open fun initialize(extras: Bundle?) {}


}