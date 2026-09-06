package com.example.exploracioncomponentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Vista previa general
                    DemoScaffold()
                }
            }
        }
    }
}

@Composable
fun DemoLazyColumn() {
    LazyColumn(modifier = Modifier.height(120.dp)) {
        items(5) { index ->
            Text("Elemento LazyColumn #$index", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DemoLazyRow() {
    LazyRow(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        items(5) { index ->
            Card(modifier = Modifier.padding(4.dp)) {
                Text("Item #$index", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun DemoGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(100.dp)
    ) {
        items(4) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.LightGray)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Grid $index")
            }
        }
    }
}

@Composable
fun DemoConstraintLayout() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth().height(80.dp)) {
        val (button, text) = createRefs()

        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        ) {
            Text("Botón")
        }

        Text(
            text = "Al lado del botón",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(button.top)
                bottom.linkTo(button.bottom)
                start.linkTo(button.end, margin = 16.dp)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScaffold() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Scaffold Demo") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            Text("Contenido dentro de Scaffold")
        }
    }
}

@Composable
fun DemoSurface() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFFE3F2FD),
        tonalElevation = 4.dp,
        modifier = Modifier.padding(8.dp)
    ) {
        Text("Este es un Surface personalizado", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun DemoChip() {
    AssistChip(
        onClick = { },
        label = { Text("Assist Chip") },
        leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) }
    )
}

@Composable
fun DemoBackdropScaffold() {
    Column(modifier = Modifier.fillMaxWidth().background(Color.DarkGray).padding(8.dp)) {
        Text("Capa Posterior (Back Layer)", color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) {
            Text("Capa Frontal (Front Layer)", modifier = Modifier.padding(16.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DemoFlowRow() {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(6) { index ->
            SuggestionChip(onClick = { }, label = { Text("Etiqueta $index") })
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DemoFlowColumn() {
    FlowColumn(modifier = Modifier.height(100.dp)) {
        repeat(4) { index ->
            Text("Col $index", modifier = Modifier.padding(4.dp))
        }
    }
}

//Controles
@Composable
fun DemoAlertDialog() {
    var openDialog by remember { mutableStateOf(true) }
    if (openDialog) {
        AlertDialog(
            onDismissRequest = { openDialog = false },
            title = { Text("Título de Alerta") },
            text = { Text("Este es el mensaje de la alerta.") },
            confirmButton = {
                TextButton(onClick = { openDialog = false }) { Text("Aceptar") }
            }
        )
    }
}

@Composable
fun DemoCard() {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text("Tarjeta de contenido (Card)", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun DemoCheckbox() {
    var checked by remember { mutableStateOf(true) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = { checked = it })
        Text("Aceptar términos")
    }
}

@Composable
fun DemoFloatingActionButton() {
    FloatingActionButton(onClick = { }) {
        Icon(Icons.Default.Add, contentDescription = "Agregar")
    }
}

@Composable
fun DemoIcon() {
    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorito", tint = Color.Red)
}

@Composable
fun DemoImage() {
    // Usa un recurso de imagen válido en res/drawable/ o painterResource
    Icon(
        imageVector = Icons.Default.Home,
        contentDescription = "Demo Imagen",
        modifier = Modifier.size(48.dp)
    )
}

@Composable
fun DemoProgressBar() {
    Column {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(progress = { 0.7f })
    }
}

@Composable
fun DemoRadioButton() {
    var selected by remember { mutableStateOf(true) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected, onClick = { selected = !selected })
        Text("Opción 1")
    }
}

@Composable
fun DemoSlider() {
    var sliderValue by remember { mutableStateOf(0.5f) }
    Slider(value = sliderValue, onValueChange = { sliderValue = it })
}

@Composable
fun DemoSpacer() {
    Row {
        Text("Izquierda")
        Spacer(modifier = Modifier.width(32.dp))
        Text("Derecha")
    }
}

@Composable
fun DemoSwitch() {
    var checked by remember { mutableStateOf(true) }
    Switch(checked = checked, onCheckedChange = { checked = it })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoTopAppBar() {
    TopAppBar(
        title = { Text("Mi Aplicación") },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Menu, contentDescription = "Menú")
            }
        }
    )
}

@Composable
fun DemoBottomNavigation() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Icon(Icons.Default.Settings, contentDescription = null) },
            label = { Text("Ajustes") }
        )
    }
}

@Composable
fun DemoDialog() {
    Dialog(onDismissRequest = { }) {
        Surface(shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(16.dp)) {
            Text("Contenido de Dialog Personalizado", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun DemoDivider() {
    Column {
        Text("Texto Superior")
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Text("Texto Inferior")
    }
}

@Composable
fun DemoDropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Button(onClick = { expanded = true }) { Text("Opciones") }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text("Opción 1") }, onClick = { expanded = false })
            DropdownMenuItem(text = { Text("Opción 2") }, onClick = { expanded = false })
        }
    }
}

@Composable
fun DemoLazyVerticalGrid() {
    DemoGrid()
}

