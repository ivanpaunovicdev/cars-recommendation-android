package com.example.carsrecommendationapp.data.mapper

import com.example.carsrecommendationapp.data.dto.CarDto
import com.example.carsrecommendationapp.domain.Car

fun CarDto.toDomain(): Car {
    return Car(
        marka = marka,
        model = model,
        cena = cena,
        godiste = godiste,
        kilometraza = kilometraza,
        kubikaza = kubikaza,
        snagaKS = snagaKS,
        snagaKw = snagaKw,
        klima = klima,
        stanje = stanje,
        poreklo = poreklo,
        brojSedista = brojSedista,
        brojVrata = brojVrata,
        ostecenje = ostecenje,
        valuta = valuta,

        gorivo = gorivo,
        karoserija = karoserija,
        menjac = menjac,
        pogon = pogon,
        skor = skor
    )
}