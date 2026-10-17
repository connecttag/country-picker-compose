package org.connecttag.features.country.picker

import androidx.compose.runtime.Immutable
import org.connecttag.country.model.Country
import org.connecttag.country.search.CountryRepository

@Immutable
public data class CountryPickerState(
    val selectedCountry: Country? = null,
    val query: String = "",
    val isExpanded: Boolean = false,
    val countries: List<Country> = emptyList(),
)

public object CountryPickerDefaults {
    public fun state(
        selectedCountry: Country? = null,
        repository: CountryRepository = CountryRepository(),
    ): CountryPickerState {
        return CountryPickerState(
            selectedCountry = selectedCountry,
            countries = repository.all(),
        )
    }
}
