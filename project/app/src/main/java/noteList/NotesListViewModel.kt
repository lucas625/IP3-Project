/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package noteList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data_sources.DataSource
import models.Anotacao
import kotlin.random.Random

class NotesListViewModel(val dataSource: DataSource) : ViewModel() {

    val notesLiveData = dataSource.getAnotacaosList()

    /* If the name and description are present, create new Note and add it to the datasource */
    fun insertNote(noteTitle: String?, noteText: String?, noteDate: String?) {
        if (noteTitle == null || noteDate == null || noteText == null ) {
            return
        }

        val str = "Image"
        val byteArray = str.toByteArray()

        val newNote = Anotacao(
            Random.nextLong(),
                "User",
                noteTitle,
            noteDate,
                noteText,
                byteArray

        )

        dataSource.addAnotacao(newNote)
    }
}

class NotesListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}