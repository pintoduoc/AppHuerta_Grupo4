package com.example.apphuerta_grupo4

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests de registro de usuario: escenario exitoso y caso negativo de correo inválido.
 * Se navega a la pantalla Registro mediante la UI (Drawer) para asegurar que los nodos existan.
 */
@RunWith(AndroidJUnit4::class)
class RegistrarUsuarioTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private fun navegarARegistroMedianteUI() {
        // Esperar a que el botón del menú aparezca tras el Splash (timeout 5s)
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag("boton_menu").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("boton_menu").performClick()
        composeRule.waitUntil(timeoutMillis = 2_000) {
            composeRule.onAllNodesWithText("Registro").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Registro").performClick()
    }

    @Test
    fun registrarUsuario_exitosamente() {
        navegarARegistroMedianteUI()

        // Llenar campos
        composeRule.onNodeWithTag("campo_nombres").performTextInput("Benjamín Alfonso")
        composeRule.onNodeWithTag("campo_apellidos").performTextInput("Pinto Ramirez")
        composeRule.onNodeWithTag("campo_correo").performTextInput("be.pintor@duocuc.cl")
        composeRule.onNodeWithTag("campo_clave").performTextInput("BenjaminPintoRamirez1")
        composeRule.onNodeWithTag("campo_direccion").performTextInput("Antonio Varas 666, Providencia")
        // Click en registrar
        composeRule.onNodeWithTag("boton_registrar").performClick()
        // Esperar navegación a resumen
        composeRule.waitUntil(timeoutMillis = 3_000) {
            composeRule.onAllNodesWithTag("pagina_resumen_cuenta").fetchSemanticsNodes().isNotEmpty()
        }
        // Verificamos datos en resumen
        composeRule.onNodeWithTag("texto_resumen").assertIsDisplayed()
        composeRule.onNodeWithTag("resumen_nombres").assertIsDisplayed()
        composeRule.onNodeWithTag("resumen_correo").assertIsDisplayed()
    }

    @Test
    fun registrarUsuario_correoInvalido_muestraError() {
        navegarARegistroMedianteUI()

        // Llenar campos con correo inválido
        composeRule.onNodeWithTag("campo_nombres").performTextInput("Benjamín Alfonso")
        composeRule.onNodeWithTag("campo_apellidos").performTextInput("Pinto Ramirez")
        composeRule.onNodeWithTag("campo_correo").performTextInput("be.pintor@duoc")
        composeRule.onNodeWithTag("campo_clave").performTextInput("BenjaminPintoRamirez1")
        composeRule.onNodeWithTag("campo_direccion").performTextInput("Antonio Varas 666, Providencia")
        // Intentar registrar
        composeRule.onNodeWithTag("boton_registrar").performClick()
        // Verificar error
        composeRule.onNodeWithText("Correo Invalido").assertIsDisplayed()
    }

    @Test
    fun registrarUsuario_contrasenaCorta_muestraError() {
        navegarARegistroMedianteUI()
        composeRule.onNodeWithTag("campo_nombres").performTextInput("Benjamín Alfonso")
        composeRule.onNodeWithTag("campo_apellidos").performTextInput("Pinto Ramirez")
        composeRule.onNodeWithTag("campo_correo").performTextInput("be.pintor@duocuc.cl")
        composeRule.onNodeWithTag("campo_clave").performTextInput("12345") // 5 chars
        composeRule.onNodeWithTag("campo_direccion").performTextInput("Antonio Varas 666, Providencia")
        composeRule.onNodeWithTag("boton_registrar").performClick()
        // Esperar a que el mensaje de error de clave aparezca
        composeRule.waitUntil(timeoutMillis = 2_000) {
            composeRule.onAllNodesWithTag("error_clave", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("error_clave", useUnmergedTree = true).assertIsDisplayed()
        // No debe navegar a resumen
        composeRule.waitUntil(timeoutMillis = 1_000) {
            composeRule.onAllNodesWithTag("pagina_resumen_cuenta").fetchSemanticsNodes().isEmpty()
        }
    }

    @Test
    fun registrarUsuario_nombresVacio_muestraError() {
        navegarARegistroMedianteUI()
        // Nombres vacío
        composeRule.onNodeWithTag("campo_apellidos").performTextInput("Pinto Ramirez")
        composeRule.onNodeWithTag("campo_correo").performTextInput("be.pintor@duocuc.cl")
        composeRule.onNodeWithTag("campo_clave").performTextInput("BenjaminPintoRamirez1")
        composeRule.onNodeWithTag("campo_direccion").performTextInput("Antonio Varas 666, Providencia")
        composeRule.onNodeWithTag("boton_registrar").performClick()
        // Esperar a que el mensaje de error de nombres aparezca
        composeRule.waitUntil(timeoutMillis = 2_000) {
            composeRule.onAllNodesWithTag("error_nombres", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("error_nombres", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun registrarUsuario_apellidosVacio_muestraError() {
        navegarARegistroMedianteUI()
        composeRule.onNodeWithTag("campo_nombres").performTextInput("Benjamín Alfonso")
        // Apellidos vacío
        composeRule.onNodeWithTag("campo_correo").performTextInput("be.pintor@duocuc.cl")
        composeRule.onNodeWithTag("campo_clave").performTextInput("BenjaminPintoRamirez1")
        composeRule.onNodeWithTag("campo_direccion").performTextInput("Antonio Varas 666, Providencia")
        composeRule.onNodeWithTag("boton_registrar").performClick()
        // Esperar a que el mensaje de error de apellidos aparezca
        composeRule.waitUntil(timeoutMillis = 2_000) {
            composeRule.onAllNodesWithTag("error_apellidos", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("error_apellidos", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun registrarUsuario_direccionVacia_muestraError() {
        navegarARegistroMedianteUI()
        composeRule.onNodeWithTag("campo_nombres").performTextInput("Benjamín Alfonso")
        composeRule.onNodeWithTag("campo_apellidos").performTextInput("Pinto Ramirez")
        composeRule.onNodeWithTag("campo_correo").performTextInput("be.pintor@duocuc.cl")
        composeRule.onNodeWithTag("campo_clave").performTextInput("BenjaminPintoRamirez1")
        // Dirección vacía
        composeRule.onNodeWithTag("boton_registrar").performClick()
        // Esperar a que el mensaje de error de dirección aparezca
        composeRule.waitUntil(timeoutMillis = 2_000) {
            composeRule.onAllNodesWithTag("error_direccion", useUnmergedTree = true).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("error_direccion", useUnmergedTree = true).assertIsDisplayed()
    }
}