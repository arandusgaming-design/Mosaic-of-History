package com.arandusgaming.mosaicofhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.arandusgaming.mosaicofhistory.data.GameDatabase
import com.arandusgaming.mosaicofhistory.data.HistoricalFigure
import com.arandusgaming.mosaicofhistory.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: GameDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = GameDatabase.getDatabase(this)

        initializeGameData()
        setupUI()
    }

    private fun initializeGameData() {
        lifecycleScope.launch {
            val count = database.historicalFigureDao().getFigureCount()
            if (count == 0) {
                loadInitialData()
            }
        }
    }

    private suspend fun loadInitialData() {
        val figures = generateHistoricalFigures()
        database.historicalFigureDao().insertFigures(figures)
    }

    private fun generateHistoricalFigures(): List<HistoricalFigure> {
        return listOf(
            // آسيا
            HistoricalFigure(
                id = 1,
                name = "Confucius",
                nameAr = "كونفوشيوس",
                continent = "Asia",
                level = 1,
                birthYear = "-551",
                deathYear = "-479",
                biography = "Chinese philosopher and politician whose teachings emphasize personal and governmental morality.",
                biographyAr = "الفيلسوف الصيني الذي ركز على الأخلاق الشخصية والحكومية.",
                achievements = "Founded Confucianism, influenced East Asian thought",
                achievementsAr = "أسس الكونفوشيوسية، أثر على الفكر الآسيوي الشرقي",
                funFact = "Never wrote down his teachings - all preserved by students",
                funFactAr = "لم يكتب تعاليمه - حفظها طلابه",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Confucius_2.png/440px-Confucius_2.png",
                difficulty = 1
            ),
            HistoricalFigure(
                id = 2,
                name = "Mahatma Gandhi",
                nameAr = "غاندي",
                continent = "Asia",
                level = 2,
                birthYear = "1869",
                deathYear = "1948",
                biography = "Indian lawyer, anti-colonial nationalist, and political ethicist who led India to independence.",
                biographyAr = "المحامي الهندي الذي قاد الهند نحو الاستقلال بمبادئ السلام.",
                achievements = "Led Indian independence movement using non-violence",
                achievementsAr = "قاد حركة الاستقلال الهندية باستخدام اللاعنف",
                funFact = "Wore homespun clothes as symbol of boycott against British",
                funFactAr = "كان يرتدي ملابس محلية الصنع رمزاً للمقاطعة البريطانية",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Photograph_of_Mahatma_Gandhi_1931_-_National_Portrait_Gallery_London.jpg/440px-Photograph_of_Mahatma_Gandhi_1931_-_National_Portrait_Gallery_London.jpg",
                difficulty = 1
            ),
            // أفريقيا
            HistoricalFigure(
                id = 3,
                name = "Nelson Mandela",
                nameAr = "نيلسون مانديلا",
                continent = "Africa",
                level = 3,
                birthYear = "1918",
                deathYear = "2013",
                biography = "South African anti-apartheid revolutionary who became the first Black president of South Africa.",
                biographyAr = "الثوري الجنوب أفريقي الذي أصبح أول رئيس أسود لجنوب أفريقيا.",
                achievements = "Ended apartheid system in South Africa",
                achievementsAr = "أنهى نظام الفصل العنصري في جنوب أفريقيا",
                funFact = "Spent 27 years in prison for fighting apartheid",
                funFactAr = "قضى 27 سنة في السجن لمحاربة الفصل العنصري",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Nelson_Mandela-2008_%28edit%29.jpg/440px-Nelson_Mandela-2008_%28edit%29.jpg",
                difficulty = 1
            ),
            // أوروبا
            HistoricalFigure(
                id = 4,
                name = "Leonardo da Vinci",
                nameAr = "ليوناردو دافنشي",
                continent = "Europe",
                level = 4,
                birthYear = "1452",
                deathYear = "1519",
                biography = "Italian polymath of the High Renaissance who was an inventor, anatomist, painter, and scientist.",
                biographyAr = "الفنان والعالم الإيطالي الذي برع في الفن والعلوم والاختراعات.",
                achievements = "Painted Mona Lisa, invented helicopter concept",
                achievementsAr = "رسم الموناليزا، اخترع مفهوم الهليكوبتر",
                funFact = "Wrote in mirror script (backwards writing) for secrecy",
                funFactAr = "كان يكتب بخط معكوس للسرية",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Vinci_Ausschnitt.jpg/440px-Vinci_Ausschnitt.jpg",
                difficulty = 1
            )
        )
    }

    private fun setupUI() {
        binding.startButton.setOnClickListener {
            // Navigate to ContinentsActivity
            startActivity(android.content.Intent(this, ContinentsActivity::class.java))
        }
    }
}

class ContinentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

class LevelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

class BiographyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
