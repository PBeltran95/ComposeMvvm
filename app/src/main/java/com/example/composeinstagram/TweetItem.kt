package com.example.composeinstagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeinstagram.ui.theme.ComposeInstagramTheme

@Composable
fun TweetItem() {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .background(Color(0xFF171C28))
    ) {
        Column {
            Row(Modifier.padding(8.dp)) {
                ProfilePhoto()
                TweetBody()
            }
            Divider(
                modifier = Modifier
                    .background(Color.Gray)
                    .height(1.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun TweetBody() {
    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        ) {
            Name(Modifier.weight(1f))
            UserName(Modifier.weight(2f))
            TweetDateOrHours(Modifier.weight(2.5f))
            OptionsIcon(Modifier.weight(1f))
        }
        Message(
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        )
        SharedImage(
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        )
        InteractiveIcons(
            Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun InteractiveIcons(modifier: Modifier) {
    Row(
        modifier = modifier.padding(top = 8.dp, end = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CommentButton()
        ReTweetsButton()
        LikesButton()
    }
}

@Composable
fun LikesButton() {
    var likesCount by rememberSaveable { mutableStateOf(1) }
    var likeClicked by rememberSaveable { mutableStateOf(false) }
    var likeIcon = R.drawable.ic_like
    var likeTint = Color.Gray
    IconButton(onClick = {
        likeClicked = !likeClicked
        if (likeClicked) {
            likeIcon = R.drawable.ic_like_filled
            likeTint = Color.Red
            likesCount++
        } else {
            likeIcon = R.drawable.ic_like
            likeTint = Color.Gray
            likesCount--
        }
    }) {
        Icon(
            painter = painterResource(id = likeIcon),
            contentDescription = "Like icon",
            tint = likeTint
        )
        Text(
            text = likesCount.toString(),
            color = Color.Gray,
            fontSize = 11.sp,
            modifier = Modifier.padding(start = 40.dp)
        )
    }
}

@Composable
fun ReTweetsButton() {
    var reTweetsCount by rememberSaveable { mutableStateOf(1) }
    var reTweetClicked by rememberSaveable { mutableStateOf(false) }
    var chatTint = Color.Gray
    IconButton(onClick = {
        reTweetClicked = !reTweetClicked
        if (reTweetClicked) {
            chatTint = Color.Green
            reTweetsCount++
        } else {
            chatTint = Color.Gray
            reTweetsCount--
        }
    }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_rt),
            contentDescription = "Re-tweet this tweet",
            tint = chatTint
        )
        Text(
            text = reTweetsCount.toString(),
            color = Color.Gray,
            fontSize = 11.sp,
            modifier = Modifier.padding(start = 40.dp)
        )
    }
}

@Composable
fun CommentButton() {

    var commentNumber by rememberSaveable { mutableStateOf(1) }
    var commentClicked by rememberSaveable { mutableStateOf(false) }
    var chatIcon = R.drawable.ic_chat
    var chatTint = Color.Gray
    IconButton(onClick = {
        commentClicked = !commentClicked
        if (commentClicked) {
            chatIcon = R.drawable.ic_chat_filled
            chatTint = Color.Cyan
            commentNumber++
        } else {
            chatIcon = R.drawable.ic_chat
            chatTint = Color.Gray
            commentNumber--
        }
    }) {
        Icon(
            painter = painterResource(id = chatIcon),
            contentDescription = "Write a comment",
            tint = chatTint
        )
        Text(
            text = commentNumber.toString(),
            color = Color.Gray,
            fontSize = 11.sp,
            modifier = Modifier.padding(start = 40.dp)
        )
    }
}

@Composable
fun OptionsIcon(modifier: Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.ic_dots),
        contentDescription = "Options",
        tint = Color.White,
        modifier = modifier
    )
}

@Composable
fun TweetDateOrHours(modifier: Modifier) {
    Text(
        text = "4h",
        fontSize = 15.sp,
        color = Color.Gray,
        modifier = modifier.padding(start = 8.dp)
    )
}

@Composable
fun UserName(modifier: Modifier) {
    Text(
        text = "@AristiDevs",
        fontSize = 15.sp,
        color = Color.Gray,
        modifier = modifier.padding(start = 8.dp)
    )
}

@Composable
fun Name(modifier: Modifier) {
    Text(
        text = "Aris",
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun SharedImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "Profile Image",
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .height(200.dp)
            .padding(top = 8.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun Message(modifier: Modifier) {
    Text(
        text = "Lorem Ipsum es simplemente el texto de relleno de las " +
                "imprentas y archivos de texto. Lorem Ipsum ha sido el texto " +
                "de relleno estándar de las industrias desde el año 1500. ",
        color = Color.White,
        fontWeight = FontWeight.W400,
        modifier = modifier
    )
}

@Composable
fun ProfilePhoto() {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TweetPreview() {
    ComposeInstagramTheme {
        TweetItem()
    }
}
