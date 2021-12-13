package com.justai.jaicf.template.data_base

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser

/**
 * Read and parse words from .csv file
 */
class WordBase {
    fun readWords(): List<String> {
        val wordsList = mutableListOf<String>()
        val baseContent: String? = this::class.java.getResource("/hangmanGameData.csv")?.readText()
        val csvParser = CSVParser.parse(baseContent, CSVFormat.newFormat(';'))
        for (record in csvParser) {
            wordsList.add(record[1])
        }
        return wordsList
    }
}