@Composable
fun DemoNavigationRail() {
    NavigationRail {
        NavigationRailItem(
            selected = true,
            onClick = { },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Inicio") }
        )
    }
}

@Composable
fun DemoOutlinedTextField() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Ingresa tu nombre") }
    )
}

@Composable
fun DemoPager() {
    val pagerState = rememberPagerState(pageCount = { 3 })
    HorizontalPager(state = pagerState, modifier = Modifier.height(60.dp)) { page ->
        Text("Página Pager: $page", modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun DemoSnackbar() {
    Snackbar(
        action = {
            TextButton(onClick = { }) { Text("Deshacer") }
        }
    ) {
        Text("Este es un mensaje de Snackbar")
    }
}

@Composable
fun DemoTabRow() {
    var selectedTab by remember { mutableStateOf(0) }
    TabRow(selectedTabIndex = selectedTab) {
        Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) { Text("Tab 1", modifier = Modifier.padding(16.dp)) }
        Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) { Text("Tab 2", modifier = Modifier.padding(16.dp)) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoTooltip() {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = { PlainTooltip { Text("Información adicional") } },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = { }) {
            Icon(Icons.Default.Info, contentDescription = null)
        }
    }
}


@Preview(showBackground = true, name = "LazyColumn")
@Composable fun PreviewLazyColumn() { DemoLazyColumn() }

@Preview(showBackground = true, name = "LazyRow")
@Composable fun PreviewLazyRow() { DemoLazyRow() }

@Preview(showBackground = true, name = "Grid")
@Composable fun PreviewGrid() { DemoGrid() }

@Preview(showBackground = true, name = "ConstraintLayout")
@Composable fun PreviewConstraintLayout() { DemoConstraintLayout() }

@Preview(showBackground = true, name = "Scaffold")
@Composable fun PreviewScaffold() { DemoScaffold() }

@Preview(showBackground = true, name = "Surface")
@Composable fun PreviewSurface() { DemoSurface() }

@Preview(showBackground = true, name = "Chip")
@Composable fun PreviewChip() { DemoChip() }

@Preview(showBackground = true, name = "BackdropScaffold")
@Composable fun PreviewBackdropScaffold() { DemoBackdropScaffold() }

@Preview(showBackground = true, name = "FlowRow")
@Composable fun PreviewFlowRow() { DemoFlowRow() }

@Preview(showBackground = true, name = "FlowColumn")
@Composable fun PreviewFlowColumn() { DemoFlowColumn() }

// --- Controles Grupo 1 ---
@Preview(showBackground = true, name = "Card")
@Composable fun PreviewCard() { DemoCard() }

@Preview(showBackground = true, name = "Checkbox")
@Composable fun PreviewCheckbox() { DemoCheckbox() }

@Preview(showBackground = true, name = "FloatingActionButton")
@Composable fun PreviewFAB() { DemoFloatingActionButton() }

@Preview(showBackground = true, name = "Icon")
@Composable fun PreviewIcon() { DemoIcon() }

@Preview(showBackground = true, name = "Image")
@Composable fun PreviewImage() { DemoImage() }

@Preview(showBackground = true, name = "ProgressBar")
@Composable fun PreviewProgressBar() { DemoProgressBar() }

@Preview(showBackground = true, name = "RadioButton")
@Composable fun PreviewRadioButton() { DemoRadioButton() }

@Preview(showBackground = true, name = "Slider")
@Composable fun PreviewSlider() { DemoSlider() }

@Preview(showBackground = true, name = "Spacer")
@Composable fun PreviewSpacer() { DemoSpacer() }

@Preview(showBackground = true, name = "Switch")
@Composable fun PreviewSwitch() { DemoSwitch() }

@Preview(showBackground = true, name = "TopAppBar")
@Composable fun PreviewTopAppBar() { DemoTopAppBar() }

// --- Controles Grupo 2 ---
@Preview(showBackground = true, name = "BottomNavigation")
@Composable fun PreviewBottomNavigation() { DemoBottomNavigation() }

@Preview(showBackground = true, name = "Dialog")
@Composable fun PreviewDialog() { DemoDialog() }

@Preview(showBackground = true, name = "Divider")
@Composable fun PreviewDivider() { DemoDivider() }

@Preview(showBackground = true, name = "DropDownMenu")
@Composable fun PreviewDropDownMenu() { DemoDropDownMenu() }

@Preview(showBackground = true, name = "NavigationRail")
@Composable fun PreviewNavigationRail() { DemoNavigationRail() }

@Preview(showBackground = true, name = "OutlinedTextField")
@Composable fun PreviewOutlinedTextField() { DemoOutlinedTextField() }

@Preview(showBackground = true, name = "Pager")
@Composable fun PreviewPager() { DemoPager() }

@Preview(showBackground = true, name = "Snackbar")
@Composable fun PreviewSnackbar() { DemoSnackbar() }

@Preview(showBackground = true, name = "TabRow")
@Composable fun PreviewTabRow() { DemoTabRow() }

@Preview(showBackground = true, name = "Tooltip")
@Composable fun PreviewTooltip() { DemoTooltip() }