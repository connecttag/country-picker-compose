package org.connecttag.features.country.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.connecttag.country.model.Country

@Composable
public fun FlagView(
    modifier: Modifier = Modifier,
    country: Country,
) {
    Text(
        modifier = modifier,
        text = country.flagEmoji,
        style = MaterialTheme.typography.titleMedium,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun CountryPickerTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
    )
}

@Composable
public fun CountryPickerBasicTextField(
    modifier: Modifier = Modifier,
    state: CountryPickerState,
    onQueryChange: (String) -> Unit,
) {
    CountryPickerTextField(
        modifier = modifier,
        value = state.query,
        label = "Search country",
        onValueChange = onQueryChange,
    )
}

@Composable
public fun CountryDialog(
    modifier: Modifier = Modifier,
    title: String = "Select country",
    countries: List<Country>,
    onCountrySelected: (Country) -> Unit,
) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(countries, key = { country -> country.id }) { country ->
                    DropdownMenuItem(
                        text = {
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                FlagView(country = country)
                                Text(
                                    text = country.displayName,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        },
                        onClick = { onCountrySelected(country) },
                    )
                }
            }
        }
    }
}

@Composable
public fun CountryPicker(
    modifier: Modifier = Modifier,
    state: CountryPickerState,
    onOpenRequested: () -> Unit,
) {
    val text = state.selectedCountry?.displayName ?: "Select country"
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onOpenRequested)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        state.selectedCountry?.let { selectedCountry -> FlagView(country = selectedCountry) }
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview
@Composable
private fun CountryPickerPreview() {
    CountryPicker(state = CountryPickerDefaults.state(), onOpenRequested = {})
}
