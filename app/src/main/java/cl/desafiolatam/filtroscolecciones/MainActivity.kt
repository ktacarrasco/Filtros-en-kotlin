package cl.desafiolatam.filtroscolecciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val numbersList = listOf("one", "five", "two", "three", "four")
    private val instanceList = listOf(null, 1, "two", 3.0, "four")
    private val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    private val unsortedList = listOf(7, 8, 5, 3, 2, 4)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFilterListeners()

        initTestingPredicatesListeners()

        initMapFilterListeners()

        initSortingListeners()
    }


    private fun initFilterListeners() {
        // Filtro por predicado
        btFilterByPredicate.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.filter { it.length > 3 },
                    "list.filter { it.length > 3 }",
                    "Devuelve la colección de elementos que cumplen la condición. Retorna los elementos con mas de 3 caracteres de largo"
            )
        }


        // Filtro por índice
        btFilterIndexed.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.filterIndexed { index, s -> (index != 0) && (s.length < 5) },
                    "list.filterIndexed { index, s -> (index != 0) && (s.length < 5) }",
                    "Recibe como parámetro el índice y el elemento. Luego filtra que no sean en el primer elemento (index != 0) y que tengan menos de 5 caracteres"
            )
        }

        //filterNot()
        btFilterNot.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.filterNot { it.length <= 3 },
                    "list.filterNot { it.length <= 3 }",
                    "Devuelve una lista de elementos para los cuales el predicado es falso, por ejemplo, los elementos que tienen mas de 4 caracteres (NO tienen al menos 3 caracteres)"
            )
        }

        //filterInstance()
        btFilterInstance.setOnClickListener {
            showResults(
                    instanceList,
                    instanceList.filterIsInstance<String>(),
                    "list.filterIsInstance<String>()",
                    "Retorna una colección de elementos de un tipo dado específico, en este caso String"
            )
        }

        //filterNotNull
        btFilterNotNull.setOnClickListener {
            showResults(
                    instanceList,
                    instanceList.filterNotNull(),
                    "list.filterNotNull<String>()",
                    "Retorna con todos los elementos no nulos"
            )
        }

        //Partitioning
        btPartitioning.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.partition { it.length > 3 },
                    "numbersList.partition { it.length > 3 }",
                    "Divide la lista en 2 aplicando el predicado. La primera lista tiene los elementos que cumplen la condición y la segunda los elementos restantes"
            )
        }
    }

    /************************************************************************
     ********************** Probando predicados *****************************
     ************************************************************************/
    private fun initTestingPredicatesListeners() {

        // Any
        btAny.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.any { it.endsWith("e") },
                    "list.any { it.endsWith(\"e\") }",
                    "Retorna true si al menos uno de los elementos coincide con el predicado"
            )
        }

        // None
        btNone.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.none { it.endsWith("a") },
                    "list.none { it.endsWith(\"a\") }",
                    "Retorna true si ninguno de los elementos coincide con el predicado"
            )
        }

        // All
        btAll.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.all { it.endsWith("e") },
                    "list.all { it.endsWith(\"e\") }",
                    "Retorna true si todos los elementos coinciden con el predicado"
            )
        }
    }

    /************************************************************************
     *********************** Filtros para mapas *****************************
     ************************************************************************/
    private fun initMapFilterListeners() {

        btMapFilterByPredicate.setOnClickListener {
            showResultsm(
                    numbersMap,
                    numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10 },
                    "numbersMap.filter { (key, value) -> key.endsWith(\"1\") && value > 10 }",
                    "Recibe 2 parametros (key, value) y verifica que la key no termine con el valor 1. Además evalua el valor como mayor que 10."
            )
        }

        btMapFilterByKey.setOnClickListener {
            showResultsm(
                    numbersMap,
                    numbersMap.filterKeys { it.length > 3 },
                    "numbersMap.filterKeys { it.length > 3 }",
                    "Aplica el filtro solo a las claves verificando que su contenido tenga un largo mayor a 3"
            )
        }

        btMapFilterByValue.setOnClickListener {
            showResultsm(
                    numbersMap,
                    numbersMap.filterValues { it < 10 },
                    "numbersMap.filterValues { it < 10 }",
                    "Aplica el filtro solo a los valores verificando que sea menor a 10"
            )
        }
    }

    private fun initSortingListeners() {
        btNaturalSort.setOnClickListener {
            showResults(unsortedList,
                    unsortedList.sorted(),
                    "unsortedList.sorted()",
                    "Utiliza un orden natural de los elementos que componen la lista para ordenarla")
        }

        btCustomSort.setOnClickListener {
            showResults(numbersList,
                    numbersList.sortedBy { it.length },
                    "numbersList.sortedBy { it.length }",
                    "Se puede indicar alguna forma particular de cómo ordenar los elementos, por ejemplo, por el largo de su contenido")
        }

        btReverse.setOnClickListener {
            showResults(numbersList,
                    numbersList.reversed(),
                    "numbersList.reversed()",
                    "La lista entrega los elementos en orden inverso del que fueron creados")
        }
    }

    private fun showResults(
            inputList: List<Any?>,
            listResult: Any,
            instruction: String,
            message: String
    ) {
        tvInputList.text = "Entrada $inputList"
        tvInputInstrucction.text = instruction
        tvResultList.text = "Resultado: $listResult"
        tvInputMessage.text = message
    }

    private fun showResultsm(
            inputMap: Map<String, Any?>,
            mapResult: Any,
            instruction: String,
            message: String
    ) {
        tvInputList.text = "Entrada $inputMap"
        tvInputInstrucction.text = instruction
        tvResultList.text = "Resultado: $mapResult"
        tvInputMessage.text = message
    }
}
