package com.example.calculadora_de_gorjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {

    private lateinit var txtTotalConta : EditText
    private lateinit var txtPessoas : EditText
    private lateinit var skGorjeta : SeekBar

    private val formatador = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTotalConta = this.findViewById<EditText>(R.id.txtTotalConta)
        txtTotalConta.setOnFocusChangeListener(this)

        txtPessoas = this.findViewById<EditText>(R.id.txtPessoas)
        txtPessoas.setOnFocusChangeListener(this)

        skGorjeta = this.findViewById<SeekBar>(R.id.skGorjeta)
        skGorjeta.setOnSeekBarChangeListener(this)
    }

    private fun atualizaDadosConta() {

        if (txtTotalConta.text.toString().isNotEmpty()
            && txtPessoas.text.toString().isNotEmpty()) {

            val valorConta = txtTotalConta.text.toString().toDouble()
            val qtdPessoas = txtPessoas.text.toString().toInt()

            val lblValorFinalGorjeta = this.findViewById<TextView>(R.id.lblValorFinalGorjeta)
            val valorFinalGorjeta = valorConta * skGorjeta.progress / 100
            lblValorFinalGorjeta.setText(formatador.format(valorFinalGorjeta))

            val lblValorFinalConta = this.findViewById<TextView>(R.id.lblValorFinalConta)
            lblValorFinalConta.setText(formatador.format(valorConta + valorFinalGorjeta))
            val lblValorFinalPorPessoa = this.findViewById<TextView>(R.id.lblValorFinalPorPessoa)
            lblValorFinalPorPessoa.setText(formatador.format((valorConta + valorFinalGorjeta) / qtdPessoas))
        }

    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        this.atualizaDadosConta()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

        val lblPercentual = this.findViewById<TextView>(R.id.lblPercentual)
        lblPercentual.setText(skGorjeta.progress.toString() + "%")

        this.atualizaDadosConta()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}