class SplashActivity : AppCompatActivity() {

    private lateinit var progressText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Основной контейнер
        val rootLayout = FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // Центральный контейнер
        val centerLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setPadding(32, 32, 32, 32)
        }

        // Логотип
        val logo = ImageView(this).apply {
            setImageResource(R.drawable.ic_launcher_foreground)
        }

        // Анимация логотипа
        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = 1000
            fillAfter = true
        }
        logo.startAnimation(fadeIn)

        // Прогресс-бар
        val progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal).apply {
            layoutParams = LinearLayout.LayoutParams(400, ViewGroup.LayoutParams.WRAP_CONTENT)
            max = 100
        }

        // Текст с процентами загрузки
        progressText = TextView(this).apply {
            text = "0%"
            textSize = 18f
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 8)
        }

        // Факт дня
        // Факт дня в виде пузыря
        val factText = TextView(this).apply {
            text = getRandomFact()
            textSize = 18f // Увеличили размер текста
            gravity = Gravity.CENTER
            setTextColor(Color.BLACK) // Белый текст
            setPadding(32, 16, 32, 16) // Отступы внутри пузыря
            background = GradientDrawable().apply {
                setColor(Color.parseColor("#ebebeb")) // Тёмно-серый фон
                cornerRadius = 50f // Закруглённые углы
            }
        }

        // Контейнер для нижних элементов
        val bottomLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM
            )
            setPadding(0, 32, 0, 32)
        }

        // Название приложения
        val appTitle = TextView(this).apply {
            text = "RuGPT"
            textSize = 24f
            gravity = Gravity.CENTER
        }

        // Авторство
        val authorText = TextView(this).apply {
            text = "© 2025"
            textSize = 18f
            gravity = Gravity.CENTER
        }

        // Добавляем элементы в центральный макет
        centerLayout.addView(logo)
        centerLayout.addView(progressBar)
        centerLayout.addView(progressText)
        centerLayout.addView(factText) // <-- Факт дня теперь сразу после прогресса!

        // Добавляем элементы в нижний макет
        bottomLayout.addView(appTitle)
        bottomLayout.addView(authorText)

        // Добавляем всё в `rootLayout`
        rootLayout.addView(centerLayout)
        rootLayout.addView(bottomLayout)

        setContentView(rootLayout)

        // Запускаем загрузку
        startLoading(progressBar)
    }
