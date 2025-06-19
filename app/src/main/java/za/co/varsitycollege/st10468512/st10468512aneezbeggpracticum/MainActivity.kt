package za.co.varsitycollege.st10468512.st10468512aneezbeggpracticum

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


        //Arrays that will be used to store the song information
        var songNames = ArrayList<String>()

        var artistArray = ArrayList<String>()

        var ratingArray = ArrayList<Int>()

        var commentsArray = ArrayList<String>()


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)

            //Getting the buttons from the layout
            val ExitButton = findViewById<Button>(R.id.exitButton)
            val NextPage = findViewById<Button>(R.id.nextPage)
            val AddToPlaylist = findViewById<Button>(R.id.playlistButton)


            //Creating the exit button so that once its clicked the program will close
            ExitButton.setOnClickListener {
                finishAffinity()
            }


            //Creating the command whaat will happen once the  button is clicked
            AddToPlaylist.setOnClickListener {

                //Showing the dialog in order to get the song information
                AddSongs()


            }

            //Creating the command of what will happe once the next page button is clicked
            NextPage.setOnClickListener {

                //Using intent to pass the user to the next page
                val intent = Intent(this, Details_Screen::class.java)

                //Using intent to pass the information stored in the arrays to the next screen so that it can be accessed on the next screen
                intent.putStringArrayListExtra("songs",songNames)
                intent.putStringArrayListExtra("artist",artistArray)
                intent.putIntegerArrayListExtra("ratings",ratingArray)
                intent.putStringArrayListExtra("comments",commentsArray)

                //starting the next page
                startActivity(intent)

            }
        }

        //declaring a private function that will be used
        private fun AddSongs() {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_list, null)

            //Getting the textboxes from the dialog page
            val SongInput = dialogView.findViewById<EditText>(R.id.EditTextSong)
            val ArtistName = dialogView.findViewById<EditText>(R.id.editTextArtist)
            val RatingInput = dialogView.findViewById<EditText>(R.id.editTextRating)
            val CommentsInput = dialogView.findViewById<EditText>(R.id.editTextComments)


            //Creating and showing the dialog box
            val dialog = AlertDialog.Builder(this)
                .setTitle("Add Song Name")
                .setView(dialogView)
                .setPositiveButton("Add")
                { _, _ ->

                    // Retriving the information from the dialog boxes
                    val song = SongInput.text.toString()
                    val artist = ArtistName.text.toString()
                    val Rating = RatingInput.text.toString().toIntOrNull()
                    val Comments = CommentsInput.text.toString()

                    //Adding to the arrays if all the field are valid
                    if (song.isNotEmpty() && artist.isNotEmpty() && Rating != null && Rating in 1..5) {
                        songNames.add(song)
                        artistArray.add(artist)
                        ratingArray.add(Rating)
                        commentsArray.add(Comments)

                        //showing a confirmation message
                        Toast.makeText(this, "Song Added", Toast.LENGTH_SHORT).show()
                    } else {

                        //Showing a error message
                        Toast.makeText(this, "Please fill all the field correctly", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                //creating the cancel button on the dialog
                .setNegativeButton("Cancel", null)
                .show()
                .create()


        }
    }
