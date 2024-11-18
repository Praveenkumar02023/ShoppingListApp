package com.praveen.myshoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Data class to represent a shopping item with an ID, name, quantity, and editing state
data class ShoppingItem(
    var Id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false // Tracks whether the item is in edit mode
)

@Composable
fun ShoppingListApp() {
    // State variables to manage the shopping list and dialog visibility
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) } // List of shopping items
    var showDialog by remember { mutableStateOf(false) } // Controls visibility of the "Add Item" dialog
    var itemName by remember { mutableStateOf("") } // Stores the name of the item being added
    var itemQuantity by remember { mutableStateOf("") } // Stores the quantity of the item being added

    // Main column layout for the app
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center // Center content vertically
    ) {
        // Button to open the "Add Item" dialog
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }

        // LazyColumn to display the list of shopping items
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // Padding around the list
        ) {
            // Dynamically display each item using the ShoppingListItem composable
            items(sItems) { item ->
                ShoppingListItem(item, {}, {})
            }
        }
    }

    // Show the "Add Item" dialog if showDialog is true
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false }, // Close dialog when dismissed
            confirmButton = { /* Confirm button placeholder */ },
            title = { Text(text = "Add shopping Item :") }, // Dialog title
            text = {
                Column {
                    // TextField for entering item name
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = {
                            itemName = it
                        },
                        singleLine = true, // Restrict input to a single line
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    // TextField for entering item quantity
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = {
                            itemQuantity = it
                        },
                        singleLine = true, // Restrict input to a single line
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    // Row for "Add" and "Cancel" buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween // Space buttons evenly
                    ) {
                        // Button to add a new item to the shopping list
                        Button(onClick = {
                            if (itemName.isNotBlank()) { // Only add if item name is not empty
                                val newItem = ShoppingItem(
                                    Id = sItems.size + 1, // Assign a unique ID
                                    name = itemName,
                                    quantity = itemQuantity.toInt() // Convert quantity to integer
                                )
                                sItems = sItems + newItem // Add new item to the list
                                itemName = "" // Clear the input fields
                                showDialog = false // Close the dialog
                            }
                        }) {
                            Text(text = "Add")
                        }
                        // Button to cancel and close the dialog
                        Button(onClick = {
                            showDialog = false // Close the dialog
                        }) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        )
    }
}

// Composable to display a single shopping item in the list
@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit, // Lambda for handling edit button clicks
    onDeleteClick: () -> Unit // Lambda for handling delete button clicks
) {
    // Row layout for displaying item details and actions
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                BorderStroke(2.dp, Color.Cyan), // Cyan border around the item
                shape = RoundedCornerShape(20) // Rounded corners for the border
            )
    ) {
        // Display item name
        Text(text = item.name, modifier = Modifier.padding(16.dp))
        // Display item quantity
        Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(16.dp))
        // Edit button with its action
        IconButton(onClick = { onEditClick() }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
        // Delete button with its action
        IconButton(onClick = { onDeleteClick() }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}
