package com.appfactory.ecommerce.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

data class Product(
    val id: String,
    val name: String,
    val price: Float,
    val originalPrice: Float,
    val rating: Float,
    val description: String,
    val image: String
)

@Composable
fun ProductListScreen(
    onProductClick: (Product) -> Unit = {},
    onAddToCartClick: (Product) -> Unit = {}
) {
    val products = listOf(
        Product(
            id = "1",
            name = "Premium Headphones",
            price = 79.99f,
            originalPrice = 129.99f,
            rating = 4.5f,
            description = "High-quality wireless headphones",
            image = "headphones"
        ),
        Product(
            id = "2",
            name = "Smartphone Case",
            price = 14.99f,
            originalPrice = 24.99f,
            rating = 4.8f,
            description = "Protective and stylish case",
            image = "case"
        ),
        Product(
            id = "3",
            name = "USB-C Cable",
            price = 9.99f,
            originalPrice = 19.99f,
            rating = 4.2f,
            description = "Durable fast charging cable",
            image = "cable"
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        TopAppBar(
            title = { Text("Shop") },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Cart"
                    )
                }
            }
        )

        // Products List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onProductClick = { onProductClick(product) },
                    onAddToCartClick = { onAddToCartClick(product) }
                )
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onProductClick: () -> Unit = {},
    onAddToCartClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductClick() }
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Product Image Placeholder
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        product.image,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Product Name
            Text(
                product.name,
                style = MaterialTheme.typography.titleMedium
            )

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    "★ ${product.rating}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    "(${(product.rating * 100).toInt()} reviews)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Price
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    "$${product.price}",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    "$${product.originalPrice}",
                    style = MaterialTheme.typography.bodySmall,
                    textDecoration = TextDecoration.LineThrough,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Add to Cart Button
            Button(
                onClick = onAddToCartClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add to Cart")
            }
        }
    }
}

@Composable
fun ProductDetailScreen(
    product: Product,
    onBackClick: () -> Unit = {},
    onAddToCartClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = { Text("Product Details") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Text("←")
                }
            }
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(product.image)
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                product.name,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                product.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    "★ ${product.rating}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                "$${product.price}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Button(
                onClick = onAddToCartClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Add to Cart")
            }
        }
    }
}

import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
