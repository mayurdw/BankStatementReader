package com.mayurdw.bankstatementreader.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.mayurdw.bankstatementreader.R
import com.mayurdw.bankstatementreader.data.Repository
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {

    @Inject lateinit var repository: Repository
    private lateinit var previewRequest: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(toolbar, navHostFragment.navController)

        previewRequest = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {

                val path = it.data!!.data
                Timber.d("Path = ${path?.path}, lastPathSegment = ${path?.lastPathSegment}")

                path?.let { filePath ->
                    val inputStream = this@MainActivity.contentResolver.openInputStream(filePath)
                    repository.readFile(inputStream = inputStream!! )
                }

//                if (path != null) {
//                    Timber.d(path)
//                    repository.readFile(filePath = path.)
//                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.itemId == R.id.add_file) {
            val intent: Intent
            val chooseFile = Intent(Intent.ACTION_GET_CONTENT)
            chooseFile.addCategory(Intent.CATEGORY_OPENABLE)
            chooseFile.type = "text/*"
            intent = Intent.createChooser(chooseFile, "Choose a file")

            previewRequest.launch(intent)
        }
        return item.onNavDestinationSelected(
            findNavController(R.id.nav_host_fragment)
        ) || super.onOptionsItemSelected(item)
    }
}