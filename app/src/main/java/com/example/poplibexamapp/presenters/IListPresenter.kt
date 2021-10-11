package com.example.poplibexamapp

interface IListPresenter<V : IListItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IListViewPresenter : IListPresenter<ListItemView>