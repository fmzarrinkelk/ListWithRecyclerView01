package edu.fullerton.fz.cs411.listwithrecyclerview01

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PersonListFragment: Fragment() {

    private lateinit var personRecyclerView: RecyclerView

    private var adapter: PersonAdapter? = null

    private val myViewModel: MyViewModel by lazy {
        ViewModelProvider(this)[MyViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "Total People: ${myViewModel.people.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_person_list, container, false)
        this.personRecyclerView = view.findViewById(R.id.person_recycler_view) as RecyclerView
        this.personRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()
        return view
    }

    private fun updateUI() {
        val people = myViewModel.people
        adapter = PersonAdapter(people)
        this.personRecyclerView.adapter = adapter
    }

    private inner class PersonHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var person: Person
        val nameTextView: TextView = this.itemView.findViewById(R.id.nameView)
        val ageTextView: TextView = this.itemView.findViewById(R.id.ageView)
        val isStudent: CheckBox = this.itemView.findViewById(R.id.studentCheckBox)
        fun bind(person: Person) {
            this.person = person
            this.nameTextView.text = this.person.name
            this.ageTextView.text = this.person.age.toString()
            this.isStudent.isChecked = this.person.isStudent
        }
    }

    private inner class PersonAdapter(var people: List<Person>) : RecyclerView.Adapter<PersonHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
            val view = layoutInflater.inflate(R.layout.list_item_person, parent, false)
            return PersonHolder(view)
        }

        override fun getItemCount() = people.size

        override fun onBindViewHolder(holder: PersonHolder, position: Int) {
            val person = this.people[position]
            holder.bind(person)
        }
    }

    companion object {
        fun newInstance(): PersonListFragment {
            return PersonListFragment()
        }
    }
}