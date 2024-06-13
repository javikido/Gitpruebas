package com.jfg.gitpruebas.workProyect.proyec1.login.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jfg.gitpruebas.R
import com.jfg.gitpruebas.loginProyect.Body
import com.jfg.gitpruebas.loginProyect.Email
import com.jfg.gitpruebas.loginProyect.Footer
import com.jfg.gitpruebas.loginProyect.ForgotPassword
import com.jfg.gitpruebas.loginProyect.Header
import com.jfg.gitpruebas.loginProyect.ImageLogo
import com.jfg.gitpruebas.loginProyect.LoginButton
import com.jfg.gitpruebas.loginProyect.LoginDivider
import com.jfg.gitpruebas.loginProyect.Password
import com.jfg.gitpruebas.loginProyect.SignUp
import com.jfg.gitpruebas.loginProyect.SocialLogin
import com.jfg.gitpruebas.loginProyect.presentation.LoginViewModel


@Composable
fun WorkScreen(loginViewModel: WorkViewModel) {

    Box(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
    ) {

        val isLoading: Boolean by loginViewModel.isLoading.observeAsState(initial = false)

        if (isLoading) {
            Box(
                    Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }
        } else {
            Header(Modifier.align(Alignment.TopEnd))
            Body2(Modifier.align(Alignment.Center), loginViewModel)
            Footer(Modifier.align(Alignment.BottomCenter))
        }
    }


}

@Composable
fun Footer2(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
                Modifier
                    .background(Color(0xFFF9F9F9))
                    .height(1.dp)
                    .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun SignUp2() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
                text = "Don't have an account?", fontSize = 12.sp, color = Color(0xFFB5B5B5)
        )
        Text(
                text = "Sign up.",
                Modifier.padding(horizontal = 8.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4EA8E9),
        )
    }
}

@Composable
fun Body2(modifier: Modifier, loginViewModel: WorkViewModel) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)

    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(email) {
            loginViewModel.onLoginChanged(email = it, password = password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            loginViewModel.onLoginChanged(email = email, password = it)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton2(isLoginEnable, loginViewModel)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun SocialLogin2() {
    Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
    ) {
        Image(
                painter = painterResource(id = R.drawable.fb),
                contentDescription = "Social login fb",
                modifier = Modifier.size(16.dp)
        )
        Text(
                text = "Continue as Aris",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun LoginDivider2() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
                Modifier
                    .background(Color(0xFFF9F9F9))
                    .height(1.dp)
                    .weight(1f)
        )
        Text(
                text = "OR",
                modifier = Modifier.padding(horizontal = 18.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB5B5B5)
        )
        Divider(
                Modifier
                    .background(Color(0xFFF9F9F9))
                    .height(1.dp)
                    .weight(1f)
        )
    }
}

@Composable
fun LoginButton2(loginEnable: Boolean, loginViewModel: WorkViewModel) {
    Button(
            onClick = { loginViewModel.onLoginSelected() },
            enabled = loginEnable,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4EA8E9),
                    disabledContainerColor = Color(0xFF78C8F9),
                    contentColor = Color.White,
                    disabledContentColor = Color.White
            )
    ) {
        Text(text = "Log In")
    }
}


@Composable
fun ForgotPassword2(modifier: Modifier) {
    Text(
            text = "Forgot password?",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9),
            modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password2(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
            value = password,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Password") },
            colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFFB2B2B2),
                    containerColor = Color(0xFFFAFAFA),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val imagen = if (passwordVisibility) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = imagen, contentDescription = "show password")
                }
            },
            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email2(email: String, onTextChanged: (String) -> Unit) {
    TextField(
            value = email,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Email") },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.textFieldColors(
                    textColor = Color(0xFFB2B2B2),
                    containerColor = Color(0xFFFAFAFA),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
            )
    )
}

@Composable
fun ImageLogo2(modifier: Modifier) {
    Image(
            painter = painterResource(id = R.drawable.insta),
            contentDescription = "logo",
            modifier = modifier
    )
}

@Composable
fun Header2(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close app",
            modifier = modifier.clickable { activity.finish() })
}
