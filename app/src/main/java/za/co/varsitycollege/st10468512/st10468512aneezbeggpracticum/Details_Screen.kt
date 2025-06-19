package za.co.varsitycollege.st10468512.st10468512aneezbeggpracticum

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Details_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details_screen)
        // Getting the views from the layout
        val home=findViewById<Button>(R.id.btnHome)
        val Average=findViewById<Button>(R.id.btnAverage)
        val Display=findViewById<Button>(R.id.btnDisplay)
        val DisplayText=findViewById<TextView>(R.id.DisplayText)
        val averageText=findViewById<TextView>(R.id.AverageText)


        //Retirving the data that was passed through from the main page
        val songsName=intent.getStringArrayListExtra("songs")?: arrayListOf()
        val artistName=intent.getStringArrayListExtra("artist")?: arrayListOf()
        val ratingscore=intent.getIntegerArrayListExtra("ratings")?: arrayListOf()
        val CommentsNotes=intent.getStringArrayListExtra("comments")?: arrayListOf()




        home.setOnClickListener {
            //Allows the user to return to the main page
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        //creates the command of what happens once the button is clicked
        Display.setOnClickListener {

            //Checks if the songs name array is empty and display a error message if it is
            if (songsName.isEmpty()){

                DisplayText.text="Theres is no songs to display"
                return@setOnClickListener
            }

            //Build the full playlist as a single string
            val Displaylist =StringBuilder()


            for(i in songsName.indices)
            {

                //writes the information to the list
                Displaylist.append( " ${songsName[i]} -${artistName[i]} \n")
                Displaylist.append( " Rating:${ratingscore[i]} \n")
                Displaylist.append( " Comments: ${CommentsNotes[i] }\n  \n")

                //Displays the list in the textbox
                DisplayText.text=Displaylist.toString()

            }
        }


        //creates the command of what happens once the button is clicked
        Average.setOnClickListener {

            var total=0

            //check if the average value is empty and displays a messages
            if (ratingscore.isEmpty()){
                Toast.makeText(this,"There is no average",Toast.LENGTH_SHORT).show()
                averageText.text=""
                return@setOnClickListener
            }

            //calcualtes the average and displays it
            for (rating in ratingscore) {
                total += rating
            }
            val average = if (ratingscore.isNotEmpty()) {
                total.toDouble() / ratingscore.size
            }
            else{ 0 }
            averageText.text="Average rating is : %.2f".format(average)

        }
    }
}
        }
    }
}