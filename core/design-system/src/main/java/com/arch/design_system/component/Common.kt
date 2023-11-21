package com.arch.design_system.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.icon.AppIcons
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.design_system.theme.extraSmallPadding

@ThemePreviews
@Composable
fun CommonComponents() {
    AppTheme {
        AppBackground {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(basePadding),
                    modifier = Modifier.padding(basePadding)
                ) {
                    Text(
                        text = "Pantalla muestra",
                        style = MaterialTheme.typography.displayMedium
                    )

                    Text(
                        text = "Sección principal",
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(basePadding),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AssistChip(
                            onClick = { },
                            leadingIcon = {
                                Icon(imageVector = AppIcons.Home, contentDescription = null)
                            },
                            label = { Text(text = "Inicio") }
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(basePadding),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        FilledIconToggleButton(checked = true, onCheckedChange = {}) {
                            Icon(imageVector = AppIcons.Check, contentDescription = null)
                        }
                        FilledIconToggleButton(checked = false, onCheckedChange = {}) {
                            Icon(imageVector = AppIcons.Add, contentDescription = null)
                        }
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(extraSmallPadding),
                            modifier = Modifier.padding(basePadding)
                        ) {
                            Text(
                                text = "Tarjeta principal",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "Esta tarjeta muestra información sobre la funcionalidad principal de esta aplicación.",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                TextButton(onClick = { }) {
                                    Text(text = "Aceptar")
                                }
                            }
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(basePadding, Alignment.End),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(onClick = { }) {
                            Text(text = "Más información")
                        }
                        Button(onClick = { }) {
                            Text(text = "Continuar")
                        }
                    }
                }
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(basePadding),
                    onClick = { }
                ) {
                    Icon(
                        imageVector = AppIcons.Add,
                        contentDescription = null
                    )
                }
            }
        }
    }
}