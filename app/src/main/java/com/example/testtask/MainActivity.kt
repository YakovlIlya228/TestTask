package com.example.testtask

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.Fragments.DetailedEditFragment
import com.example.testtask.Pojo.Data
import com.example.testtask.ViewModel.GeneralViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(),
    ProfileListAdapter.ProfileListViewHolder.OnProfileListener {

    val viewModel: GeneralViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val page = 2
        val profilesAdapter = ProfileListAdapter(this, this)
        profilesRecycler.layoutManager = LinearLayoutManager(this)
        profilesRecycler.adapter = profilesAdapter
        viewModel.getLiveDataProfiles().observe(this, Observer {
                profilesAdapter.updateList(it as ArrayList<Data>)
        })
        fun refresh() {
            viewModel.getProfiles(page).observe(this, Observer {
                profilesAdapter.updateList(it.dataList)
                Log.i("LoadingFromNetwork", "Fetched ${it.dataList.size} profiles from the network")
                for (profile in it.dataList) {
                    GlobalScope.launch(Dispatchers.IO) {
                        if (!viewModel.checkExistence(profile.id)) {
                            viewModel.insertProfile(profile)
                            Log.i(
                                "UpdatingCache",
                                "Profile with Id:${profile.id} has been inserted to database!"
                            )
                        }
                    }
                }

            })
        }
        if (isConnected(applicationContext) && savedInstanceState == null) {
            refresh()
        }
        pullToRefresh.setOnRefreshListener {
            if (isConnected(applicationContext))
                refresh()
            else
                Toast.makeText(
                    this,
                    "No connection to network. Try again later!",
                    Toast.LENGTH_SHORT
                ).show()
            pullToRefresh.setRefreshing(false)
        }

        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                viewModel.deleteById(profilesAdapter.profileList[pos].id)
                profilesAdapter.profileList.removeAt(pos)
                profilesAdapter.notifyItemRemoved(pos)
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(profilesRecycler)


    }

    private fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    override fun onProfileClick(holder: ProfileListAdapter.ProfileListViewHolder) {
        super.onProfileClick(holder)
        val bundle = Bundle()
        bundle.putString("firstName", holder.firstName.text.toString())
        bundle.putString("lastName", holder.lastName.text.toString())
        bundle.putString("email", holder.email.text.toString())
        bundle.putString("avatar", holder.avatarUrl)
        bundle.putInt("ID", holder.id!!)
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = DetailedEditFragment()
        fragment.arguments = bundle
        transaction.replace(R.id.main, fragment)
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}