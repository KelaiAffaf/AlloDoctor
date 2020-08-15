package com.example.e_sant.activities.activities.models

import com.example.e_sant.activities.activities.enumerations.SymptomType

data class Symptom(
    val icon: Int,
    val name: SymptomType,
    var isChecked: Boolean = false
)