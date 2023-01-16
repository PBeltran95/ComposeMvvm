package com.example.composeinstagram.login.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeinstagram.R
import com.example.composeinstagram.ui.theme.*

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center), loginViewModel)
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier
                .background(GrayDivider)
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun SignUp() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "Don't have an account?", fontSize = 12.sp, color = ORGray,)
        Text(
            text = "Sign up.",
            modifier = Modifier.padding(start = 4.dp),
            fontSize = 12.sp,
            color = WhiteBlue,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Header(modifier: Modifier) {
//    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close the app",
        modifier = modifier.clickable { /*activity.finish()*/ }
    )
}

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {
    val email by loginViewModel.email.observeAsState(initial = "")
    val password by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnabled by loginViewModel.isLoginEnabled.observeAsState(initial = false)
    Column(modifier = modifier) {
        InstagramLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        EmailField(Modifier.fillMaxWidth(), email) {
            loginViewModel.onLoginChanged(email = it, password = password)
        }
        Spacer(modifier = Modifier.height(4.dp))
        PasswordField(Modifier.fillMaxWidth(), password) {
            loginViewModel.onLoginChanged(password = it, email = email)
        }
        Spacer(modifier = Modifier.height(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(isLoginEnabled)
        Spacer(modifier = Modifier.height(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.height(32.dp))
        SocialLogin()
    }
}

@Composable
fun SocialLogin() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Social Login fb",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continue with Facebook",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = SocialMediaColor
        )
    }
}

@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            modifier = Modifier
                .background(GrayDivider)
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = ORGray
        )
        Divider(
            modifier = Modifier
                .background(GrayDivider)
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun LoginButton(isLoginEnabled: Boolean) {
    Button(
        onClick = { /*TODO*/ },
        enabled = isLoginEnabled,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF3A86BD),
            contentColor = Color.White,
            disabledBackgroundColor = Color(0xFF78C8F9),
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Log In")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = WhiteBlue,
        modifier = modifier
    )
}

@Composable
fun PasswordField(modifier: Modifier, password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = password, onValueChange = { onTextChanged(it) }, modifier = modifier,
        placeholder = { Text(text = "Password", color = ORGray) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedBorderColor = WhiteBlue,
            unfocusedBorderColor = BorderGray
        ),
        trailingIcon = {
            val image = if (passwordVisibility) {
                painterResource(id = R.drawable.ic_invisible)
            } else {
                painterResource(id = R.drawable.ic_visible)
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(painter = image, contentDescription = "Hide or show password icon")
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@Composable
fun EmailField(modifier: Modifier, email: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = modifier,
        placeholder = { Text(text = "Email", color = ORGray) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedBorderColor = WhiteBlue,
            unfocusedBorderColor = BorderGray
        )
    )
}

@Composable
fun InstagramLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "Logo",
        contentScale = ContentScale.None.copy(0.5f),
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    ComposeInstagramTheme {
//        LoginScreen()
    }
}
