package vcmsa.projects.demoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find UI components by their IDs
        val edtBillAmt = findViewById<EditText>(R.id.edtBillAmt)
        val edtTip = findViewById<EditText>(R.id.edtTipPercentage)
        val edtNumPeople = findViewById<EditText>(R.id.edtNumPeople)
        val btnCalculateTip = findViewById<Button>(R.id.btnCalculateTip)
        val btnSplitBill = findViewById<Button>(R.id.btnSplitBill)
        val txtTip = findViewById<TextView>(R.id.txtTip)
        val txtTotalBill = findViewById<TextView>(R.id.txtTotalBill)

        // Set an OnClickListener for the "Calculate Total Tip" button
        btnCalculateTip.setOnClickListener {
            val billAmt = edtBillAmt.text.toString().toDoubleOrNull() ?: 0.0
            val tipPercent = edtTip.text.toString().toDoubleOrNull() ?: 0.0

            // Validate inputs
            if (billAmt < 0 || tipPercent < 0) {
                txtTip.text = "Invalid input. Please enter positive values."
                return@setOnClickListener
            }

            // Calculate the total tip
            val tipAmt = billAmt * (tipPercent / 100)

            // Display the total tip
            txtTip.text = "Total Tip: R${String.format("%.2f", tipAmt)}"
        }

        // Set an OnClickListener for the "Split Bill" button
        btnSplitBill.setOnClickListener {
            val billAmt = edtBillAmt.text.toString().toDoubleOrNull() ?: 0.0
            val tipPercent = edtTip.text.toString().toDoubleOrNull() ?: 0.0
            val numPeople = edtNumPeople.text.toString().toIntOrNull() ?: 1

            // Validate inputs
            if (billAmt < 0 || numPeople <= 0 || tipPercent < 0) {
                txtTotalBill.text = "Invalid input. Please enter positive values."
                return@setOnClickListener
            }

            // Calculate the total tip
            val tipAmt = billAmt * (tipPercent / 100)

            // Split the bill and tip equally
            val totalPerPerson = (billAmt + tipAmt) / numPeople

            // Display the total per person (including their share of the bill and the tip)
            txtTotalBill.text = "Total Bill Per Person (including tip): R${String.format("%.2f", totalPerPerson)}"
        }
    }
}
