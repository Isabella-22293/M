package uvg.isabella.myapplication.ui.theme

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uvg.isabella.myapplication.R
import java.util.prefs.Preferences

class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }


    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginMainApp(navController: NavController) {
    var Correo by remember { mutableStateOf("") }
    var Contraseña by remember { mutableStateOf("") }
    var MensajeError by remember { mutableStateOf("") }
    val Preferences = Preferences(LocalContext.current)
    val auth = Firebase.getInstance()
    val context = LocalContext.current

    //String Resources
    val EmailHint = context.getString(R.string.User_hint)
    val PasswordHint = context.getString(R.string.Password_hint)
    val LoginButton = context.getString(R.string.Login_button)
    val InvalidCredentials = context.getString(R.string.Invalid_credentials)
    val LoginFontSizeValue = context.getString(R.string.Login_title_font_size).toInt()
    val LoginFontSize = LoginFontSizeValue.sp
    val ErrorMessageFontSizeValue= context.getString(R.string.Error_message_font_size).toInt()
    val ErrorMessageFontSize= ErrorMessageFontSizeValue.sp
    var AuthenticationFail = null
    var ErrorMessage = AuthenticationFail

    //Dimension Resources
    val loginCardPadding = dimensionResource(R.dimen.Login_card_padding)

    //Color Resources
    val ColorFondo = colorResource(Color(fondo))
    val Titulo = colorResource(Color(titulo))
    val focusedIndicatorColor = colorResource(R.color.Focused_indicator_color)
    val unfocusedIndicatorColor = colorResource(R.color.unfocused_indicator_color)
    val buttonColor = colorResource(Color(general))

    Surface(color = ColorFondo, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // Blurred Background Image
            Image(
                painter = painterResource(id = R.drawable.dise_o_sin_t_tulo__8_),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,  // Fill the screen, might crop the image
                modifier = Modifier
                    .fillMaxSize()
                    .blur(
                        radiusX = 10.dp,
                        radiusY = 10.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(0.dp))
                    ),
            )
            Image(
                painter = painterResource(id = R.drawable._7699),
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(NavigationState.UserChoiceMainApp.route)
                    }
            )
            Card(
                modifier = Modifier.padding(loginCardPadding),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                //TextFields
                    TextField(
                        value = Correo,
                        onValueChange = { Correo= it },
                        label = { Text(EmailHint, Clarity = ) },
                        textStyle = TextStyle(color = Color.Black, ),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = focusedIndicatorColor,
                            unfocusedIndicatorColor = unfocusedIndicatorColor
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = Contraseña,
                        onValueChange = { Contraseña = it },
                        label = { Text(PasswordHint, Clarity=) },
                        textStyle = TextStyle(color = Color.Black, ),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = focusedIndicatorColor,
                            unfocusedIndicatorColor = unfocusedIndicatorColor
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (MensajeError.isNotEmpty()) {
                        Text(
                            text = MensajeError,
                            color = Color.Red,
                            fontSize = ErrorMessageFontSize,
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            auth.SignInButton(Correo, Contraseña)
                                .addOnCompleteListener{task ->
                                    if (task.isSuccessful){
                                        Login.d("Login", "signInWithEmail:success")
                                        Preferences.importPreferences(false)
                                        navController.navigate(NavigationState.MainScreen.route)
                                    } else{
                                        Login.w("Login", "signInWithEmail:failure", task.exception)
                                        MensajeError = "Authentication failed."
                                    }
                                }
                        },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                    ) {
                        Text(text = LoginButton, color = Color(general), )
                    }
                }
            }
        }
    }

fun Text(text: String, color: Int) {
    TODO("Not yet implemented")
}

fun Preferences(current: Context): Preferences {
    TODO("Not yet implemented")
}
}

fun Color(color: Color): Int {
    TODO("Not yet implemented")
}

// Funciones de validación

fun String.isValidUsername(): Boolean {
    return this.isNotEmpty() && this.length >= 6
}

fun String.isValidPassword(): Boolean {
    return this.isNotEmpty() && this.length >= 8
}



