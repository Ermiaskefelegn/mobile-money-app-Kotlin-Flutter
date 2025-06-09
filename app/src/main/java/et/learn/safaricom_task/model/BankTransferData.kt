

data class BankTransferData(
    val bankName: String,
    val accountNumber: String,
    val amount: Double,
    val description: String
)

data class BankTransferUiState(
    val selectedBank: String = "Select bank",
    val accountNumber: String = "",
    val amount: String = "",
    val description: String = "",
    val accountNumberError: String? = null,
    val amountError: String? = null,
    val isFormValid: Boolean = false
)
