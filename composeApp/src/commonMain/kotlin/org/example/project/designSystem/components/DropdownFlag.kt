package org.example.project.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

data class Country(
    val name: String,
    val code: String,
    val phoneCode: String,
    val flagEmoji: String
)

// Sample countries list
val countriesList = listOf(
    Country("United States", "US", "+1", "🇺🇸"),
    Country("Egypt", "EG", "+20", "🇪🇬"),
    Country("United Kingdom", "GB", "+44", "🇬🇧"),
    Country("Germany", "DE", "+49", "🇩🇪"),
    Country("France", "FR", "+33", "🇫🇷"),
    Country("Saudi Arabia", "SA", "+966", "🇸🇦"),
    Country("United Arab Emirates", "AE", "+971", "🇦🇪"),
    Country("India", "IN", "+91", "🇮🇳"),
    Country("China", "CN", "+86", "🇨🇳"),
    Country("Japan", "JP", "+81", "🇯🇵"),
    Country("Brazil", "BR", "+55", "🇧🇷"),
    Country("Canada", "CA", "+1", "🇨🇦"),
    Country("Australia", "AU", "+61", "🇦🇺"),
    Country("Spain", "ES", "+34", "🇪🇸"),
    Country("Italy", "IT", "+39", "🇮🇹"),
    // Add more countries as needed
)


@Composable
fun CountryCodeSelector(
    selectedCountry: Country,
    onCountrySelected: (Country) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var showDropdown by remember { mutableStateOf(false) }

    // Country selector button
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (enabled) Color.Transparent else Color.Gray.copy(alpha = 0.1f))
            .clickable(enabled = enabled) { showDropdown = true }
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Flag emoji
            Text(
                text = selectedCountry.flagEmoji,
                fontSize = 24.sp
            )

            // Phone code
            Text(
                text = selectedCountry.phoneCode,
                color = if (enabled) AppTheme.craftoColors.brand.secondary else Color.Gray,
                style = AppTheme.textStyle.body.smallRegular
            )

        }
    }

    // Country selector dialog
    if (showDropdown) {
        CountryPickerDialog(
            selectedCountry = selectedCountry,
            onCountrySelected = { country ->
                onCountrySelected(country)
                showDropdown = false
            },
            onDismiss = { showDropdown = false }
        )
    }
}

@Composable
fun CountryPickerDialog(
    selectedCountry: Country,
    onCountrySelected: (Country) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp)
            ) {
                // Header
                Text(
                    text = "Select Country",
                    modifier = Modifier.padding(16.dp)
                )

                Divider()

                // Countries list
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(countriesList) { country ->
                        CountryItem(
                            country = country,
                            isSelected = country == selectedCountry,
                            onClick = { onCountrySelected(country) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CountryItem(
    country: Country,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                if (isSelected) AppTheme.craftoColors.brand.primary.copy(alpha = 0.1f)
                else Color.Transparent
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Flag
        Text(
            text = country.flagEmoji,
            fontSize = 24.sp
        )

        // Country name
        Text(
            text = country.name,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            style = AppTheme.textStyle.body.smallRegular,
            color = if (isSelected) AppTheme.craftoColors.brand.primary
            else AppTheme.craftoColors.brand.tertiary
        )

        // Phone code
        Text(
            text = country.phoneCode,
            style = AppTheme.textStyle.body.largeMedium,
            color = if (isSelected) AppTheme.craftoColors.brand.primary
            else AppTheme.craftoColors.brand.tertiary
        )
    }
}


@Composable
fun TestScreen() {
    var selectedCountry by remember { mutableStateOf(countriesList[1]) }

    AppTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(AppTheme.craftoColors.background.screen),
            contentAlignment = Alignment.Center
        ) {
            CountryCodeSelector(
                selectedCountry = selectedCountry,
                onCountrySelected = { selectedCountry = it }
            )
        }
    }
}

@Preview()
@Composable
fun DialogPreview() {
    AppTheme {
        Box(Modifier.fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)) {
            CountryCodeSelector(
                selectedCountry = countriesList[0],
                onCountrySelected = {}
            )
        }
    }
}

