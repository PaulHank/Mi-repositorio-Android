package com.liceolapaz.dam.tresenrayapae

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TresEnRaya : View {
        companion object{
                const val VACIO = 0
                const val FICHA_O = 1
                const val FICHA_X = 2
                const val EMPATE = 0
                const val NO_GANADOR = -1
        }

        private var ganador = NO_GANADOR
        private var casillasRestantes = 0
        private val tablero = Array(3){Array(3){0}}
        private var fichaActiva = FICHA_X
        private var xColor = Color.RED
        private var oColor = Color.BLUE
        private val pBorde = Paint().apply {
                style = Paint.Style.STROKE
                color = Color.BLACK
                strokeWidth = 4f
        }

        private val pMarcaO = Paint().apply {
                style = Paint.Style.STROKE
                strokeWidth = 10f
        }

        private val pMarcaX = Paint().apply {
                style = Paint.Style.STROKE
                strokeWidth = 10f
        }

        private lateinit var listener : OnCasillaSeleccionadaListener

        constructor(ctx: Context) : super(ctx)

        constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
                context.theme.obtainStyledAttributes(
                        attrs,
                        R.styleable.TresEnRaya, 0, 0).apply {
                        try {
                                oColor = getColor(R.styleable.TresEnRaya_ocolor, Color.BLUE)
                                xColor = getColor(R.styleable.TresEnRaya_xcolor, Color.RED)
                        } finally {
                                recycle()
                        }
                }
        }

        constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr) {
                context.theme.obtainStyledAttributes(
                        attrs,
                        R.styleable.TresEnRaya, 0, 0).apply {
                        try {
                                oColor = getColor(R.styleable.TresEnRaya_ocolor, Color.BLUE)
                                xColor = getColor(R.styleable.TresEnRaya_xcolor, Color.RED)
                        } finally {
                                recycle()
                        }
                }
        }

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
                var ancho = calcularAncho(widthMeasureSpec)
                var alto = calcularAlto(heightMeasureSpec)

                if(ancho < alto) { alto = ancho }
                else { ancho = alto }
                setMeasuredDimension(ancho, alto)
        }

        private fun calcularAlto(limitesSpec: Int): Int {
                var res = 100
                val modo = MeasureSpec.getMode(limitesSpec)
                val limite = MeasureSpec.getSize(limitesSpec)

                if (modo == MeasureSpec.AT_MOST) { res = limite }
                else if (modo == MeasureSpec.EXACTLY) { res = limite }

                return res
        }

        private fun calcularAncho(limitesSpec: Int): Int {
                var res = 100
                val modo = MeasureSpec.getMode(limitesSpec)
                val limite = MeasureSpec.getSize(limitesSpec)

                if (modo == MeasureSpec.AT_MOST) { res = limite }
                else if (modo == MeasureSpec.EXACTLY) { res = limite }
                return res
        }

        fun limpiarTablero() {
                for(i in 0..2){
                        for (j in 0..2){
                                setCasilla(i, j, VACIO)
                        }
                }
                ganador = NO_GANADOR
                casillasRestantes = 0
                this.invalidate()
        }

        private fun setCasilla(fil: Int, col: Int, valor: Int) { tablero[fil][col] = valor}

        private fun getCasilla(fil: Int, col: Int) : Int { return tablero[fil][col] }

        private fun cambioFicha() {
                fichaActiva = if (fichaActiva == FICHA_O)   FICHA_X else FICHA_O
        }

        override fun onDraw(canvas: Canvas?) {
                val alto = measuredHeight
                val ancho = measuredWidth

                canvas!!.drawLine((ancho / 3).toFloat(), 0f, (ancho / 3).toFloat(), alto.toFloat(), pBorde)
                canvas.drawLine((2 * ancho / 3).toFloat(), 0f, (2 * ancho / 3).toFloat(), alto.toFloat(), pBorde)
                canvas.drawLine(0f, (alto / 3).toFloat(), ancho.toFloat(), (alto / 3).toFloat(), pBorde)
                canvas.drawLine(0f, (2 * alto / 3).toFloat(), ancho.toFloat(), (2 * alto / 3).toFloat(), pBorde)
                canvas.drawRect(0f, 0f, ancho.toFloat(), alto.toFloat(), pBorde)

                pMarcaO.color = oColor
                pMarcaX.color = xColor

                for (fil in 0..2) {
                        for (col in 0..2) {
                                if (getCasilla(fil, col) == FICHA_X) {
                                        canvas.drawLine(
                                                col * (ancho / 3) + ancho / 3 * 0.1f,
                                                fil * (alto / 3) + alto / 3 * 0.1f,
                                                col * (ancho / 3) + ancho / 3 * 0.9f,
                                                fil * (alto / 3) + alto / 3 * 0.9f,
                                                pMarcaX
                                        )
                                        canvas.drawLine(
                                                col * (ancho / 3) + ancho / 3 * 0.1f,
                                                fil * (alto / 3) + alto / 3 * 0.9f,
                                                col * (ancho / 3) + ancho / 3 * 0.9f,
                                                fil * (alto / 3) + alto / 3 * 0.1f,
                                                pMarcaX
                                        )
                                } else if (getCasilla(fil, col) == FICHA_O) {
                                        canvas.drawCircle(
                                                (col * (ancho / 3) + ancho / 6).toFloat(),
                                                (fil * (alto / 3) + alto / 6).toFloat(),
                                                ancho / 6 * 0.8f,
                                                pMarcaO
                                        )
                                }
                        }
                }
        }

        //Aqui es donde se ponen las fichas.
        override fun onTouchEvent(event: MotionEvent?): Boolean {
                val fil = (event!!.y / (measuredHeight / 3)).toInt()
                val col = (event.x / (measuredWidth / 3)).toInt()
                // Esta pequeña funcion hace que si la casilla se llena, no se sobreescriba.
                // Incluso si se clica otra vez y ya esta llena, su contenido no se alterara, y su posicion no sera recogida tampoco.
                if (getCasilla(fil,col) == VACIO) {
                        setCasilla(fil, col, fichaActiva)
                        comprobarGanador()
                        listener.onCasillaSeleccionada(fil, col)
                        cambioFicha()
                        this.invalidate()
                        return super.onTouchEvent(event)
                } else {

                }
                        this.invalidate()
                return super.onTouchEvent(event)
        }

        fun comprobarGanador() {
                if (((getCasilla(0,0) == FICHA_O) && (getCasilla(1,0) == FICHA_O))) {

                }
        }

        fun setOnCasillaSeleccionadaListener(seleccion: (Int, Int) -> Unit) {
                listener = object:OnCasillaSeleccionadaListener {
                        override fun onCasillaSeleccionada(fila: Int, columna: Int) {
                                seleccion(fila, columna)
                        }
                }
        }

        fun getGanador(): Int {
        return ganador
        }
}