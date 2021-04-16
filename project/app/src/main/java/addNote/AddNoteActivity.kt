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

package addNote

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.yourdiary.R
import com.google.android.material.textfield.TextInputEditText

const val NOTE_TITLE = "title"
const val NOTE_TEXT = "text"
const val NOTE_DATE = "date"

class AddNoteActivity : AppCompatActivity() {
    private lateinit var addNoteTitle: TextInputEditText
    private lateinit var addNoteText: TextInputEditText
    private lateinit var addNoteDate: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_note_layout)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addNote()
        }
        addNoteTitle = findViewById(R.id.add_note_title)
        addNoteText = findViewById(R.id.add_note_text)
        addNoteDate = findViewById(R.id.add_note_date)
    }

    /* The onClick action for the done button. Closes the activity and returns the new flower name
    and description as part of the intent. If the name or description are missing, the result is set
    to cancelled. */

    private fun addNote() {
        val resultIntent = Intent()

        if (addNoteTitle.text.isNullOrEmpty() || addNoteText.text.isNullOrEmpty() || addNoteDate.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val title = addNoteTitle.text.toString()
            val text = addNoteText.text.toString()
            val date = addNoteDate.text.toString()
            resultIntent.putExtra(NOTE_TITLE, title)
            resultIntent.putExtra(NOTE_TEXT, text)
            resultIntent.putExtra(NOTE_DATE, date)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}