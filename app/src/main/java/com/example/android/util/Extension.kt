package com.example.android.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.android.R
import com.google.android.material.snackbar.Snackbar
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_login.*

fun Context.toast(message:String,icon: Boolean){
    FancyToast.makeText(this,message,FancyToast.LENGTH_LONG, FancyToast.DEFAULT,icon).show()
}
fun Context.toastSuccess(message: String,icon: Boolean)
{
    FancyToast.makeText(this,message,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,icon).show()
}
fun Context.toastInfo(message: String,icon: Boolean)
{
    FancyToast.makeText(this,message,FancyToast.LENGTH_LONG,FancyToast.INFO,icon).show()
}
fun Context.toastWarning(message: String,icon: Boolean)
{
    FancyToast.makeText(this,message,FancyToast.LENGTH_LONG,FancyToast.WARNING,icon).show()
}
fun Context.toastError(message: String,icon: Boolean)
{
    FancyToast.makeText(this,message,FancyToast.LENGTH_LONG,FancyToast.ERROR,icon).show()
}
fun Context.toastConfusing(message: String,icon: Boolean)
{
    FancyToast.makeText(this,message,FancyToast.LENGTH_LONG,FancyToast.CONFUSING,icon).show()
}
fun Context.toastCustom(message: String,path:Int,icon: Boolean)
{
    FancyToast.makeText(this, message, FancyToast.LENGTH_LONG, FancyToast.CONFUSING,path, icon)
}
fun View.snackbar(message: String)
{
    Snackbar.make(this,"logged in", Snackbar.LENGTH_INDEFINITE).also {
            snackbar -> snackbar.setAction("Ok"){
            snackbar.dismiss()
        }
    }.show()
}

